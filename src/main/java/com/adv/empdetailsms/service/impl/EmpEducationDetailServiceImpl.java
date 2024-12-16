package com.adv.empdetailsms.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adv.empdetailsms.dto.EmpEducationalDetailDto;
import com.adv.empdetailsms.entity.BasicDetailsEntity;
import com.adv.empdetailsms.entity.EmpEducationDetailEntity;
import com.adv.empdetailsms.exception.EmployeeDetailsException;
import com.adv.empdetailsms.model.ErrorMsgConst;
import com.adv.empdetailsms.repository.BasicDetailsRepository;
import com.adv.empdetailsms.repository.EmpEducationDetailRepository;
import com.adv.empdetailsms.service.EmpEducationDetailService;

@Service(value = "empEducationDetailServiceImpl")
public class EmpEducationDetailServiceImpl implements EmpEducationDetailService {

	@Autowired
	private EmpEducationDetailRepository empEducationDetailRepository;

	@Autowired
	private BasicDetailsRepository basicDetailsRepository;

	private static final Logger LOGGER = LogManager.getLogger(EmpEducationDetailServiceImpl.class);

	@Override
	public void registerEmpEduDetails(String empId,
				List<EmpEducationalDetailDto> empEducationalDetailDtos, String userId) {
		LOGGER.info("Inside EmpEducationDetailServiceImpl  - registerEmpEduDetails() ");
		if (empId!=null && !empEducationalDetailDtos.isEmpty()) {
			BasicDetailsEntity basicDetailsEntity = basicDetailsRepository
						.findByIdAndIsDeleted(empId, false);
			LOGGER.info("Retrieved Employee basic details from db : " + basicDetailsEntity);
			if (basicDetailsEntity != null) {
				LOGGER.info("Retrieved Employee basic details is not null : " + basicDetailsEntity);
				for (EmpEducationalDetailDto empEducationalDetailDto : empEducationalDetailDtos) {
					registeringDtoToEntity(empId, empEducationalDetailDto, userId);
				}
			} else {
				LOGGER.error(ErrorMsgConst.EC102MESSAGE);
				throw new EmployeeDetailsException(ErrorMsgConst.EC102, ErrorMsgConst.EC102MESSAGE);
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC101MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
		}
	}

	private void registeringDtoToEntity(String empId, EmpEducationalDetailDto empEducationalDetailDto,
				String userId) {
		if (empEducationalDetailDto != null) {
			EmpEducationDetailEntity empEducationDetailEntity = transformDtoToEntity(empId, empEducationalDetailDto);
			empEducationDetailEntity.setCreatedDate(new Timestamp(new java.util.Date().getTime()));
			empEducationDetailEntity.setCreatedBy(userId);
			EmpEducationDetailEntity empEducationDetailEntityNew = empEducationDetailRepository
						.saveAndFlush(empEducationDetailEntity);
			if (empEducationDetailEntityNew != null) {
				LOGGER.info("Educational details created with ID : {}", empEducationDetailEntityNew
							.getId());
			} else {
				LOGGER.info("Educational details not created for empEducationalDetailDto : {} ", empEducationalDetailDto);
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC103MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC103, ErrorMsgConst.EC103MESSAGE);
		}
	}

	private EmpEducationDetailEntity transformDtoToEntity(String empId,
				EmpEducationalDetailDto empEducationalDetailDto) {
		EmpEducationDetailEntity empEducationDetailEntity = new EmpEducationDetailEntity();
		empEducationDetailEntity.setDegreeType(empEducationalDetailDto.getDegreeType());
		empEducationDetailEntity.setCollegeName(empEducationalDetailDto.getCollegeName());
		empEducationDetailEntity.setDepartment(empEducationalDetailDto.getDepartment());
		empEducationDetailEntity.setNameOfUniversity(empEducationalDetailDto.getNameOfUniversity());
		empEducationDetailEntity.setPassedOutYear(empEducationalDetailDto.getPassedOutYear());
		empEducationDetailEntity.setEmployeeId(empId);
		return empEducationDetailEntity;
	}

	@Override
	public List<EmpEducationalDetailDto> getEmpEduDetails() {
		LOGGER.info("Inside EmpEducationDetailServiceImpl  - getEmpEduDetails() ");
		List<EmpEducationalDetailDto> educationalDetailDtos = new ArrayList<EmpEducationalDetailDto>();
		List<EmpEducationDetailEntity> empEducationDetailRepositoryList = empEducationDetailRepository
					.findByIsDeleted(false);
		LOGGER.info("Retrieved Employee basic details from db :{}", empEducationDetailRepositoryList);
		for (EmpEducationDetailEntity empEducationDetailEntity : empEducationDetailRepositoryList) {
			EmpEducationalDetailDto empEducationalDetailDto = transformEntityToDto(empEducationDetailEntity);
			LOGGER.info("Educational details dto created with ID : {}", empEducationalDetailDto
						.getId());
			educationalDetailDtos.add(empEducationalDetailDto);
		}
		LOGGER.info("Educational details list response : {}", educationalDetailDtos);
		return educationalDetailDtos;
	}

	private EmpEducationalDetailDto
				transformEntityToDto(EmpEducationDetailEntity empEducationDetailEntity) {
		EmpEducationalDetailDto empEducationalDetailDto = new EmpEducationalDetailDto();
		empEducationalDetailDto.setDegreeType(empEducationDetailEntity.getDegreeType());
		empEducationalDetailDto.setCollegeName(empEducationDetailEntity.getCollegeName());
		empEducationalDetailDto.setDepartment(empEducationDetailEntity.getDepartment());
		empEducationalDetailDto.setNameOfUniversity(empEducationDetailEntity.getNameOfUniversity());
		empEducationalDetailDto.setPassedOutYear(empEducationDetailEntity.getPassedOutYear());
		empEducationalDetailDto.setEmployeeId(empEducationDetailEntity.getEmployeeId());
		empEducationalDetailDto.setId(empEducationDetailEntity.getId());
		return empEducationalDetailDto;
	}

	@Override
	public void delEmpEduDetails(String empId, String userId) {
		LOGGER.info("Inside EmpEducationDetailServiceImpl  - delEmpEduDetails() ");
		if (empId!=null) {
			List<EmpEducationDetailEntity> educationDetailEntityList = empEducationDetailRepository
						.findByIsDeletedAndEmployeeId(false, empId);
			if (!educationDetailEntityList.isEmpty()) {
				for (EmpEducationDetailEntity empEducationDetailEntity : educationDetailEntityList) {
					empEducationDetailEntity.setDeleted(true);
					empEducationDetailEntity.setUpdatedBy(userId);
					empEducationDetailEntity
								.setUpdatedDate(new Timestamp(new java.util.Date().getTime()));
					EmpEducationDetailEntity empEducationDetailEntityNew = empEducationDetailRepository
								.saveAndFlush(empEducationDetailEntity);
					if (empEducationDetailEntityNew.isDeleted()) {
						LOGGER.info("Educational details deleted with ID : {}", empId);
					} else {
						LOGGER.info("Educational details not deleted with ID : {} ", empId);
					}
				}
			} else {
				LOGGER.error(ErrorMsgConst.EC102MESSAGE);
				throw new EmployeeDetailsException(ErrorMsgConst.EC102, ErrorMsgConst.EC102MESSAGE);
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC101MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
		}
	}

	@Override
	public void updParticularEmpEduDetails(String empId,
				List<EmpEducationalDetailDto> empEducationalDetailDtoList, String userId) {
		LOGGER.info("Inside EmpEducationDetailServiceImpl  - updParticularEmpEduDetails() ");
		if (!empEducationalDetailDtoList.isEmpty() && empId!=null) {
			List<EmpEducationDetailEntity> educationDetailEntityListFromDb = empEducationDetailRepository
						.findByIsDeletedAndEmployeeId(false, empId);
			LOGGER.info("Employee Educational Details from db : {}", educationDetailEntityListFromDb);
			for (EmpEducationalDetailDto empEduDetailsDto : empEducationalDetailDtoList) {
				if (empEduDetailsDto.getId()!=null) {
					for (EmpEducationDetailEntity empEduDetailsEntity : educationDetailEntityListFromDb) {
						if (empEduDetailsEntity.getId().equals(empEduDetailsDto.getId())) {
							updateEduDtoToEntity(empId, empEduDetailsDto, empEduDetailsEntity, userId);
							break;
						} else {
							LOGGER.error(ErrorMsgConst.EC102MESSAGE);
						}
					}
				} else {
					createEmpEduDetailEntity(empId, empEduDetailsDto, userId);
					LOGGER.info("new emp education detail is created");
				}
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC101MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
		}
	}

	private void updateEduDtoToEntity(String empId, EmpEducationalDetailDto empEduDetailsDto,
				EmpEducationDetailEntity empEduDetailsEntity, String userId) {
		if (!empEduDetailsEntity.getCollegeName().equals(empEduDetailsDto.getCollegeName())) {
			empEduDetailsEntity.setCollegeName(empEduDetailsDto.getCollegeName());
		}
		if (!empEduDetailsEntity.getDegreeType().equals(empEduDetailsDto.getDegreeType())) {
			empEduDetailsEntity.setDegreeType(empEduDetailsDto.getDegreeType());
		}
		if (!empEduDetailsEntity.getDepartment().equals(empEduDetailsDto.getDepartment())) {
			empEduDetailsEntity.setDepartment(empEduDetailsDto.getDepartment());
		}
		if (!empEduDetailsEntity.getNameOfUniversity()
					.equals(empEduDetailsDto.getNameOfUniversity())) {
			empEduDetailsEntity.setNameOfUniversity(empEduDetailsDto.getNameOfUniversity());
		}
		if (!empEduDetailsEntity.getPassedOutYear().equals(empEduDetailsDto.getPassedOutYear())) {
			empEduDetailsEntity.setPassedOutYear(empEduDetailsDto.getPassedOutYear());
		}
		empEduDetailsEntity.setUpdatedBy(userId);
		empEduDetailsEntity.setUpdatedDate(new Timestamp(new java.util.Date().getTime()));
		EmpEducationDetailEntity empEducationDetailEntityNew = empEducationDetailRepository
					.saveAndFlush(empEduDetailsEntity);
		if (empEducationDetailEntityNew != null) {
			LOGGER.info("Educational details updated with ID : {}", empId);
		} else {
			LOGGER.info("Educational details not updated with ID : {} ", empId);
		}
	}

	private void createEmpEduDetailEntity(String empId,
				EmpEducationalDetailDto empEducationalDetailObj, String userId) {
		EmpEducationDetailEntity empEducationDetailEntityForInsertion = new EmpEducationDetailEntity();
		empEducationDetailEntityForInsertion
					.setNameOfUniversity(empEducationalDetailObj.getNameOfUniversity());
		empEducationDetailEntityForInsertion
					.setCollegeName(empEducationalDetailObj.getCollegeName());
		empEducationDetailEntityForInsertion.setDegreeType(empEducationalDetailObj.getDegreeType());
		empEducationDetailEntityForInsertion.setDepartment(empEducationalDetailObj.getDepartment());
		empEducationDetailEntityForInsertion
					.setPassedOutYear(empEducationalDetailObj.getPassedOutYear());
		empEducationDetailEntityForInsertion.setEmployeeId(empId);
		empEducationDetailEntityForInsertion
					.setCreatedDate(new Timestamp(new java.util.Date().getTime()));
		empEducationDetailEntityForInsertion.setCreatedBy(userId);
		EmpEducationDetailEntity empEducationDetailEntityNew = empEducationDetailRepository
					.saveAndFlush(empEducationDetailEntityForInsertion);
		if (empEducationDetailEntityNew != null) {
			LOGGER.info("Educational detail created for ID : {}", empId);
		} else {
			LOGGER.info("Educational detail not created for ID : {} ", empId);
		}
	}

	@Override
	public void delParticularEduDetails(String eduId, String userId) {
		LOGGER.info("Inside EmpEducationDetailServiceImpl  - delParticularEduDetails() ");
		if (eduId!=null) {
			EmpEducationDetailEntity empEducationDetailEntity = empEducationDetailRepository
						.findByIsDeletedAndId(false, eduId);
			if (empEducationDetailEntity != null) {
				empEducationDetailEntity.setDeleted(true);
				empEducationDetailEntity.setUpdatedBy(userId);
				empEducationDetailEntity.setUpdatedDate(new Timestamp(new java.util.Date().getTime()));
				EmpEducationDetailEntity empEducationDetailEntityNew = empEducationDetailRepository
							.saveAndFlush(empEducationDetailEntity);
				if (empEducationDetailEntityNew.isDeleted()) {
					LOGGER.info("Educational detail deleted with ID : {}", eduId);
				} else {
					LOGGER.info("Educational detail not deleted with ID : {} ", eduId);
				}
			} else {
				LOGGER.error(ErrorMsgConst.EC102MESSAGE);
				throw new EmployeeDetailsException(ErrorMsgConst.EC102, ErrorMsgConst.EC102MESSAGE);
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC101MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
		}
	}

	@Override
	public List<EmpEducationalDetailDto> getParticularEmpEduDetails(String empId) {
		LOGGER.info("Inside EmpEducationDetailServiceImpl  - getParticularEmpEduDetails() ");
		if (empId!=null) {
			List<EmpEducationDetailEntity> particularEmpEduDetailsEntity = empEducationDetailRepository
						.findByIsDeletedAndEmployeeId(false, empId);
			List<EmpEducationalDetailDto> particularEmpEduDetails = new ArrayList<>();
			if (!particularEmpEduDetailsEntity.isEmpty()) {
				for (EmpEducationDetailEntity empEducationDetailEntity : particularEmpEduDetailsEntity) {
					EmpEducationalDetailDto empEducationalDetailDto = transformEntityToDto(empEducationDetailEntity);
					LOGGER.info("Educational details dto created with ID : {}", empEducationalDetailDto
								.getId());
					particularEmpEduDetails.add(empEducationalDetailDto);
				}
				LOGGER.info("Educational details list response : {}", particularEmpEduDetails);
				return particularEmpEduDetails;
			} else {
				LOGGER.error(ErrorMsgConst.EC102MESSAGE);
				throw new EmployeeDetailsException(ErrorMsgConst.EC102, ErrorMsgConst.EC102MESSAGE);
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC101MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
		}
	}
}
