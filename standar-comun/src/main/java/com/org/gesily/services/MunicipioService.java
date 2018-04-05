package com.org.gesily.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.org.gesily.model.Municipio;
import com.org.gesily.repository.MunicipioRepository;
import com.org.util.repository.BaseRepository;
import com.org.util.service.BaseService;

@Stateless
public class MunicipioService extends BaseService<Municipio, Long> {

	@Inject
	private MunicipioRepository municipioRepository;

	@Override
	public BaseRepository<Municipio, Long> getRepository() {
		return municipioRepository;
	}

}
