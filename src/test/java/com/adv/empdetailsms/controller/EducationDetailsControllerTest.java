
package com.adv.empdetailsms.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;

import com.adv.empdetailsms.dto.EmpEducationalDetailDto;
import com.adv.empdetailsms.service.EmpEducationDetailService;

@RunWith(MockitoJUnitRunner.class)
public class EducationDetailsControllerTest {

	@InjectMocks
	private EducationDetailsController empEducationDetailController;
	@Mock
	private EmpEducationDetailService empEducationDetailService;

	@Mock
	private HttpHeaders headers;

	@Test
	public void testGetEmpEduDetails() {
		headers.add("1", "gabi");
		EmpEducationalDetailDto empEducationalDetailDto1 = new EmpEducationalDetailDto();
		empEducationalDetailDto1.setId("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto1.setDegreeType("bsc maths");
		empEducationalDetailDto1.setCollegeName("sacred heart college");
		empEducationalDetailDto1.setDepartment("maths");
		empEducationalDetailDto1.setNameOfUniversity("periyar univ");
		empEducationalDetailDto1.setEmployeeId("9f36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto1.setPassedOutYear(new Date(2000 / 12 / 02));

		EmpEducationalDetailDto empEducationalDetailDto2 = new EmpEducationalDetailDto();
		empEducationalDetailDto2.setId("9f36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto2.setDegreeType("bsc physics");
		empEducationalDetailDto2.setCollegeName("sacred heart college");
		empEducationalDetailDto2.setDepartment("physics");
		empEducationalDetailDto2.setNameOfUniversity("anna univ");
		empEducationalDetailDto2.setEmployeeId("9g36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto2.setPassedOutYear(new Date(2000 / 12 / 03));

		List<EmpEducationalDetailDto> empEducationalDetailDtoList = new ArrayList<>();
		empEducationalDetailDtoList.add(empEducationalDetailDto1);
		empEducationalDetailDtoList.add(empEducationalDetailDto2);
//
//		when(empEducationDetailService.getEmpEduDetails()).thenReturn(empEducationalDetailDtoList);
//		empEducationDetailController.getEmpEduDetails();

	}

	@Test
	public void testregisterEmpEduDetails() {
		String empId = "9f36f3d5-f3cf-4734-a47a-07c7de32b4b1";

		EmpEducationalDetailDto empEducationalDetailDto1 = new EmpEducationalDetailDto();
		empEducationalDetailDto1.setId("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto1.setDegreeType("bsc maths");
		empEducationalDetailDto1.setCollegeName("sacred heart college");
		empEducationalDetailDto1.setDepartment("maths");
		empEducationalDetailDto1.setNameOfUniversity("periyar univ");
		empEducationalDetailDto1.setEmployeeId("9f36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto1.setPassedOutYear(new Date(2000 / 12 / 02));

		EmpEducationalDetailDto empEducationalDetailDto2 = new EmpEducationalDetailDto();
		empEducationalDetailDto2.setId("9f36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto2.setDegreeType("bsc physics");
		empEducationalDetailDto2.setCollegeName("sacred heart college");
		empEducationalDetailDto2.setDepartment("physics");
		empEducationalDetailDto2.setNameOfUniversity("anna univ");
		empEducationalDetailDto2.setEmployeeId("9g36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto2.setPassedOutYear(new Date(2000 / 12 / 03));

		List<EmpEducationalDetailDto> empEducationalDetailDtoList = new ArrayList<>();
		empEducationalDetailDtoList.add(empEducationalDetailDto1);
		empEducationalDetailDtoList.add(empEducationalDetailDto2);

		String userId = headers.getFirst("1");
		doNothing().when(empEducationDetailService).registerEmpEduDetails(empId, empEducationalDetailDtoList, userId);
		empEducationDetailController.createEmpEduDetails(empId, empEducationalDetailDtoList, headers);
	}

	@Test
	public void testUpdateEmpEduDetails() {
		String empId = "9f36f3d5-f3cf-4734-a47a-07c7de32b4b1";

		EmpEducationalDetailDto empEducationalDetailDto1 = new EmpEducationalDetailDto();
		empEducationalDetailDto1.setId("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto1.setDegreeType("msc maths");
		empEducationalDetailDto1.setCollegeName("sacred heart college");
		empEducationalDetailDto1.setDepartment("maths");
		empEducationalDetailDto1.setNameOfUniversity("periyar univ");
		empEducationalDetailDto1.setEmployeeId("9f36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto1.setPassedOutYear(new Date(2000 / 12 / 02));

		EmpEducationalDetailDto empEducationalDetailDto2 = new EmpEducationalDetailDto();
		empEducationalDetailDto2.setId("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto2.setDegreeType("bsc physics");
		empEducationalDetailDto2.setCollegeName("sacred heart college");
		empEducationalDetailDto2.setDepartment("physics");
		empEducationalDetailDto2.setNameOfUniversity("anna univ");
		empEducationalDetailDto2.setEmployeeId("9g36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto2.setPassedOutYear(new Date(2000 / 12 / 03));

		List<EmpEducationalDetailDto> empEducationalDetailDtoList = new ArrayList<>();
		empEducationalDetailDtoList.add(empEducationalDetailDto1);
		empEducationalDetailDtoList.add(empEducationalDetailDto2);
		String userId = headers.getFirst("1");
		doNothing().when(empEducationDetailService).updParticularEmpEduDetails(empId, empEducationalDetailDtoList,
				userId);
		empEducationDetailController.updateEmpEduDetails(empId, empEducationalDetailDtoList, headers);
	}

//	@Test
//	public void testDeleteEmpEduDetails() {
//		String empId = "9f36f3d5-f3cf-4734-a47a-07c7de32b4b1";
//		String userId = headers.getFirst("1");
//		doNothing().when(empEducationDetailService).delEmpEduDetails(empId, userId);
//		empEducationDetailController.delEmpEduDetails(empId, headers);
//	}

	@Test
	public void testGetParticularEmpEduDetails() {
		String empId = "9f36f3d5-f3cf-4734-a47a-07c7de32b4b1";
		EmpEducationalDetailDto empEducationalDetailDto1 = new EmpEducationalDetailDto();
		empEducationalDetailDto1.setId("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto1.setDegreeType("msc maths");
		empEducationalDetailDto1.setCollegeName("sacred heart college");
		empEducationalDetailDto1.setDepartment("maths");
		empEducationalDetailDto1.setNameOfUniversity("periyar univ");
		empEducationalDetailDto1.setEmployeeId("9f36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto1.setPassedOutYear(new Date(2000 / 12 / 02));

		EmpEducationalDetailDto empEducationalDetailDto2 = new EmpEducationalDetailDto();
		empEducationalDetailDto2.setId("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto2.setDegreeType("bsc physics");
		empEducationalDetailDto2.setCollegeName("sacred heart college");
		empEducationalDetailDto2.setDepartment("physics");
		empEducationalDetailDto2.setNameOfUniversity("anna univ");
		empEducationalDetailDto2.setEmployeeId("9f36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		empEducationalDetailDto2.setPassedOutYear(new Date(2000 / 12 / 03));

		List<EmpEducationalDetailDto> empEducationalDetailDtoList = new ArrayList<>();
		empEducationalDetailDtoList.add(empEducationalDetailDto1);
		empEducationalDetailDtoList.add(empEducationalDetailDto2);

		when(empEducationDetailService.getParticularEmpEduDetails(empId)).thenReturn(empEducationalDetailDtoList);
		empEducationDetailController.getparticularEmpEduDetails(empId);
	}

	@Test
	public void testDeleteParticularEducationDetail() {
		String empId = "9f36f3d5-f3cf-4734-a47a-07c7de32b4b1";
		String userId = headers.getFirst("1");
		doNothing().when(empEducationDetailService).delParticularEduDetails(empId, userId);
		empEducationDetailController.delParticularEduDetail(empId, headers);
	}
}
