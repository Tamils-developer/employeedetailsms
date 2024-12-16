package com.adv.empdetailsms.service;

import java.util.List;

import com.adv.empdetailsms.dto.EmpBankDetailsDto;

public interface EmpBankDetailsService {

	public void registerEmpBankDetails(String empId, EmpBankDetailsDto empBankDetailsDto,
				String userId);

	public List<EmpBankDetailsDto> getAllBankDetails();

	public void updateEmpBankDetails(String empId, EmpBankDetailsDto empBankDetailsDto,
				String userId);

	public void deleteEmpBankDetailsWithBankDetailId(String bankDetailId, String userId);

	public EmpBankDetailsDto getParticularEmpBankDetail(String empId);

	public void deleteEmpBankDetailsWithEmpId(String empId, String userId);
}
