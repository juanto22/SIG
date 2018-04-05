package com.org.gesily.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.org.gesily.model.Proyecto;
import com.org.gesily.repository.ProyectoRepository;
import com.org.util.repository.BaseRepository;
import com.org.util.service.BaseService;

@Stateless
public class ProyectoService extends BaseService<Proyecto, Long> {

	@Inject
	private ProyectoRepository proyectoRepository;

	@Override
	public BaseRepository<Proyecto, Long> getRepository() {
		return proyectoRepository;
	}

}
