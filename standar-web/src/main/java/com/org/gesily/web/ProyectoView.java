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
import com.org.gesily.model.Departamento;
import com.org.gesily.model.Municipio;
import com.org.gesily.model.Persona;
import com.org.gesily.model.Proyecto;
import com.org.gesily.services.CandidatoService;
import com.org.gesily.services.DepartamentoService;
import com.org.gesily.services.MunicipioService;
import com.org.gesily.services.ProyectoService;
import com.org.school.enums.Gender;
import com.org.school.services.MunicipalService;
import com.org.security.service.SecurityManagedService;
import com.org.util.web.BaseLazyModel;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class ProyectoView implements Serializable {

	private static final long serialVersionUID = 80351893876789L;

	@Inject
	private Identity identity;

	@Inject
	private transient SecurityManagedService securityManagedService;

	@Inject
	private transient ProyectoService proyectoService;

	@Inject
	private transient DepartamentoService departamentoService;

	@Inject
	private transient MunicipioService municipioService;

	private Proyecto proyecto;

	private Departamento selectedDepartamento;
	
	private List<Departamento> departamentosList;
	
	private List<Municipio> municipiosList;

	private BaseLazyModel<Proyecto, Long> proyectoLazyData;

	private boolean renderEditView;
	
	private boolean isEdit;

	@PostConstruct
	public void init() {
		renderEditView = false;
		loadProyectos();
	}

	public void loadProyectos() {
		proyectoLazyData = new BaseLazyModel<>(getProyectoService());
	}

	public void prepareSave() {
		isEdit = false;
		renderEditView = true;
		proyecto = new Proyecto();
		departamentosList = departamentoService.findAll();
	}

	public void loadMunicipios() {
		if (selectedDepartamento == null || selectedDepartamento.getId() == null) {
			Messages.create("ADVERTENCIA").detail("Seleccione departamento").warn().add();
		} else {
			municipiosList = municipioService.findByDepartamento(selectedDepartamento);
		}
	}

	public void save() {
		proyectoService.save(proyecto);
		Messages.create("INFO").detail("Proyecto registrado exitosamente").add();
		renderEditView = false;
	}

	public void prepareUpdate() {
		isEdit = true;
		renderEditView = true;
		selectedDepartamento = proyecto.getMunicipio().getDepartamento();
		departamentosList = departamentoService.findAll();
		loadMunicipios();
	}

	public void update() {
		proyectoService.save(proyecto);
		Messages.create("INFO").detail("Proyecto actualizado exitosamente").add();
		renderEditView = false;
	}

	public void delete() {
		if (proyecto != null) {
			proyectoService.delete(proyecto);
			Messages.create("INFO").detail("Proyecto eliminado exitosamente").add();
		}
	}
}
