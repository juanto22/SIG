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
@Table(name = "PROYECTOS")
@SequenceGenerator(name = "SEQ_PROYECTOS", sequenceName = "SEQ_PROYECTOS", allocationSize = 1)
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class Proyecto implements BaseModelEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1226746728446058616L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROYECTOS")
	@Column(nullable = false)
	private Long id;

	private String nombre;

	private String descripcion;

	private String supervisor;//posible objeto de empleado

	private Double costo;

	private Integer nroPersonas;
	
	private String estado;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fechaFin;
	
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "PERSONA_ID", referencedColumnName = "ID", nullable = false) })
	@NotFound(action = NotFoundAction.IGNORE)
	private Municipio municipio;

}
