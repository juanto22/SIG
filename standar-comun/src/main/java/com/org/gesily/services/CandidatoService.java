package com.org.gesily.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.org.gesily.model.Candidato;
import com.org.gesily.model.Persona;
import com.org.gesily.repository.CandidatoRepository;
import com.org.gesily.repository.PersonaRepository;
import com.org.util.repository.BaseRepository;
import com.org.util.service.BaseService;

@Stateless
public class CandidatoService extends BaseService<Candidato, Long>{

	@Inject
	private CandidatoRepository candidatoRepository;
	
	@Inject
	private PersonaRepository personaRepository;
	
	@Override
	public BaseRepository<Candidato, Long> getRepository() {
		return candidatoRepository;
	}
	
	public void saveCandidato(Candidato candidato, Persona persona) {
		personaRepository.save(persona);
		candidato.setPersona(persona);
		candidatoRepository.save(candidato);
	}

}
