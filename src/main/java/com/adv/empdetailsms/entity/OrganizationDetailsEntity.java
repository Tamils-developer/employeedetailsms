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
@Table(name = "emp_experience_details")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrganizationDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(name = "name_of_organization")
	private String nameOfOrganization;

	@Column(name = "from_date")
	private Date fromDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "designation")
	private String role;

	@Column(name = "total_number_of_years_exp")
	private int totalYearsOfExperience;

	@Column(name = "ESI_number")
	private String esiNumber;

	@Column(name = "emp_basic_details_id")
	private String employeeId;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@Column(name = "updated_by")
	private String updatedBy;
}