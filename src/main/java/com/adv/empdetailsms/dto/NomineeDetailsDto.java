package com.adv.empdetailsms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
@NoArgsConstructor
public class NomineeDetailsDto {

	private String id;

	private String name;

	private String relation;

	private int percentage;
}