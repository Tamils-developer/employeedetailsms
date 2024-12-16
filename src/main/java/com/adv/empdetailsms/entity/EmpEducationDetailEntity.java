package com.adv.empdetailsms.entity;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Table(name = "emp_educational_details")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmpEducationDetailEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(name = "degree_type")
	private String degreeType;

	@Column(name = "college_name")
	private String collegeName;

	@Column(name = "university")
	private String nameOfUniversity;

	@Column(name = "passedout_year")
	private Date passedOutYear;

	@Column(name = "department")
	private String department;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "emp_basic_details_id")
	private String employeeId;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "updated_date")
	private Timestamp updatedDate;
}