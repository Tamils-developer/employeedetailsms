package com.adv.empdetailsms.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmpPersonalDetailsDto {

	List<GenderDto> genderList;

	List<BloodGroupDto> bloodGroupList;

	List<MaritalStatusDto> maritalStatusList;
}
