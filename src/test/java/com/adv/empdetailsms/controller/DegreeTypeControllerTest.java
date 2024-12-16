package com.adv.empdetailsms.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;

import com.adv.empdetailsms.dto.BankDto;
import com.adv.empdetailsms.dto.DegreeTypeDto;
import com.adv.empdetailsms.dto.DepartmentDto;
import com.adv.empdetailsms.service.DropdownService;

@RunWith(MockitoJUnitRunner.class)
public class DegreeTypeControllerTest {
	
	@InjectMocks
	private DropdownController degreeTypeController;
	
	@Mock
	private DropdownService dropdownService;
	
	@Mock
	private HttpHeaders headers;
	
	private String id;
	
	private List<DegreeTypeDto> degreeTypeDtoList =new ArrayList<DegreeTypeDto>();
	
	private List<BankDto> bankDtoList =new ArrayList<BankDto>();
	
	private List<DepartmentDto> departmentDtoList =new ArrayList<DepartmentDto>();
	
	@Before
	public void run() {
		id = "9x36f3d5-f3cf-4734-a47a-07c7de32b4b1";
	}
	
	@Test
	public void testGetDegreeTypes() {
		DegreeTypeDto degreeTypeDto = new DegreeTypeDto();
		degreeTypeDto.setId(id);
		degreeTypeDto.setDegreeType("bsc");
		DegreeTypeDto degreeTypeDto2 = new DegreeTypeDto();
		degreeTypeDto.setId("9x36f3d5-f3cf-4734-a47a-07c7de32b4b2");
		degreeTypeDto.setDegreeType("bsc");
		degreeTypeDtoList.add(degreeTypeDto2);
		degreeTypeDtoList.add(degreeTypeDto);
		when(dropdownService.getAllDegreeTypes()).thenReturn(degreeTypeDtoList);
		degreeTypeController.getAllDegreeTypes();
	}
	
	@Test
	public void testGetDepartmentTypes() {
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setId(id);
		departmentDto.setDepartment("maths");
		DepartmentDto departmentDto2 = new DepartmentDto();
		departmentDto2.setId("9x36f3d5-f3cf-4734-a47a-07c7de32b4b2");
		departmentDto2.setDepartment("science");
		departmentDtoList.add(departmentDto);
		departmentDtoList.add(departmentDto2);
		when(dropdownService.getAllDepartment()).thenReturn(departmentDtoList);
		degreeTypeController.getAllDepartmentTypes();
	}
	
	@Test
	public void testGetBankNames() {
		BankDto bankDto = new BankDto();
		bankDto.setId(id);
		bankDto.setBankName("sbi");
		BankDto bankDto2 = new BankDto();
		bankDto2.setId("9x36f3d5-f3cf-4734-a47a-07c7de32b4b2");
		bankDto2.setBankName("pnb");
		bankDtoList.add(bankDto2);
		bankDtoList.add(bankDto);
		when(dropdownService.getAllBankNames()).thenReturn(bankDtoList);
		degreeTypeController.getAllBankNames();
	}
}
