package com.adv.empdetailsms.controller;

import static org.mockito.Mockito.doNothing;
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

import com.adv.empdetailsms.dto.EmpBankDetailsDto;
import com.adv.empdetailsms.service.EmpBankDetailsService;

@RunWith(MockitoJUnitRunner.class)
public class BankDetailsControllerTest {

	@InjectMocks
	private BankDetailsController empBankDetailController;
	@Mock
	private EmpBankDetailsService empBankDetailsService;

	@Mock
	private HttpHeaders headers;

	private String empId;

	private String bankId;

	@Before
	public void run() {
		empId = "9x36f3d5-f3cf-4734-a47a-07c7de32b4b1";
		bankId = "9a36f3d5-f3cf-4734-a47a-07c7de32b4b1";
	}

	@Test
	public void testGetAllBankDetail() {

		EmpBankDetailsDto bankDetailsDto1 = new EmpBankDetailsDto();
		bankDetailsDto1.setId("9a36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		bankDetailsDto1.setAccountHolderName("Test1");
		bankDetailsDto1.setAccountNumber("12456789452AD");
		bankDetailsDto1.setBranchName("Branch1");
		bankDetailsDto1.setEmployeeId("9x36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		bankDetailsDto1.setIfscCode("IFF123456");

		EmpBankDetailsDto bankDetailsDto2 = new EmpBankDetailsDto();
		bankDetailsDto1.setId("9b36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		bankDetailsDto1.setAccountHolderName("Test2");
		bankDetailsDto1.setAccountNumber("12456459852AD");
		bankDetailsDto1.setBranchName("Branch2");
		bankDetailsDto1.setEmployeeId("9y36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		bankDetailsDto1.setIfscCode("IFF132556");

		EmpBankDetailsDto bankDetailsDto3 = new EmpBankDetailsDto();
		bankDetailsDto1.setId("9c36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		bankDetailsDto1.setAccountHolderName("Test3");
		bankDetailsDto1.setAccountNumber("1248549452AF");
		bankDetailsDto1.setBranchName("Branch3");
		bankDetailsDto1.setEmployeeId("9z36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		bankDetailsDto1.setIfscCode("IFCC23006");

		List<EmpBankDetailsDto> empBankDetailsDtosList = new ArrayList<>();

		empBankDetailsDtosList.add(bankDetailsDto1);
		empBankDetailsDtosList.add(bankDetailsDto2);
		empBankDetailsDtosList.add(bankDetailsDto3);
//		when(empBankDetailsService.getAllBankDetails()).thenReturn(empBankDetailsDtosList);
//		empBankDetailController.getAllBankDetails();
	}

	@Test
	public void testRegisterEmpBankDetails() {
		headers.add("1", "gabi");
		String userId = headers.getFirst("1");
		EmpBankDetailsDto empBankDetailsDto = new EmpBankDetailsDto();
		empBankDetailsDto.setAccountHolderName("Test1");
		empBankDetailsDto.setAccountNumber("12456789452AD");
		empBankDetailsDto.setBranchName("Branch1");
		empBankDetailsDto.setIfscCode("IFF123456");

		doNothing().when(empBankDetailsService).registerEmpBankDetails(empId, empBankDetailsDto, userId);
		empBankDetailController.registerEmpBankDetails(empId, empBankDetailsDto, headers);

	}

	@Test
	public void testUpdateEmpBankDetails() {
		String userId = headers.getFirst("1");
		EmpBankDetailsDto empBankDetailsDto = new EmpBankDetailsDto();
		empBankDetailsDto.setAccountHolderName("Test1");
		empBankDetailsDto.setAccountNumber("12456789452AD");
		empBankDetailsDto.setBranchName("Branch1");
		empBankDetailsDto.setIfscCode("IFF123456");
		doNothing().when(empBankDetailsService).updateEmpBankDetails(empId, empBankDetailsDto, userId);
		empBankDetailController.updateEmpBankDetails(empId, empBankDetailsDto, headers);
	}

	@Test
	public void testDeleteEmpBankDetailsWithBankDetailId() {
		String userId = headers.getFirst("1");
		doNothing().when(empBankDetailsService).deleteEmpBankDetailsWithBankDetailId(bankId, userId);
		empBankDetailController.deleteEmpBankDetailsWithBankDetailId(bankId, headers);

	}

	@Test
	public void testGetparticularEmpBankDetailCase1() {
		EmpBankDetailsDto empBankDetailsDto = new EmpBankDetailsDto();
		empBankDetailsDto.setAccountHolderName("Test1");
		empBankDetailsDto.setAccountNumber("12456789452AD");
		empBankDetailsDto.setBranchName("Branch1");
		empBankDetailsDto.setIfscCode("IFF123456");
		when(empBankDetailsService.getParticularEmpBankDetail(empId)).thenReturn(empBankDetailsDto);
		empBankDetailController.getParticularEmpBankDetail(empId);
	}

	@Test
	public void testGetparticularEmpBankDetailCase2() {
		EmpBankDetailsDto empBankDetailsDto = new EmpBankDetailsDto();
		empBankDetailsDto.setAccountHolderName("Test1");
		empBankDetailsDto.setAccountNumber("12456789452AD");
		empBankDetailsDto.setBranchName("Branch1");
		empBankDetailsDto.setIfscCode("IFF123456");

		when(empBankDetailsService.getParticularEmpBankDetail(empId)).thenReturn(empBankDetailsDto);
		empBankDetailController.getParticularEmpBankDetail(empId);
	}

//	@Test
//	public void testDeleteEmpBankDetailsWithEmpId() {
//		String userId = headers.getFirst("1");
//		doNothing().when(empBankDetailsService).deleteEmpBankDetailsWithEmpId(empId, userId);
//		empBankDetailController.deleteEmpBankDetailsWithEmpId(empId, headers);
//	}
}