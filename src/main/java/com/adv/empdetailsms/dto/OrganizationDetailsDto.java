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
public class OrganizationDetailsDto {

	private String id;

	private String nameOfOrganization;

	private Date fromDate;

	private Date endDate;

	private int totalYearsOfExperience;

	private String role;

	private String esiNumber;

	private boolean isDeleted;
}