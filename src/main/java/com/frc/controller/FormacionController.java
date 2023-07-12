package com.frc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.frc.model.Curso;
import com.frc.model.Formacion;
import com.frc.service.FormacionService;

@RestController
public class FormacionController {
	@Autowired
	FormacionService service;

	@GetMapping(value = "listado")
	public List<Formacion> listadoFormaciones() {
		System.out.println("DENTRO CONTROLADOR");
		return service.listadodeAsignaturas();
	}

	@GetMapping(value = "alta/{nombreCurso}/{asignaturas}/{precio}")
	public Curso altaFormacion(@PathVariable("nombreCurso") String curso,
										@PathVariable("asignaturas") int asignaturas, 
										@PathVariable("precio") double precio) {
		Formacion formacion = new Formacion(curso, asignaturas, precio);
		
		return service.altaCurso(formacion);
	}
}
