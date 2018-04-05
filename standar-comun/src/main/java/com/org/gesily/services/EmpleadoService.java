package com.org.gesily.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.org.gesily.model.Empleado;
import com.org.gesily.repository.EmpleadoRepository;
import com.org.util.repository.BaseRepository;
import com.org.util.service.BaseService;

@Stateless
public class EmpleadoService extends BaseService<Empleado, Long> {

	@Inject
	private EmpleadoRepository empleadoRepository;

	@Override
	public BaseRepository<Empleado, Long> getRepository() {
		return empleadoRepository;
	}

}
