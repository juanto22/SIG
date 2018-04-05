package com.org.gesily.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.org.gesily.model.Persona;
import com.org.gesily.repository.PersonaRepository;
import com.org.util.repository.BaseRepository;
import com.org.util.service.BaseService;

@Stateless
public class PersonaService extends BaseService<Persona, Long> {

	@Inject
	private PersonaRepository personaRepository;

	@Override
	public BaseRepository<Persona, Long> getRepository() {
		return personaRepository;
	}

}
