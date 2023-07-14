package com.frc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.frc.model.Formacion;
import com.frc.service.FormacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Microservicio para el alta de cursos")
public class FormacionController {
	@Autowired
	FormacionService service;

	@Operation(summary = "Listado de cursos con sus asignaturas", description = "Muestra el listado de cursos existente en la base de datos con el número de asignaturas que tiene cada uno")
	@GetMapping(value = "listado")
	public List<Formacion> listadoFormaciones() {
		System.out.println("DENTRO CONTROLADOR");
		return service.listadodeAsignaturas();
	}

	@Operation(summary = "Alta curso nuevo", description = "Alta de un curso nuevo mediante los datos pasados en la URL")
	@GetMapping(value = "alta/{nombreCurso}/{asignaturas}/{precio}")
	public Formacion altaFormacion(@Parameter(name = "nombreCurso", description = "Nombre del curso que se quiere añadir") @PathVariable("nombreCurso") String curso,
			@Parameter(name = "asignaturas", description = "Número de asignaturas que tiene el curso que se va a añadir") @PathVariable("asignaturas") int asignaturas, 
			@Parameter(name = "precio", description = "Precio del curso que se va a añadir") @PathVariable("precio") double precio) {
		Formacion formacion = new Formacion(curso, asignaturas, precio);
		
		return service.altaCurso(formacion);
	}
}
