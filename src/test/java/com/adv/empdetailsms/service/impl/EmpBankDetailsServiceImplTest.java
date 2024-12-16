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
import com.adv.empdetailsms.dto.EmpBankDetailsDto;
import com.adv.empdetailsms.entity.AddressEntity;
import com.adv.empdetailsms.entity.BasicDetailsEntity;
import com.adv.empdetailsms.entity.EmpBankDetailsEntity;
import com.adv.empdetailsms.exception.EmployeeDetailsException;
import com.adv.empdetailsms.repository.BasicDetailsRepository;
import com.adv.empdetailsms.repository.EmpBankDetailRepository;

@RunWith(MockitoJUnitRunner.class)
public class EmpBankDetailsServiceImplTest {

	@InjectMocks
	EmpBankDetailsServiceImpl empBankDetailsServiceImpl = new EmpBankDetailsServiceImpl();

	@Mock
	private EmpBankDetailRepository empBankDetailRepository;

	@Mock
	private BasicDetailsRepository basicDetailsRepository;

	private EmpBankDetailsDto empBankDetailsDto = new EmpBankDetailsDto();

	private EmpBankDetailsEntity empBankDetailsEntity = new EmpBankDetailsEntity();
	
	private EmpBankDetailsDto empBankDetailsDto1 = new EmpBankDetailsDto();

	private EmpBankDetailsEntity empBankDetailsEntity1 = new EmpBankDetailsEntity();
	
	private BasicDetailsEntity basicDetailsEntity = new BasicDetailsEntity();

	private BasicDetailsDto basicDetailsDto = new BasicDetailsDto();

	private List<AddressEntity> addressEntityList = new ArrayList<>();

	private List<AddressDto> addressDtoList = new ArrayList<>();

	private String userId;

	private String empId;

	private String bankId;

	@Before
	public void before() {
		
		empId="9x36f3d5-f3cf-4734-a47a-07c7de32b4b1";
		bankId="9a36f3d5-f3cf-4734-a47a-07c7de32b4b1";
		
		userId = "gabi";
		empBankDetailsDto.setId("9x36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empBankDetailsDto.setEmployeeId("9a36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empBankDetailsDto.setAccountHolderName("name1");
		empBankDetailsDto.setAccountNumber("456723143jo");
		empBankDetailsDto.setBankName("Bank 1");
		empBankDetailsDto.setIfscCode("IDBI657483");
		empBankDetailsDto.setBranchName("Branch1");

		empBankDetailsEntity.setId("9x36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empBankDetailsEntity.setEmployeeId("9a36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empBankDetailsEntity.setAccountHolderName("name2");
		empBankDetailsEntity.setAccountNumber("456723143ko");
		empBankDetailsEntity.setBankName("Bank 2");
		empBankDetailsEntity.setIfscCode("IDCI657483");
		empBankDetailsEntity.setBranchName("Branch2");
		
		
		
		
		empBankDetailsDto1.setId("9x36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empBankDetailsDto1.setEmployeeId("9a36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empBankDetailsDto1.setAccountHolderName("name1");
		empBankDetailsDto1.setAccountNumber("456723143jo");
		empBankDetailsDto1.setBankName("Bank 1");
		empBankDetailsDto1.setIfscCode("IDBI657483");
		empBankDetailsDto1.setBranchName("Branch1");

		empBankDetailsEntity1.setId("9x36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empBankDetailsEntity1.setEmployeeId("9a36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empBankDetailsEntity1.setAccountHolderName("name1");
		empBankDetailsEntity1.setAccountNumber("456723143jo");
		empBankDetailsEntity1.setBankName("Bank 1");
		empBankDetailsEntity1.setIfscCode("IDBI657483");
		empBankDetailsEntity1.setBranchName("Branch1");

		basicDetailsEntity.setId("9a36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		basicDetailsEntity.setFirstName("Joey");
		basicDetailsEntity.setLastName("Chandler");
		basicDetailsEntity.setEmail("joey@gmail.com");
		basicDetailsEntity.setDob(new Date(2007 - 03 - 28));
		basicDetailsEntity.setAadharNum("66557778888");
		basicDetailsEntity.setBloodGroup("O +ve");
		basicDetailsEntity.setPanNum("VVYF6777");
		basicDetailsEntity.setPassportNum("AAAA456785626");
		basicDetailsEntity.setGender("male");
		basicDetailsEntity.setMaritalStatus("Unmarried");
		basicDetailsEntity.setMobile("887766655441");
		basicDetailsEntity.setAlterMobileNum("7766788999l");
		basicDetailsEntity.setDeleted(false);
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setDoorNo("20A");
		addressEntity.setLandMark("near church");
		addressEntity.setStreet("Gandhi nagar");
		addressEntity.setCity("Tirupattur");
		addressEntity.setDistrict("Tiupattur");
		addressEntity.setPincode("635601");
		addressEntity.setState("Tamilnadu");
		addressEntity.setCountry("India");
		addressEntityList.add(addressEntity);
		AddressEntity secondAddressEntity = new AddressEntity();
		secondAddressEntity.setDoorNo("20A");
		secondAddressEntity.setLandMark("near church");
		secondAddressEntity.setStreet("Gandhi nagar");
		secondAddressEntity.setCity("Tiupattur");
		secondAddressEntity.setDistrict("Tiupattur");
		secondAddressEntity.setPincode("635601");
		secondAddressEntity.setState("Tamilnadu");
		secondAddressEntity.setCountry("India");
		addressEntityList.add(secondAddressEntity);
		basicDetailsEntity.setAddressEntities(addressEntityList);

		basicDetailsDto.setId("9a36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		basicDetailsDto.setFirstName("Joey");
		basicDetailsDto.setLastName("Chandler");
		basicDetailsDto.setEmail("joey@gmail.com");
		basicDetailsDto.setDob(new Date(2007 - 03 - 28));
		basicDetailsDto.setAadharNum("66557778888l");
		basicDetailsDto.setBloodGroup("O +ve");
		basicDetailsDto.setPanNum("VVYF6777");
		basicDetailsDto.setPassportNum("AAAD456785626");
		basicDetailsDto.setGender("male");
		basicDetailsDto.setMaritalStatus("Unmarried");
		basicDetailsDto.setMobileNum("88776665544l");
		basicDetailsDto.setAlterMobileNum("7766788999l");
		AddressDto addressDto = new AddressDto();
		addressDto.setDoorNo("20A");
		addressDto.setLandMark("near church");
		addressDto.setStreet("Gandhi nagar");
		addressDto.setCity("Tirupattur");
		addressDto.setDistrict("Tiupattur");
		addressDto.setPincode("635601");
		addressDto.setState("Tamilnadu");
		addressDto.setCountry("India");
		addressDtoList.add(addressDto);
		AddressDto addressDto2 = new AddressDto();
		addressDto2.setDoorNo("20A");
		addressDto2.setLandMark("near church");
		addressDto2.setStreet("Gandhi nagar");
		addressDto2.setCity("Tiupattur");
		addressDto2.setDistrict("Tiupattur");
		addressDto2.setPincode("635601");
		addressDto2.setState("Tamilnadu");
		addressDto2.setCountry("India");
		addressDtoList.add(addressDto2);
		basicDetailsDto.setAddresses(addressDtoList);

	}

	@Test(expected = EmployeeDetailsException.class)
	public void testRegisterEmpBankDetailsCase1() {
		empBankDetailsServiceImpl.registerEmpBankDetails(empId, new EmpBankDetailsDto(), userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testRegisterEmpBankDetailsCase2() {
		empBankDetailsServiceImpl.registerEmpBankDetails(null, new EmpBankDetailsDto(), userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testRegisterEmpBankDetailsCase3() {
		when(basicDetailsRepository.findByIdAndIsDeleted(empId, false))
					.thenReturn(basicDetailsEntity);
		empBankDetailsServiceImpl.registerEmpBankDetails(empId, empBankDetailsDto, userId);
	}

	@Test
	public void testRegisterEmpBankDetailsCase4() {
		when(basicDetailsRepository.findByIdAndIsDeleted(Mockito.any(), Mockito.anyBoolean()))
					.thenReturn(basicDetailsEntity);
		when(empBankDetailRepository.saveAndFlush(Mockito.any())).thenReturn(empBankDetailsEntity);
		empBankDetailsServiceImpl.registerEmpBankDetails(empId, empBankDetailsDto, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testRegisterEmpBankDetailsCase5() {
		when(basicDetailsRepository.findByIdAndIsDeleted(empId, false)).thenReturn(null);
		empBankDetailsServiceImpl.registerEmpBankDetails(empId, empBankDetailsDto, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testRegisterEmpBankDetailsCase6() {
		empBankDetailsDto = null;
		empBankDetailsServiceImpl.registerEmpBankDetails(empId, empBankDetailsDto, userId);
	}

	@Test
	public void testGetAllBankDetails() {
		List<EmpBankDetailsEntity> bankDetailsEntitiesList = new ArrayList<>();
		bankDetailsEntitiesList.add(empBankDetailsEntity);
		when(empBankDetailRepository.findByIsDeleted(false)).thenReturn(bankDetailsEntitiesList);
		empBankDetailsServiceImpl.getAllBankDetails();
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateEmpBankDetailsCase1() {
		empBankDetailsServiceImpl.updateEmpBankDetails(null, empBankDetailsDto, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateEmpBankDetailsCase2() {
		EmpBankDetailsDto empBankDetailsDto1 = new EmpBankDetailsDto();
		empBankDetailsDto1.setId(bankId);
		empBankDetailsDto1.setEmployeeId(empId);
		empBankDetailsDto1.setAccountHolderName("Jonny");
		empBankDetailsDto1.setAccountNumber("4567843143jo");
		empBankDetailsDto1.setBankName("HDFC Bank");
		empBankDetailsDto1.setIfscCode("hdfc657483");
		empBankDetailsDto1.setBranchName("pudupet");
		when(empBankDetailRepository
					.findByEmployeeIdAndIsDeleted(Mockito.any(), Mockito.anyBoolean()))
					.thenReturn(empBankDetailsEntity);
//		when(empBankDetailRepository.saveAndFlush(Mockito.any())).thenReturn(empBankDetailsEntity);
		empBankDetailsServiceImpl.updateEmpBankDetails(empId, empBankDetailsDto1, userId);
	}

	@Test
	public void testUpdateEmpBankDetailsCase3() {
		when(empBankDetailRepository
					.findByEmployeeIdAndIsDeleted(Mockito.any(), Mockito.anyBoolean()))
					.thenReturn(empBankDetailsEntity1);
		when(empBankDetailRepository.saveAndFlush(Mockito.any())).thenReturn(null);
		empBankDetailsServiceImpl.updateEmpBankDetails(empId, empBankDetailsDto1, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateEmpBankDetailsCase4() {
		empBankDetailsServiceImpl.updateEmpBankDetails(null, new EmpBankDetailsDto(), userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateEmpBankDetailsCase7() {
		empBankDetailsServiceImpl.updateEmpBankDetails(empId, null, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateEmpBankDetailsCase8() {
		when(empBankDetailRepository
					.findByEmployeeIdAndIsDeleted(Mockito.any(), Mockito.anyBoolean()))
					.thenReturn(null);
		empBankDetailsServiceImpl.updateEmpBankDetails(empId, empBankDetailsDto, userId);
	}

	@Test
	public void testUpdateEmpBankDetailsCase5() {
		when(empBankDetailRepository
					.findByEmployeeIdAndIsDeleted(Mockito.any(), Mockito.anyBoolean()))
					.thenReturn(empBankDetailsEntity);
		when(empBankDetailRepository.saveAndFlush(Mockito.any()))
					.thenReturn(new EmpBankDetailsEntity());
		empBankDetailsServiceImpl.updateEmpBankDetails(empId, empBankDetailsDto, userId);
	}

	@Test
	public void testGetParticularEmpBankDetailCase1() {
		when(empBankDetailRepository
					.findByEmployeeIdAndIsDeleted(Mockito.any(), Mockito.anyBoolean()))
					.thenReturn(empBankDetailsEntity);
		empBankDetailsServiceImpl.getParticularEmpBankDetail(empId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testGetParticularEmpBankDetailCase2() {
		empBankDetailsServiceImpl.getParticularEmpBankDetail(null);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testGetParticularEmpBankDetailCase3() {
		when(empBankDetailRepository
					.findByEmployeeIdAndIsDeleted(Mockito.any(), Mockito.anyBoolean()))
					.thenReturn(null);
		empBankDetailsServiceImpl.getParticularEmpBankDetail(empId);
	}

	@Test
	public void testDeleteEmpBankDetailsWithEmpIdCase1() {
		when(empBankDetailRepository.findByEmployeeIdAndIsDeleted(empId, false))
					.thenReturn(new EmpBankDetailsEntity());
		when(empBankDetailRepository.saveAndFlush(Mockito.any()))
					.thenReturn(new EmpBankDetailsEntity());
		empBankDetailsServiceImpl.deleteEmpBankDetailsWithEmpId(empId, userId);
	}

	@Test
	public void testDeleteEmpBankDetailsWithEmpIdCase2() {
		when(empBankDetailRepository.findByEmployeeIdAndIsDeleted(empId, false))
					.thenReturn(new EmpBankDetailsEntity());
		when(empBankDetailRepository.saveAndFlush(Mockito.any())).thenReturn(null);
		empBankDetailsServiceImpl.deleteEmpBankDetailsWithEmpId(empId, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testDeleteEmpBankDetailsWithEmpIdCase3() {
		empBankDetailsServiceImpl.deleteEmpBankDetailsWithEmpId(null, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testDeleteEmpBankDetailsWithEmpIdCase4() {
		empBankDetailsServiceImpl.deleteEmpBankDetailsWithEmpId(bankId, userId);
	}

	@Test
	public void testDeleteEmpBankDetailsWithBankDetailIdCase1() {
		when(empBankDetailRepository.findByIdAndIsDeleted(bankId, false))
					.thenReturn(new EmpBankDetailsEntity());
		when(empBankDetailRepository.saveAndFlush(Mockito.any()))
					.thenReturn(new EmpBankDetailsEntity());
		empBankDetailsServiceImpl.deleteEmpBankDetailsWithBankDetailId(bankId, userId);
	}

	@Test
	public void testDeleteEmpBankDetailsWithBankDetailIdCase2() {
		when(empBankDetailRepository.findByIdAndIsDeleted(bankId, false))
					.thenReturn(new EmpBankDetailsEntity());
		when(empBankDetailRepository.saveAndFlush(Mockito.any())).thenReturn(null);
		empBankDetailsServiceImpl.deleteEmpBankDetailsWithBankDetailId(bankId, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testDeleteEmpBankDetailsWithBankDetailIdCase3() {
		empBankDetailsServiceImpl.deleteEmpBankDetailsWithBankDetailId(null, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testDeleteEmpBankDetailsWithBankDetailIdCase4() {
		empBankDetailsServiceImpl.deleteEmpBankDetailsWithBankDetailId(bankId, userId);
	}

}
