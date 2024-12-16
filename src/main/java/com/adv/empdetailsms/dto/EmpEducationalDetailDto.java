package com.adv.empdetailsms.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
@NoArgsConstructor
public class EmpEducationalDetailDto {

	private String id;

	private String degreeType;

	private String collegeName;

	private String nameOfUniversity;

	private Date passedOutYear;

	private String department;

	private boolean isDeleted;

	private String employeeId;
}