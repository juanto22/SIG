package com.org.gesily.model;

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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.org.util.domain.BaseModelEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CANDIDATO")
@SequenceGenerator(name = "SEQ_CANDIDATO", sequenceName = "SEQ_CANDIDATO", allocationSize = 1)
@EqualsAndHashCode(of = { "id" })
@ToString(of = { "id" })
@Getter
@Setter
public class Candidato implements BaseModelEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9023714707253300872L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CANDIDATO")
	@Column(nullable = false)
	private Long id;

	private String codigoEmpleado;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "PERSONA_ID", referencedColumnName = "ID", nullable = false) })
	@NotFound(action = NotFoundAction.IGNORE)
	private Persona persona;



}
