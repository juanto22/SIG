package com.org.school.model;

import java.util.Date;

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
@Table(name = "STUDENTCOURSEATTENDANCE")
@SequenceGenerator(name = "SEQ_STUDENTCOURSEATTENDANCE", sequenceName = "SEQ_STUDENTCOURSEATTENDANCE", allocationSize = 1)
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
@Getter
@Setter
public class StudentCourseAttendance implements BaseModelEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5226046395458064122L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STUDENTCOURSEATTENDANCE")
	@Column(nullable = false)
	private Long id;
	
	private Date dateAttendance;
	
	private Boolean attendance;
	
	@ManyToOne
	@JoinColumn(name = "STUDENTSPERCOURSE_ID")
	private StudentsPerCourse studentsPerCourse;

}
