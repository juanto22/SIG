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
@Table(name = "ENTREVISTA_PROYECTO")
@SequenceGenerator(name = "SEQ_EPROYECTO", sequenceName = "SEQ_EPROYECTO", allocationSize = 1)
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class EntrevistasProyecto implements BaseModelEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5309910007244780631L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EPROYECTO")
	@Column(nullable = false)
	private Long id;

	private String observaciones;

	private Long realizador;//posible objeto del empleado que lo entrevisto
	
	private Boolean aceptado;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fechaRealizada;
	
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "CANDIDATO_ID", referencedColumnName = "ID", nullable = false) })
	@NotFound(action = NotFoundAction.IGNORE)
	private Candidato candidato;
	
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "PROYECTO_ID", referencedColumnName = "ID", nullable = false) })
	@NotFound(action = NotFoundAction.IGNORE)
	private Proyecto proyecto;

}
