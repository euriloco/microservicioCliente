package com.frc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.frc.model.Curso;
import com.frc.model.Formacion;

@Service
public class FormacionServiceImpl implements FormacionService {
	@Autowired
	RestTemplate template;

	private String url = "http://localhost:8080";

	@Override
	public List<Formacion> listadodeAsignaturas() {
		List<Formacion> formacion = new ArrayList<>();
		Curso[] cursos = template.getForObject(url + "/listado", Curso[].class);
		System.out.println("Estás dentro service");
		for (Curso curso : cursos) {
			Formacion elemento = new Formacion(curso.getNombre(), curso.getHoras() >= 50 ? 10 : 5, curso.getPrecio());
			formacion.add(elemento);
		}
		return formacion;
	}

	@Override
	public Curso altaCurso(Formacion formacion) {
		Curso[] cursos = template.getForObject(url + "/listado", Curso[].class);
		/*
		 * for(Curso curso: cursos) { if(formacion.getCurso().toLowerCase() !=
		 * curso.getNombre().toLowerCase()) { System.out.println("Dentro service ALTA");
		 * curso.setHoras(formacion.getAsignaturas()*10); String codigo = (String.
		 * valueOf(formacion.getCurso().charAt(0))+ String.
		 * valueOf(formacion.getCurso().charAt(1))+ String.
		 * valueOf(formacion.getCurso().charAt(2))+ Integer.
		 * toString(formacion.getAsignaturas()*10)); System.out.println(codigo);
		 * curso.setCodCurso(codigo); template.postForLocation(url+"/alta", curso);
		 * 
		 */
		// Verificar si ya existe un curso con el mismo nombre
		for (Curso curso : cursos) {
			if (curso.getNombre().equalsIgnoreCase(formacion.getCurso())) {
				// Si ya existe un curso con el mismo nombre, no se hace nada
				return null;
			}
		}

		// Generar el código del curso a partir de los tres primeros caracteres del
		// nombre
		// y la duración (asignaturas * 10)
		String codCurso = formacion.getCurso().substring(0, 3) + (formacion.getAsignaturas() * 10);

		// Calcular la duración del curso
		int duracion = formacion.getAsignaturas() * 10;

		// Crear y retornar el nuevo objeto Curso
		Curso cursoNuevo = new Curso(codCurso, formacion.getCurso(), duracion, formacion.getPrecio());
		template.postForLocation(url + "/alta", cursoNuevo);
		return cursoNuevo;
	}

}
