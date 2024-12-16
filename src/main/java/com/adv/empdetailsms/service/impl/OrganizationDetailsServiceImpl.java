package com.adv.empdetailsms.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adv.empdetailsms.dto.OrganizationDetailsDto;
import com.adv.empdetailsms.entity.BasicDetailsEntity;
import com.adv.empdetailsms.entity.OrganizationDetailsEntity;
import com.adv.empdetailsms.exception.EmployeeDetailsException;
import com.adv.empdetailsms.model.ErrorMsgConst;
import com.adv.empdetailsms.repository.BasicDetailsRepository;
import com.adv.empdetailsms.repository.OrganizationDetailsRepository;
import com.adv.empdetailsms.service.OrganizationDetailsService;

@Service(value = "experienceDetailsServiceImpl")
public class OrganizationDetailsServiceImpl implements OrganizationDetailsService {

	@Autowired
	private OrganizationDetailsRepository organizationDetialsRepository;

	@Autowired
	private BasicDetailsRepository detailsRepository;

	private static final Logger LOGGER = LogManager.getLogger(OrganizationDetailsServiceImpl.class);

	@Override
	public void createOrganizationDetails(List<OrganizationDetailsDto> organizationDetailsDtoList, String employeeId,
			String userId) {
		LOGGER.info(" Inside OrganizationDetailsServiceImpl :: createOrganizationDetails()");
		if (employeeId != null && !organizationDetailsDtoList.isEmpty()) {
			BasicDetailsEntity basicDetailsEntity = detailsRepository.findByIdAndIsDeleted(employeeId, false);
			LOGGER.info("Employee details with ID : {}", basicDetailsEntity);
			if (basicDetailsEntity != null && employeeId.equals(basicDetailsEntity.getId())) {
				for (OrganizationDetailsDto organizationDetailsDto : organizationDetailsDtoList) {
					if (organizationDetailsDto != null) {
						OrganizationDetailsEntity organizationDetailsEntity = createOrgDtoToOrgEntity(
								organizationDetailsDto);
						organizationDetailsEntity.setEmployeeId(employeeId);
						organizationDetailsEntity.setId(null);
						organizationDetailsEntity.setCreatedBy(userId);
						OrganizationDetailsEntity organizationDetailsEntityNew = organizationDetialsRepository
								.save(organizationDetailsEntity);
						if (organizationDetailsEntityNew != null) {
							LOGGER.info("Experience details created with ID : {} , Entity : {}", employeeId,
									organizationDetailsEntityNew);
						} else {
							LOGGER.info("Experience details not created with ID : {} ", employeeId);
						}
					} else {
						LOGGER.error(ErrorMsgConst.EC102MESSAGE);
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
	public List<OrganizationDetailsDto> getParticularEmpOrgDetails(String empId) {
		LOGGER.info(" Inside OrganizationDetailsServiceImpl :: getParticularEmpOrgDetails()");
		if (empId != null) {
			List<OrganizationDetailsEntity> organizationDetailsEntityList = organizationDetialsRepository
					.findByEmployeeIdAndIsDeleted(empId, false);
			LOGGER.info("Get Employee Experience Details from Db:: organizationDetailsEntity: {} ", empId,
					organizationDetailsEntityList);
			if (!organizationDetailsEntityList.isEmpty()) {
				List<OrganizationDetailsDto> organizationDetialsDtoList = new ArrayList<>();
				for (OrganizationDetailsEntity organizationDetailsEntity : organizationDetailsEntityList) {
					OrganizationDetailsDto organizationDetialsDto = createExpEntityToOrgDto(organizationDetailsEntity);
					organizationDetialsDtoList.add(organizationDetialsDto);
				}
				LOGGER.info("Get Employee Experience Details from Db : {} ", organizationDetialsDtoList);
				return organizationDetialsDtoList;
			} else {
				LOGGER.error(ErrorMsgConst.EC102MESSAGE);
				throw new EmployeeDetailsException(ErrorMsgConst.EC104, ErrorMsgConst.EC104MESSAGE);
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC102MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
		}
	}

	@Override
	public void deleteParticularEmpOrgDetails(String organizationId, String userId) {
		LOGGER.info(" Inside OrganizationDetailsServiceImpl :: deleteParticularEmpOrgDetails()");
		if (organizationId != null) {
			OrganizationDetailsEntity organizationDetailsEntity = organizationDetialsRepository
					.findByIdAndIsDeleted(organizationId, false);
			LOGGER.info("Get Employee Experience Details from Db:: organizationDetailsEntity: {} ", organizationId,
					organizationDetailsEntity);
			if (organizationDetailsEntity != null) {
				organizationDetailsEntity.setDeleted(true);
				organizationDetailsEntity.setCreatedBy(userId);
				OrganizationDetailsEntity organizationDetailsEntityNew = organizationDetialsRepository
						.saveAndFlush(organizationDetailsEntity);
				if (organizationDetailsEntityNew.isDeleted()) {
					LOGGER.info("Experience details deleted with ID : {}", organizationId);
				} else {
					LOGGER.info("Experience details not deleted with ID : {} ", organizationId);
				}
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC102MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
		}
	}

	@Override
	public void updateParticularEmpOrgDetails(List<OrganizationDetailsDto> organizationDetailsDtoList,
			String employeeId, String userId) {
		LOGGER.info(" Inside OrganizationDetailsServiceImpl :: updateParticularEmpOrgDetails()");
		if (!organizationDetailsDtoList.isEmpty() && employeeId != null) {
			List<OrganizationDetailsEntity> organizationDetailsEntityListDb = organizationDetialsRepository
					.findByEmployeeIdAndIsDeleted(employeeId, false);
			LOGGER.info("Get Employee Experience Details from Db:: organizationDetailsEntity: {} ", employeeId,
					organizationDetailsEntityListDb);
			for (OrganizationDetailsDto organizationDetailsdto : organizationDetailsDtoList) {
				if (organizationDetailsdto != null) {
					if (organizationDetailsdto.getId() != null) {
						updateOrgEntity(organizationDetailsEntityListDb, organizationDetailsdto, userId);
					} else {
						createOrgDetails(organizationDetailsdto, employeeId, userId);
					}
				} else {
					LOGGER.error(ErrorMsgConst.EC102MESSAGE);
				}
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC102MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
		}
	}

	public void createOrgDetails(OrganizationDetailsDto organizationDetailsdto, String employeeId, String userId) {
		OrganizationDetailsEntity organizationDetailsEntity = createOrgDtoToOrgEntity(organizationDetailsdto);
		organizationDetailsEntity.setEmployeeId(employeeId);
		organizationDetailsEntity.setId(null);
		organizationDetailsEntity.setCreatedBy(userId);
		OrganizationDetailsEntity organizationDetailsEntityNew = organizationDetialsRepository
				.save(organizationDetailsEntity);
		if (organizationDetailsEntityNew != null) {
			LOGGER.info("Experience details created with ID : {} , Entity : {}", employeeId,
					organizationDetailsEntityNew);
		} else {
			LOGGER.info("Experience details not created with ID : {} ", employeeId);
		}
	}

	private void updateOrgEntity(List<OrganizationDetailsEntity> organizationDetailsEntityListDb,
			OrganizationDetailsDto organizationDetailsdto, String userId) {
		for (OrganizationDetailsEntity organizationDetailsEntity : organizationDetailsEntityListDb) {
			if (organizationDetailsEntity.getId().equals(organizationDetailsdto.getId())) {
				if (!organizationDetailsEntity.getNameOfOrganization()
						.equals(organizationDetailsdto.getNameOfOrganization())) {
					organizationDetailsEntity.setNameOfOrganization(organizationDetailsdto.getNameOfOrganization());
				}
				if (!organizationDetailsEntity.getEsiNumber().equalsIgnoreCase(organizationDetailsdto.getEsiNumber())) {
					organizationDetailsEntity.setEsiNumber(organizationDetailsdto.getEsiNumber());
				}
				if (!organizationDetailsEntity.getFromDate().equals(organizationDetailsdto.getFromDate())) {
					organizationDetailsEntity.setFromDate(organizationDetailsdto.getFromDate());
				}
				if (!organizationDetailsEntity.getEndDate().equals(organizationDetailsdto.getEndDate())) {
					organizationDetailsEntity.setEndDate(organizationDetailsdto.getEndDate());
				}
				if (!organizationDetailsEntity.getRole().equals(organizationDetailsdto.getRole())) {
					organizationDetailsEntity.setRole(organizationDetailsdto.getRole());
				}
				organizationDetailsEntity.setUpdatedBy(userId);
				organizationDetailsEntity.setUpdatedDate(new Timestamp(new Date().getTime()));
				OrganizationDetailsEntity organizationDetailsEntityNew = organizationDetialsRepository
						.saveAndFlush(organizationDetailsEntity);
				if (organizationDetailsEntityNew != null) {
					LOGGER.info("Experience details updated with ID : {}", organizationDetailsEntity.getId());
				} else {
					LOGGER.info("Experience details not updated with ID : {} ", organizationDetailsEntity.getId());
				}
				break;
			}
		}
	}

	private OrganizationDetailsEntity createOrgDtoToOrgEntity(OrganizationDetailsDto organizationDetialsDto) {
		OrganizationDetailsEntity organizationDetialsEntity = new OrganizationDetailsEntity();
		organizationDetialsEntity.setTotalYearsOfExperience(organizationDetialsDto.getTotalYearsOfExperience());
		organizationDetialsEntity.setNameOfOrganization(organizationDetialsDto.getNameOfOrganization());
		organizationDetialsEntity.setFromDate(organizationDetialsDto.getFromDate());
		organizationDetialsEntity.setEsiNumber(organizationDetialsDto.getEsiNumber());
		organizationDetialsEntity.setRole(organizationDetialsDto.getRole());
		organizationDetialsEntity.setEndDate(organizationDetialsDto.getEndDate());
		organizationDetialsEntity.setCreatedDate(new Timestamp(new Date().getTime()));
		return organizationDetialsEntity;
	}

	private OrganizationDetailsDto createExpEntityToOrgDto(OrganizationDetailsEntity organizationDetailsEntity) {
		OrganizationDetailsDto organizationDetialsDto = new OrganizationDetailsDto();
		organizationDetialsDto.setId(organizationDetailsEntity.getId());
		organizationDetialsDto.setTotalYearsOfExperience(organizationDetailsEntity.getTotalYearsOfExperience());
		organizationDetialsDto.setNameOfOrganization(organizationDetailsEntity.getNameOfOrganization());
		organizationDetialsDto.setFromDate(organizationDetailsEntity.getFromDate());
		organizationDetialsDto.setEndDate(organizationDetailsEntity.getEndDate());
		organizationDetialsDto.setEsiNumber(organizationDetailsEntity.getEsiNumber());
		organizationDetialsDto.setRole(organizationDetailsEntity.getRole());
		return organizationDetialsDto;
	}
}