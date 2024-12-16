package com.adv.empdetailsms.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
@NoArgsConstructor
public class AddressDto {

	private String id;

	private String doorNo;

	private String street;

	private String pincode;

	private String landMark;

	private String city;

	private String district;

	private String state;

	private String country;

	private String type;

	private boolean sameAsCommu;

}