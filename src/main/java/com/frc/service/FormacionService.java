package com.frc.service;

import java.util.List;

import com.frc.model.Formacion;

public interface FormacionService {
	
	List<Formacion> listadodeAsignaturas();
	Formacion altaCurso(Formacion formacion);

}
