package com.adv.empdetailsms.service.impl;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.adv.empdetailsms.dto.AddressDto;
import com.adv.empdetailsms.dto.BasicDetailsDto;
import com.adv.empdetailsms.dto.EmpEducationalDetailDto;
import com.adv.empdetailsms.entity.AddressEntity;
import com.adv.empdetailsms.entity.BasicDetailsEntity;
import com.adv.empdetailsms.entity.EmpEducationDetailEntity;
import com.adv.empdetailsms.exception.EmployeeDetailsException;
import com.adv.empdetailsms.repository.BasicDetailsRepository;
import com.adv.empdetailsms.repository.EmpEducationDetailRepository;

@RunWith(MockitoJUnitRunner.class)
public class EmpEducationDetailServiceImplTest {

	@InjectMocks
	private EmpEducationDetailServiceImpl empEducationDetailServiceImpl;

	@Mock
	private EmpEducationDetailRepository empEducationDetailRepository;

	@Mock
	private BasicDetailsRepository basicDetailsRepository;

	private List<EmpEducationDetailEntity> empEducationDetailEntityList = new ArrayList<>();

	private List<EmpEducationalDetailDto> empEducationalDetailDtoList = new ArrayList<>();

	private BasicDetailsDto basicDetailsDto = new BasicDetailsDto();

	private BasicDetailsDto basicDetailsDto2 = new BasicDetailsDto();

	private BasicDetailsEntity basicDetailsEntity = new BasicDetailsEntity();

	private BasicDetailsEntity basicDetailsEntity2 = new BasicDetailsEntity();

	private EmpEducationDetailEntity empEducationDetailEntity = new EmpEducationDetailEntity();

	private List<AddressEntity> addressEntityList = new ArrayList<>();

	private List<AddressDto> addressList = new ArrayList<>();

	private EmpEducationalDetailDto empEducationalDetailDto = new EmpEducationalDetailDto();

	private String userId;
	
	private String empId;
	
	private String eduId;

	private void createBasicDetailsDto() {
		basicDetailsDto.setId("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		basicDetailsDto.setFirstName("sathiya");
		basicDetailsDto.setLastName("seelan");
		basicDetailsDto.setEmail("adv@gmail.com");
		basicDetailsDto.setDob(new Date(2003 - 11 - 18));
		basicDetailsDto.setAadharNum("64404440444");
		basicDetailsDto.setBloodGroup("AB +ve");
		basicDetailsDto.setPanNum("AAAA4567R");
		basicDetailsDto.setGender("male");
		basicDetailsDto.setMaritalStatus("Unmarried");
		basicDetailsDto.setMobileNum("7010345678l");
		basicDetailsDto.setAlterMobileNum("9750466550l");
		AddressDto addressDto = new AddressDto();
		addressDto.setDoorNo("89u");
		addressDto.setLandMark("kovil back side");
		addressDto.setStreet("kovil street");
		addressDto.setCity("salem");
		addressDto.setDistrict("salem");
		addressDto.setPincode("789067");
		addressDto.setState("Tamilnadu");
		addressDto.setCountry("India");
		addressList.add(addressDto);
		AddressDto secondAddressDto = new AddressDto();
		secondAddressDto.setDoorNo("89u");
		secondAddressDto.setLandMark("kovil back side");
		secondAddressDto.setStreet("kovil street");
		secondAddressDto.setCity("salem");
		secondAddressDto.setDistrict("salem");
		secondAddressDto.setPincode("789067");
		secondAddressDto.setState("Tamilnadu");
		secondAddressDto.setCountry("India");
		addressList.add(secondAddressDto);
		basicDetailsDto.setAddresses(addressList);
		createBasicDetailsDto2();
	}

	private void createBasicDetailsDto2() {
		basicDetailsDto2.setId("9f36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		basicDetailsDto2.setFirstName("tiger");
		basicDetailsDto2.setLastName("girl");
		basicDetailsDto2.setEmail("tiger@gmail.com");
		basicDetailsDto2.setDob(new Date(2003 - 11 - 18));
		basicDetailsDto2.setAadharNum("64404440555l");
		basicDetailsDto2.setBloodGroup("A +ve");
		basicDetailsDto2.setPanNum("AAAA5678R");
		basicDetailsDto2.setGender("Female");
		basicDetailsDto2.setMaritalStatus("married");
		basicDetailsDto2.setMobileNum("7010245678l");
		basicDetailsDto2.setAlterMobileNum("9751466550l");
		basicDetailsDto2.setAddresses(addressList);
		createBasicDetailsEntity();
	}

	private void createBasicDetailsEntity() {
		basicDetailsEntity.setId("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		basicDetailsEntity.setFirstName("sathiya");
		basicDetailsEntity.setLastName("seelan");
		basicDetailsEntity.setEmail("adv@gmail.com");
		basicDetailsEntity.setDob(new Date(2003 - 11 - 18));
		basicDetailsEntity.setAadharNum("64404440444l");
		basicDetailsEntity.setBloodGroup("AB +ve");
		basicDetailsEntity.setPanNum("AAAA4567R");
		basicDetailsEntity.setPassportNum("AAAA456785626");
		basicDetailsEntity.setGender("male");
		basicDetailsEntity.setMaritalStatus("Unmarried");
		basicDetailsEntity.setMobile("7010345678l");
		basicDetailsEntity.setAlterMobileNum("9750466550l");
		basicDetailsEntity.setDeleted(false);
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setDoorNo("89u");
		addressEntity.setLandMark("kovil back side");
		addressEntity.setStreet("kovil street");
		addressEntity.setCity("salem");
		addressEntity.setDistrict("salem");
		addressEntity.setPincode("789067");
		addressEntity.setState("Tamilnadu");
		addressEntity.setCountry("India");
		addressEntityList.add(addressEntity);
		AddressEntity secondAddressEntity = new AddressEntity();
		secondAddressEntity.setDoorNo("89u");
		secondAddressEntity.setLandMark("kovil back side");
		secondAddressEntity.setStreet("kovil street");
		secondAddressEntity.setCity("salem");
		secondAddressEntity.setDistrict("salem");
		secondAddressEntity.setPincode("789067");
		secondAddressEntity.setState("Tamilnadu");
		secondAddressEntity.setCountry("India");
		addressEntityList.add(secondAddressEntity);
		basicDetailsEntity.setAddressEntities(addressEntityList);
		createBasicDetailsEntity2();
	}

	public void createBasicDetailsEntity2() {
		basicDetailsEntity2.setId("9f36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		basicDetailsEntity2.setFirstName("sathiya");
		basicDetailsEntity2.setLastName("seelan");
		basicDetailsEntity2.setEmail("adv@gmail.com");
		basicDetailsEntity2.setDob(new Date(2003 - 11 - 18));
		basicDetailsEntity2.setAadharNum("64404440444l");
		basicDetailsEntity2.setBloodGroup("AB +ve");
		basicDetailsEntity2.setPanNum("AAAA4567R");
		basicDetailsEntity2.setPassportNum("AAAE456785626");
		basicDetailsEntity2.setGender("male");
		basicDetailsEntity2.setMaritalStatus("Unmarried");
		basicDetailsEntity2.setMobile("7010345678l");
		basicDetailsEntity2.setAlterMobileNum("9750466550l");
		basicDetailsEntity2.setDeleted(false);
		basicDetailsEntity2.setAddressEntities(addressEntityList);
	}

	@Before
	public void run() {
		userId = "gabi";
		createBasicDetailsDto();
		empEducationalDetailDto.setId("9a36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto.setDegreeType("bsc maths");
		empEducationalDetailDto.setCollegeName("SCS College");
		empEducationalDetailDto.setDepartment("maths");
		empEducationalDetailDto.setNameOfUniversity("periyar university");
		empEducationalDetailDto.setEmployeeId("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto.setPassedOutYear(new Date(2000 / 13 / 02));
		empEducationalDetailDtoList.add(empEducationalDetailDto);

		EmpEducationalDetailDto empEducationalDetailDto1 = new EmpEducationalDetailDto();
		empEducationalDetailDto1.setId("9a36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto1.setDegreeType("Msc maths");
		empEducationalDetailDto1.setCollegeName("SKS College");
		empEducationalDetailDto1.setDepartment("physics");
		empEducationalDetailDto1.setNameOfUniversity("anna university");
		empEducationalDetailDto1.setEmployeeId("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto1.setPassedOutYear(new Date(2000 / 13 / 02));
		empEducationalDetailDtoList.add(empEducationalDetailDto1);

		empEducationDetailEntity.setId("9a36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationDetailEntity.setDegreeType("bsc archeology");
		empEducationDetailEntity.setCollegeName("sacred jesus college");
		empEducationDetailEntity.setDepartment("archeology");
		empEducationDetailEntity.setNameOfUniversity("periyar univ");
		empEducationDetailEntity.setEmployeeId("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationDetailEntity.setPassedOutYear(new Date(2000 / 12 / 02));
		empEducationDetailEntityList.add(empEducationDetailEntity);

		EmpEducationDetailEntity empEducationDetailEntity2 = new EmpEducationDetailEntity();
		empEducationDetailEntity2.setId("9a36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationDetailEntity2.setDegreeType("Msc maths");
		empEducationDetailEntity2.setCollegeName("SKS College");
		empEducationDetailEntity2.setDepartment("physics");
		empEducationDetailEntity2.setNameOfUniversity("anna university");
		empEducationDetailEntity2.setEmployeeId("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationDetailEntity2.setPassedOutYear(new Date(2000 / 13 / 02));
		empEducationDetailEntityList.add(empEducationDetailEntity2);
		
		empId="9e36f3d5-f3cf-4734-a47a-07c7de32b4b1";
		eduId="9a36f3d5-f3cf-4734-a47a-07c7de32b4b1";
	}

	@Test
	public void testgetAllCollegeName() {
		when(empEducationDetailRepository.findByIsDeleted(false))
					.thenReturn(empEducationDetailEntityList);
		empEducationDetailServiceImpl.getEmpEduDetails();
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdParticularEmpEduDetailsCase2() {
		
		empEducationDetailServiceImpl
					.updParticularEmpEduDetails(null, empEducationalDetailDtoList, userId);
	}

	@Test
	public void testUpdParticularEmpEduDetailsCase3() {
		when(empEducationDetailRepository
					.findByIsDeletedAndEmployeeId(Mockito.anyBoolean(), Mockito.any()))
					.thenReturn(empEducationDetailEntityList);
		when(empEducationDetailRepository.saveAndFlush(Mockito.any()))
					.thenReturn(new EmpEducationDetailEntity());
		empEducationalDetailDtoList.get(0).setId(null);
		empEducationDetailServiceImpl
					.updParticularEmpEduDetails(empId, empEducationalDetailDtoList, userId);
	}

	@Test
	public void testUpdParticularEmpEduDetailsCase4() {
		when(empEducationDetailRepository
					.findByIsDeletedAndEmployeeId(Mockito.anyBoolean(), Mockito.any()))
					.thenReturn(empEducationDetailEntityList);
//		when(empEducationDetailRepository.saveAndFlush(Mockito.any())).thenReturn(null);
		empEducationDetailServiceImpl
					.updParticularEmpEduDetails(empId, empEducationalDetailDtoList, userId);

	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdParticularEmpEduDetailsCase5() {
		empEducationDetailServiceImpl
					.updParticularEmpEduDetails(null, new ArrayList<EmpEducationalDetailDto>(), userId);

	}

	@Test
	public void testUpdParticularEmpEduDetailsCase7() {
		when(empEducationDetailRepository
					.findByIsDeletedAndEmployeeId(Mockito.anyBoolean(), Mockito.any()))
					.thenReturn(new ArrayList<EmpEducationDetailEntity>());
		when(empEducationDetailRepository.saveAndFlush(Mockito.any())).thenReturn(null);
		empEducationalDetailDtoList.get(0).setId(null);
		empEducationDetailServiceImpl
					.updParticularEmpEduDetails(empId, empEducationalDetailDtoList, userId);
	}

	@Test
	public void testUpdParticularEmpEduDetailsCase6() {
		empEducationalDetailDto.setId("9a36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationDetailEntity.setId("9b36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationDetailEntityList.add(empEducationDetailEntity);
		empEducationalDetailDtoList.add(empEducationalDetailDto);
		when(empEducationDetailRepository
					.findByIsDeletedAndEmployeeId(Mockito.anyBoolean(), Mockito.any()))
					.thenReturn(empEducationDetailEntityList);
//		when(empEducationDetailRepository.saveAndFlush(Mockito.any()))
//					.thenReturn(new EmpEducationDetailEntity());
		empEducationDetailServiceImpl
					.updParticularEmpEduDetails(empId, empEducationalDetailDtoList, userId);

	}
	@Test
	public void testUpdParticularEmpEduDetailsCase8() {
		empEducationalDetailDto.setId("9a36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto.setDegreeType("bsc maths");
		empEducationalDetailDto.setCollegeName("SCS College");
		empEducationalDetailDto.setDepartment("maths");
		empEducationalDetailDto.setNameOfUniversity("periyar university");
		empEducationalDetailDto.setEmployeeId("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto.setPassedOutYear(new Date(2000 / 13 / 02));
		empEducationDetailEntity.setId("9a36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationDetailEntity.setDegreeType("bsc maths");
		empEducationDetailEntity.setCollegeName("SCS College");
		empEducationDetailEntity.setDepartment("maths");
		empEducationDetailEntity.setNameOfUniversity("periyar university");
		empEducationDetailEntity.setEmployeeId("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationDetailEntity.setPassedOutYear(new Date(2000 / 13 / 02));
		empEducationDetailEntityList.add(empEducationDetailEntity);
		empEducationalDetailDtoList.add(empEducationalDetailDto);
		when(empEducationDetailRepository
					.findByIsDeletedAndEmployeeId(Mockito.anyBoolean(), Mockito.any()))
					.thenReturn(empEducationDetailEntityList);
//		when(empEducationDetailRepository.saveAndFlush(Mockito.any()))
//					.thenReturn(new EmpEducationDetailEntity());
		empEducationDetailServiceImpl
					.updParticularEmpEduDetails(empId, empEducationalDetailDtoList, userId);

	}
	@Test
	public void testDeleteEmpEduDetails() {
		when(empEducationDetailRepository
					.findByIsDeletedAndEmployeeId(Mockito.anyBoolean(), Mockito.any()))
					.thenReturn(empEducationDetailEntityList);
		when(empEducationDetailRepository.saveAndFlush(Mockito.any()))
					.thenReturn(new EmpEducationDetailEntity());
		empEducationalDetailDtoList.get(0).setDeleted(true);
		empEducationDetailServiceImpl.delEmpEduDetails(empId, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testDeleteEmpEduDetailsCase1() {
		empEducationDetailServiceImpl.delEmpEduDetails(null, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testDeleteEmpEduDetailsCase2() {
		when(empEducationDetailRepository
					.findByIsDeletedAndEmployeeId(Mockito.anyBoolean(), Mockito.any()))
					.thenReturn(new ArrayList<EmpEducationDetailEntity>());
		empEducationDetailServiceImpl.delEmpEduDetails(empId, userId);
	}

	@Test
	public void testDeleteEmpEduDetailsCase3() {
		when(empEducationDetailRepository
					.findByIsDeletedAndEmployeeId(Mockito.anyBoolean(), Mockito.any()))
					.thenReturn(empEducationDetailEntityList);
		empEducationDetailEntity.setDeleted(true);
		when(empEducationDetailRepository.saveAndFlush(Mockito.any()))
					.thenReturn(empEducationDetailEntity);
		empEducationDetailServiceImpl.delEmpEduDetails(empId, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testRegisterEmpEduDetailsCase1() {
		empEducationDetailServiceImpl
					.registerEmpEduDetails(empId, new ArrayList<EmpEducationalDetailDto>(), userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testRegisterEmpEduDetailsCase2() {
		empEducationDetailServiceImpl
					.registerEmpEduDetails(null, new ArrayList<EmpEducationalDetailDto>(), userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testRegisterEmpEduDetailsCase5() {
		when(basicDetailsRepository.findByIdAndIsDeleted(empId, false)).thenReturn(null);
		empEducationDetailServiceImpl
					.registerEmpEduDetails(empId, empEducationalDetailDtoList, userId);
	}

	@Test
	public void testRegisterEmpEduDetailsCase3() {
		when(basicDetailsRepository.findByIdAndIsDeleted(empId, false))
					.thenReturn(basicDetailsEntity);
		empEducationDetailServiceImpl
					.registerEmpEduDetails(empId, empEducationalDetailDtoList, userId);
	}

	@Test
	public void testRegisterEmpEduDetailsCase4() {
		when(basicDetailsRepository.findByIdAndIsDeleted(Mockito.any(), Mockito.anyBoolean()))
					.thenReturn(basicDetailsEntity);
		when(empEducationDetailRepository.saveAndFlush(Mockito.any()))
					.thenReturn(empEducationDetailEntity);
		empEducationDetailServiceImpl
					.registerEmpEduDetails(empId, empEducationalDetailDtoList, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testRegisterEmpEduDetailsCase6() {
		when(basicDetailsRepository.findByIdAndIsDeleted(Mockito.any(), Mockito.anyBoolean()))
					.thenReturn(basicDetailsEntity);
		when(empEducationDetailRepository.saveAndFlush(Mockito.any()))
					.thenReturn(empEducationDetailEntity);
		empEducationalDetailDto = null;
		empEducationalDetailDtoList.add(empEducationalDetailDto);
		empEducationDetailServiceImpl
					.registerEmpEduDetails(empId, empEducationalDetailDtoList, userId);

	}

	@Test
	public void testDeleteParticularEmpEduDetails() {
		when(empEducationDetailRepository
					.findByIsDeletedAndId(Mockito.anyBoolean(), Mockito.any()))
					.thenReturn(new EmpEducationDetailEntity());
		when(empEducationDetailRepository.saveAndFlush(Mockito.any()))
					.thenReturn(new EmpEducationDetailEntity());
		empEducationalDetailDtoList.get(0).setDeleted(true);
		empEducationDetailServiceImpl.delParticularEduDetails(eduId, userId);
	}

	@Test
	public void testDeleteParticularEmpEduDetailsCase3() {
		when(empEducationDetailRepository
					.findByIsDeletedAndId(Mockito.anyBoolean(), Mockito.any()))
					.thenReturn(new EmpEducationDetailEntity());
		empEducationDetailEntity.setDeleted(true);
		when(empEducationDetailRepository.saveAndFlush(Mockito.any()))
					.thenReturn(empEducationDetailEntity);
		empEducationalDetailDtoList.get(0).setDeleted(true);
		empEducationDetailServiceImpl.delParticularEduDetails(eduId, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testDeleteParticularEmpEduDetailsCase1() {
		empEducationDetailServiceImpl.delParticularEduDetails(null, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testDeleteParticularEmpEduDetailsCase2() {
		when(empEducationDetailRepository
					.findByIsDeletedAndId(Mockito.anyBoolean(), Mockito.any()))
					.thenReturn(null);
		empEducationDetailServiceImpl.delParticularEduDetails(eduId, userId);
	}

	@Test
	public void testGetParticularEmpEduDetails() {
		when(empEducationDetailRepository
					.findByIsDeletedAndEmployeeId(Mockito.anyBoolean(), Mockito.any()))
					.thenReturn(empEducationDetailEntityList);
		empEducationDetailServiceImpl.getParticularEmpEduDetails(empId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testGetParticularEmpEduDetailsCase1() {
		empEducationDetailServiceImpl.getParticularEmpEduDetails(null);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testGetParticularEmpEduDetailsCase2() {
		when(empEducationDetailRepository
					.findByIsDeletedAndEmployeeId(Mockito.anyBoolean(), Mockito.any()))
					.thenReturn(new ArrayList<EmpEducationDetailEntity>());
		empEducationDetailServiceImpl.getParticularEmpEduDetails(empId);
	}
}
