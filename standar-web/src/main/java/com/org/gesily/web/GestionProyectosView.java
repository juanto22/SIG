package com.org.gesily.web;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;
import org.picketlink.Identity;

import com.org.gesily.model.Candidato;
import com.org.gesily.model.Departamento;
import com.org.gesily.model.Empleado;
import com.org.gesily.model.Municipio;
import com.org.gesily.model.Persona;
import com.org.gesily.model.Proyecto;
import com.org.gesily.model.ProyectoEmpleados;
import com.org.gesily.services.CandidatoService;
import com.org.gesily.services.DepartamentoService;
import com.org.gesily.services.MunicipioService;
import com.org.gesily.services.ProyectoEmpleadosService;
import com.org.gesily.services.ProyectoService;
import com.org.school.enums.Gender;
import com.org.school.services.MunicipalService;
import com.org.security.service.SecurityManagedService;
import com.org.util.enumeration.OperationType;
import com.org.util.safe.ValueHolder;
import com.org.util.web.BaseLazyModel;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class GestionProyectosView implements Serializable {

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
	private transient ProyectoEmpleadosService proyectoEmpleadosService;

	private Proyecto proyecto;

	private Departamento selectedDepartamento;
	
	private List<Departamento> departamentosList;
	
	private List<Municipio> municipiosList;

	private BaseLazyModel<Proyecto, Long> proyectoLazyData;
	
	private BaseLazyModel<ProyectoEmpleados, Long> empleadoLazyData;

	private boolean renderEditView;
	
	private boolean isEdit;

	@PostConstruct
	public void init() {
		renderEditView = false;
		loadProyectos();
	}

	public void loadProyectos() {
		proyectoLazyData = new BaseLazyModel<>(getProyectoService());
		proyectoLazyData.addCustomFilters(where());
	}
	
	public Set<ValueHolder> where() {
		Set<ValueHolder> filters = new HashSet<>();
		
		filters.add(new ValueHolder("estado", OperationType.EQ.getCode(), "APROBADO"));
		
		return filters;
	}
	
	public void prepareAsignarEmpleados() {
		
	}
	
	public void verEmpleados() {
		empleadoLazyData = new BaseLazyModel<>(getProyectoEmpleadosService());
		empleadoLazyData.addCustomFilters(whereEmpleados());
	}
	
	public Set<ValueHolder> whereEmpleados() {
		Set<ValueHolder> filters = new HashSet<>();
		
		filters.add(new ValueHolder("proyecto.id", OperationType.EQ.getCode(), proyecto.getId()));
		
		return filters;
	}

}
