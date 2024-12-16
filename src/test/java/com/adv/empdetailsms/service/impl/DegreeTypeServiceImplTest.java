package com.adv.empdetailsms.service.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.adv.empdetailsms.dto.BankDto;
import com.adv.empdetailsms.dto.DegreeTypeDto;
import com.adv.empdetailsms.dto.DepartmentDto;
import com.adv.empdetailsms.entity.BankEntity;
import com.adv.empdetailsms.entity.DegreeTypeEntity;
import com.adv.empdetailsms.entity.DepartmentEntity;
import com.adv.empdetailsms.repository.BankNameRepository;
import com.adv.empdetailsms.repository.DegreeTypeRepository;
import com.adv.empdetailsms.repository.DepartmentRepository;

@RunWith(MockitoJUnitRunner.class)
public class DegreeTypeServiceImplTest {

	@InjectMocks
	DropdownServiceImpl degreeTypeServiceImpl = new DropdownServiceImpl();

	@Mock
	private DegreeTypeRepository degreeTypeRepository;
	
	@Mock
	private DepartmentRepository departmentRepository;
	
	@Mock
	private BankNameRepository bankNameRepository;

	private List<DegreeTypeDto> degreeTypeDtoList = new ArrayList<DegreeTypeDto>();
	
	private List<BankDto> bankDtoList = new ArrayList<BankDto>();
	
	private List<BankEntity> bankEntityList = new ArrayList<BankEntity>();

	private List<DepartmentDto> departmentDtoList = new ArrayList<DepartmentDto>();
	
	private List<DegreeTypeEntity> degreeTypeEntityList = new ArrayList<DegreeTypeEntity>();
	
	private List<DepartmentEntity> departmentEntityList = new ArrayList<DepartmentEntity>();
	
	
	
	private String id;
	

	@Before
	public void run() {
		DegreeTypeDto degreeTypeDto = new DegreeTypeDto();
		degreeTypeDto.setId(id);
		degreeTypeDto.setDegreeType("bsc");
		DegreeTypeDto degreeTypeDto2 = new DegreeTypeDto();
		degreeTypeDto.setId("9x36f3d5-f3cf-4734-a47a-07c7de32b4b2");
		degreeTypeDto.setDegreeType("bsc");
		degreeTypeDtoList.add(degreeTypeDto2);
		degreeTypeDtoList.add(degreeTypeDto);
		
		
		DegreeTypeEntity degreeTypeEntity = new DegreeTypeEntity();
		degreeTypeEntity.setId(id);
		degreeTypeEntity.setDegreeType("bsc");
		DegreeTypeEntity degreeTypeEntity2 = new DegreeTypeEntity();
		degreeTypeEntity2.setId("9x36f3d5-f3cf-4734-a47a-07c7de32b4b2");
		degreeTypeEntity2.setDegreeType("bsc");
		degreeTypeEntityList.add(degreeTypeEntity);
		degreeTypeEntityList.add(degreeTypeEntity2);
		
		
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setId(id);
		departmentDto.setDepartment("bsc");
		DepartmentDto departmentDto2 = new DepartmentDto();
		departmentDto2.setId("9x36f3d5-f3cf-4734-a47a-07c7de32b4b2");
		departmentDto2.setDepartment("bsc");
		departmentDtoList.add(departmentDto);
		departmentDtoList.add(departmentDto2);
		
		
		DepartmentEntity departmentEntity = new DepartmentEntity();
		departmentEntity.setId(id);
		departmentEntity.setDepartment("bsc");
		departmentEntity.setIsDeleted(false);
		DepartmentEntity departmentEntity2 = new DepartmentEntity();
		departmentEntity2.setId("9x36f3d5-f3cf-4734-a47a-07c7de32b4b2");
		departmentEntity2.setDepartment("bsc");
		departmentEntity2.setIsDeleted(false);
		departmentEntityList.add(departmentEntity2);
		departmentEntityList.add(departmentEntity);
		
		BankDto bankDto = new BankDto();
		bankDto.setId(id);
		bankDto.setBankName("sbi");
		BankDto bankDto2 = new BankDto();
		bankDto2.setId("9x36f3d5-f3cf-4734-a47a-07c7de32b4b2");
		bankDto2.setBankName("pyb");
		bankDtoList.add(bankDto);
		bankDtoList.add(bankDto2);
		
		
		BankEntity bankEntity = new BankEntity();
		bankEntity.setId(id);
		bankEntity.setBankName("sbi");
		departmentEntity.setIsDeleted(false);
		BankEntity bankEntity2 = new BankEntity();
		bankEntity2.setId("9x36f3d5-f3cf-4734-a47a-07c7de32b4b2");
		bankEntity2.setBankName("pyb");
		bankEntity2.setIsDeleted(false);
		bankEntityList.add(bankEntity2);
		bankEntityList.add(bankEntity);
	}

	@Test
	public void testGetAllDegreeTypes() {
		when(degreeTypeRepository.findByIsDeleted(false)).thenReturn(degreeTypeEntityList);
		degreeTypeServiceImpl.getAllDegreeTypes();
	}

	@Test
	public void testGetAllDepartmentTypes() {
		when(departmentRepository.findByIsDeleted(false)).thenReturn(departmentEntityList);
		degreeTypeServiceImpl.getAllDepartment();
	}
	
	@Test
	public void testGetAllBankNames() {
		when(bankNameRepository.findByIsDeleted(false)).thenReturn(bankEntityList);
		degreeTypeServiceImpl.getAllBankNames();
	}
}
