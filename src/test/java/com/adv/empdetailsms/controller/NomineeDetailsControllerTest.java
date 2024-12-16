

package com.adv.empdetailsms.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;

import com.adv.empdetailsms.dto.DropDownDto;
import com.adv.empdetailsms.dto.NomineeDetailsDto;
import com.adv.empdetailsms.dto.NomineeDto;
import com.adv.empdetailsms.service.NomineeDetailsService;

import io.swagger.v3.oas.annotations.Operation;

@RunWith(MockitoJUnitRunner.class)
public class NomineeDetailsControllerTest {
	
	@InjectMocks
	private NomineeDetailsController nomineeDetailsController;
	
	@Mock
	private NomineeDetailsService nomineeDetailService;
	
	private NomineeDto nomineeDto=new NomineeDto();
	
	private String employeeId = "8f751cd6-c9d5-4156-b2af-33a53c3a42d3";

	
	@Mock
	private HttpHeaders headers;
	
	@Test
	@Operation(summary = "Get the Family Details")
	public void testGetNomineeDetails() {
		List<NomineeDetailsDto> detailsDtos = new ArrayList<>();
		NomineeDetailsDto nomineeDetailsDto=new NomineeDetailsDto();
		nomineeDetailsDto.setId(employeeId);
		nomineeDetailsDto.setName("Tiger");
		nomineeDetailsDto.setRelation("Father");
		nomineeDetailsDto.setPercentage(50);
		detailsDtos.add(nomineeDetailsDto);
		nomineeDto.setNomineeDetailsDtoList(detailsDtos);
		nomineeDto.setUanNumber("233456432");
		when(nomineeDetailService.getNomineeDetails(employeeId)).thenReturn(nomineeDto);
		nomineeDetailsController.getNomineeDetails(employeeId);
	}
	@Test
	@Operation(summary = "Get the Family Details")
	public void testGetDropDownDetails() {
		List<DropDownDto> detailsDtos = new ArrayList<>();
		DropDownDto downDto=new DropDownDto();
		downDto.setId(employeeId);
		downDto.setName("Tiger");
		downDto.setRelation("Father");
		detailsDtos.add(downDto);
		when(nomineeDetailService.getDropDownDetails(employeeId)).thenReturn(detailsDtos);
		nomineeDetailsController.getDropDownDetails(employeeId);
	}
	
	@Test
	@Operation(summary = "Update the Nominee Details")
	public void testUpdateNomineeDetails() {
		String userId = headers.getFirst("1");
		doNothing().when(nomineeDetailService).updateNomineeDetails(nomineeDto, employeeId, userId);
		nomineeDetailsController.updateNomineeDetails(employeeId, nomineeDto, headers);
	}
	@Test
	@Operation(summary = "Delete the Nominee Details")
	public void testDeleteNomineeDetails() {
		String userId = headers.getFirst("1");
		doNothing().when(nomineeDetailService).deleteNomineeDetails(employeeId, userId);
		nomineeDetailsController.deleteNomineeDetails(employeeId, headers);
	}
}