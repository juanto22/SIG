package com.org.gesily.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
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
import com.org.gesily.services.EmpleadoService;
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

	@Inject
	private transient EmpleadoService empleadoService;

	private Proyecto proyecto;

	private Departamento selectedDepartamento;

	private List<Departamento> departamentosList;

	private List<Municipio> municipiosList;

	private BaseLazyModel<Proyecto, Long> proyectoLazyData;

	private BaseLazyModel<ProyectoEmpleados, Long> empleadoLazyData;

	private BaseLazyModel<Empleado, Long> empleadosLazyData;

	private List<Empleado> empleadosForProyecto;

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
		empleadosLazyData = new BaseLazyModel<>(getEmpleadoService());
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

	public void asociarEmpleados() {
		List<ProyectoEmpleados> proyectoEmpleados = new ArrayList<>();
		ProyectoEmpleados proyectoEmpleado;
		for (Empleado empleado : empleadosForProyecto) {
			proyectoEmpleado = new ProyectoEmpleados();
			proyectoEmpleado.setProyecto(proyecto);
			proyectoEmpleado.setEmpleado(empleado);
			proyectoEmpleado.setFechaIncorporacion(new Date());

			proyectoEmpleados.add(proyectoEmpleado);
		}

		try {
			proyectoEmpleadosService.save(proyectoEmpleados);
			Messages.create("ASOCIACION EMPLEADOS").detail("Asociacion realizada exitosamente").add();
		} catch (Exception e) {
			Messages.create("ERROR").detail(e.getMessage()).error().add();
			Faces.validationFailed();
		}

	}

}
