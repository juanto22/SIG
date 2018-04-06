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
import com.org.gesily.model.Persona;
import com.org.gesily.services.CandidatoService;
import com.org.school.enums.Gender;
import com.org.security.service.SecurityManagedService;
import com.org.util.web.BaseLazyModel;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class CandidatoView implements Serializable {

	private static final long serialVersionUID = 80351893876789L;

	@Inject
	private Identity identity;

	@Inject
	private transient SecurityManagedService securityManagedService;

	@Inject
	private transient CandidatoService candidatoService;

	private Candidato candidato;
	
	private Persona persona;

	private List<Gender> genderList;

	private BaseLazyModel<Candidato, Long> candidatoLazyData;
	
	private boolean renderEditView;


	@PostConstruct
	public void init() {
		renderEditView = false;
		genderList = Arrays.asList(Gender.values());
		loadTeachers();
	}

	public void loadTeachers() {
		candidatoLazyData = new BaseLazyModel<>(getCandidatoService());
	}

	public void prepareSave() {
		renderEditView = true;
		candidato = new Candidato();
		persona = new Persona();
		renderEditView = false;
	}

	public void save() {
		candidatoService.saveCandidato(candidato, persona);
		Messages.create("INFO").detail("Candidato guardado exitosamente").add();
	}

	public void prepareUpdate() {
		renderEditView = true;
		persona = candidato.getPersona();
	}
	
	public void update() {
		candidatoService.saveCandidato(candidato, candidato.getPersona());
		Messages.create("INFO").detail("Candidato actualizado exitosamente").add();
	}

	public void delete() {
		if (candidato != null) {
			candidatoService.delete(candidato);
			Messages.create("INFO").detail("Candidato eliminado exitosamente").add();
		}
	}
}
