package com.adv.empdetailsms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

	private String candidateId;
	
	private String password;
}
