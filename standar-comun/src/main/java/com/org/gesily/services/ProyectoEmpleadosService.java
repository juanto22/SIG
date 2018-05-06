package com.org.gesily.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.org.gesily.model.Proyecto;
import com.org.gesily.model.ProyectoEmpleados;
import com.org.gesily.repository.ProyectoEmpleadosRepository;
import com.org.gesily.repository.ProyectoRepository;
import com.org.util.repository.BaseRepository;
import com.org.util.service.BaseService;

@Stateless
public class ProyectoEmpleadosService extends BaseService<ProyectoEmpleados, Long> {

	@Inject
	private ProyectoEmpleadosRepository proyectoEmpleadosRepository;

	@Override
	public BaseRepository<ProyectoEmpleados, Long> getRepository() {
		return proyectoEmpleadosRepository;
	}

}
