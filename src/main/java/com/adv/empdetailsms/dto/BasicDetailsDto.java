package com.adv.empdetailsms.dto;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
@NoArgsConstructor
public class BasicDetailsDto {

	private String id;

	private String firstName;

	private String lastName;

	private String email;

	private String mobileNum;

	private String alterMobileNum;

	private Date dob;

	private String gender;

	private String aadharNum;

	private String panNum;
	
	private String passportNum;

	private String bloodGroup;

	private String maritalStatus;

	private List<AddressDto> addresses;

	private String uanNumber;

}
