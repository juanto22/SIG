package com.org.gesily.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.org.gesily.model.EntrevistasProyecto;
import com.org.gesily.repository.EntrevistasProyectoRepository;
import com.org.util.repository.BaseRepository;
import com.org.util.service.BaseService;

@Stateless
public class EntrevistasProyectoService extends BaseService<EntrevistasProyecto, Long>{

	@Inject
	private EntrevistasProyectoRepository entrevistasProyectoRepository;
	
	
	@Override
	public BaseRepository<EntrevistasProyecto, Long> getRepository() {
		return entrevistasProyectoRepository;
	}

}
