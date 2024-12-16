package com.adv.empdetailsms.service;

import java.util.List;
import java.util.Map;

import com.adv.empdetailsms.dto.BankDto;
import com.adv.empdetailsms.dto.DegreeTypeDto;
import com.adv.empdetailsms.dto.DepartmentDto;
import com.adv.empdetailsms.dto.EmpPersonalDetailsDto;
import com.adv.empdetailsms.dto.RoleDto;

public interface DropdownService {

	public List<RoleDto> getDropdownDetails();

	public List<DegreeTypeDto> getAllDegreeTypes();

	public List<DepartmentDto> getAllDepartment();

	public EmpPersonalDetailsDto getAllPersonalDetails();

	public List<BankDto> getAllBankNames();

	public Map<String, Map<String, List<String>>> getStateAndCountry();

}
