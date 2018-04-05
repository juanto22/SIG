package com.org.gesily.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.org.gesily.model.Departamento;
import com.org.gesily.repository.DepartamentoRepository;
import com.org.util.repository.BaseRepository;
import com.org.util.service.BaseService;

@Stateless
public class DepartamentoService extends BaseService<Departamento, Long> {

	@Inject
	private DepartamentoRepository departamentoRepository;

	@Override
	public BaseRepository<Departamento, Long> getRepository() {
		return departamentoRepository;
	}


}
