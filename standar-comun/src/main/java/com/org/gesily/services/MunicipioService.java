package com.org.gesily.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.org.gesily.model.Departamento;
import com.org.gesily.model.Municipio;
import com.org.gesily.model.QMunicipio;
import com.org.gesily.repository.MunicipioRepository;
import com.org.util.repository.BaseRepository;
import com.org.util.service.BaseService;

@Stateless
public class MunicipioService extends BaseService<Municipio, Long> {

	@Inject
	private MunicipioRepository municipioRepository;
	
	private static final QMunicipio qMunicipio = QMunicipio.municipio;

	@Override
	public BaseRepository<Municipio, Long> getRepository() {
		return municipioRepository;
	}
	
	public List<Municipio> findByDepartamento(Departamento departamento){
		return newJpaQuery().from(qMunicipio)
				.where(qMunicipio.departamento.id.eq(departamento.getId()))
				.list(qMunicipio);
	}

}
