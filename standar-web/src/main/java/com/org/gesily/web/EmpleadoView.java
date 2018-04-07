package com.org.gesily.web;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;
import org.picketlink.Identity;

import com.org.gesily.model.Candidato;
import com.org.gesily.model.Empleado;
import com.org.gesily.model.Persona;
import com.org.gesily.services.CandidatoService;
import com.org.gesily.services.EmpleadoService;
import com.org.school.enums.Gender;
import com.org.security.service.SecurityManagedService;
import com.org.util.web.BaseLazyModel;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class EmpleadoView implements Serializable {

	private static final long serialVersionUID = 80351893876789L;

	@Inject
	private Identity identity;

	@Inject
	private transient SecurityManagedService securityManagedService;

	@Inject
	private transient CandidatoService candidatoService;
	
	@Inject
	private transient EmpleadoService empleadoService;

	private Candidato candidato;
	
	private Persona persona;
	
	private Empleado empleado;

	private List<Gender> genderList;

	private BaseLazyModel<Candidato, Long> candidatoLazyData;
	
	private BaseLazyModel<Empleado, Long> empleadoLazyData;
	
	private boolean renderEditView;


	@PostConstruct
	public void init() {
		renderEditView = false;
		genderList = Arrays.asList(Gender.values());
		loadLazyData();
	}

	public void loadLazyData() {
		empleadoLazyData = new BaseLazyModel<>(getEmpleadoService());
		candidatoLazyData = new BaseLazyModel<>(getCandidatoService());
	}
	
	public void prepareEmpleamiento() {
		candidato = new Candidato();
	}
	
	public void addEmpleado() {
		Empleado empleado = new Empleado();
		empleado.setPersona(candidato.getPersona());
		
		empleadoService.save(empleado);
		Messages.create("INFO").detail("Candidato contratado").add();
		
	}
	
	public void prepareSave() {
		renderEditView = true;
		candidato = new Candidato();
		persona = new Persona();
	}

	public void prepareUpdate() {
		renderEditView = true;
		persona = empleado.getPersona();
	}
	
	public void update() {
		empleadoService.saveEmpleado(empleado, persona);
		Messages.create("INFO").detail("Empleado actualizado exitosamente").add();
		renderEditView = false;
	}

	public void delete() {
		if (empleado != null) {
			empleadoService.delete(empleado);
			Messages.create("INFO").detail("Empleado eliminado exitosamente").add();
		}
	}
}
