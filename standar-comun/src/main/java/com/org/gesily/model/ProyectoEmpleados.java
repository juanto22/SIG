package com.org.gesily.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.org.util.domain.BaseModelEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PROYECTOSEMPLEADOS")
@SequenceGenerator(name = "SEQ_PROYECTOSEMP", sequenceName = "SEQ_PROYECTOSEMP", allocationSize = 1)
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class ProyectoEmpleados implements BaseModelEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1226746728446058616L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROYECTOSEMP")
	@Column(nullable = false)
	private Long id;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fechaIncorporacion;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fechaBaja;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "PROYECTO_ID", referencedColumnName = "ID", nullable = false) })
	@NotFound(action = NotFoundAction.IGNORE)
	private Proyecto proyecto;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "EMPLEADO_ID", referencedColumnName = "ID", nullable = false) })
	@NotFound(action = NotFoundAction.IGNORE)
	private Empleado empleado;

}
