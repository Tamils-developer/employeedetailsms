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
public class FamilyDetailsDto {

	private String id;

	private String name;

	private Date dob;

	private String aadhar;

	private String relation;
	
	private String mobile;

	private boolean isNominee;

	private int percentage;
}