package com.adv.empdetailsms.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
@NoArgsConstructor
public class NomineeDto {

	private String uanNumber;

	private List<NomineeDetailsDto> nomineeDetailsDtoList;
}