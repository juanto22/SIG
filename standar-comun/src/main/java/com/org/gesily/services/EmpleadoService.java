package com.org.gesily.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.org.gesily.model.Candidato;
import com.org.gesily.model.Empleado;
import com.org.gesily.model.Persona;
import com.org.gesily.repository.EmpleadoRepository;
import com.org.gesily.repository.PersonaRepository;
import com.org.util.repository.BaseRepository;
import com.org.util.service.BaseService;

@Stateless
public class EmpleadoService extends BaseService<Empleado, Long> {

	@Inject
	private EmpleadoRepository empleadoRepository;
	
	@Inject
	private PersonaRepository personaRepository;

	@Override
	public BaseRepository<Empleado, Long> getRepository() {
		return empleadoRepository;
	}
	
	public void saveEmpleado(Empleado empleado, Persona persona) {
		personaRepository.save(persona);
		empleado.setPersona(persona);
		empleadoRepository.save(empleado);
	}

}
