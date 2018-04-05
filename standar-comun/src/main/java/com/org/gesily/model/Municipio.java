package com.org.gesily.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.org.util.domain.BaseModelEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "MUNICIPIO")
@SequenceGenerator(name = "SEQ_MUNICIPIO", sequenceName = "SEQ_MUNICIPIO", allocationSize = 1)
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class Municipio implements BaseModelEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8922231230568995633L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MUNICIPIO")
	@Column(nullable = false)
	private Long id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "DEPARTAMENTO_ID")
	private Departamento departamento;
}
