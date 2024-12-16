package com.adv.empdetailsms.service;

import java.util.List;

import com.adv.empdetailsms.dto.DropDownDto;
import com.adv.empdetailsms.dto.NomineeDto;

public interface NomineeDetailsService {

	public NomineeDto getNomineeDetails(String employeeId);

	public void updateNomineeDetails(NomineeDto detailsDtos, String employeeId, String userId);

	public void deleteNomineeDetails(String employeeId, String userId);

	public List<DropDownDto> getDropDownDetails(String employeeId);

}
