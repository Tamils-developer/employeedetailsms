package com.adv.empdetailsms.service;

import java.util.List;

import com.adv.empdetailsms.dto.EmpEducationalDetailDto;

public interface EmpEducationDetailService {

	public void registerEmpEduDetails(String empId,
				List<EmpEducationalDetailDto> empEducationalDetailDto, String userId);

	public List<EmpEducationalDetailDto> getEmpEduDetails();

	public void delEmpEduDetails(String empId, String userId);

	public void updParticularEmpEduDetails(String empId,
				List<EmpEducationalDetailDto> empEducationalDetailDto, String userId);

	public void delParticularEduDetails(String eduId, String userId);

	public List<EmpEducationalDetailDto> getParticularEmpEduDetails(String empId);

}
