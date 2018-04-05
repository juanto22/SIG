package com.org.gesily.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.org.school.enums.Gender;
import com.org.util.domain.BaseModelEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PERSONA")
@SequenceGenerator(name = "SEQ_PERSONA", sequenceName = "SEQ_PERSONA", allocationSize = 1)
@EqualsAndHashCode(of = { "id" })
@ToString(of = { "id" })
@Getter
@Setter
public class Persona implements BaseModelEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8964052580244424285L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERSONA")
	@Column(nullable = false)
	private Long id;

	private String nombres;

	private String apellidos;

	private int edad;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	private String direccion;

	private String email;

	private String celular;

	private Boolean activo;

//	@ManyToOne
//	@JoinColumns({ @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false) })
//	@NotFound(action = NotFoundAction.IGNORE)
//	private UserTypeEntity userTypeEntity;

	@Column(length = 255, nullable = true)
	private String genero;

	/**
	 * Relations By Enums
	 **/
	public Gender getGender() {
		return Gender.getGender(this.genero);
	}

	public void setGender(Gender gender) {
		this.genero = gender != null ? gender.getCode() : null;
	}

}
