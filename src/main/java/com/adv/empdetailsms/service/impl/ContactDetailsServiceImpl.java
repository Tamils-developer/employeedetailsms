package com.adv.empdetailsms.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adv.empdetailsms.dto.ContactDetailsDto;
import com.adv.empdetailsms.dto.FamilyDetailsDto;
import com.adv.empdetailsms.entity.BasicDetailsEntity;
import com.adv.empdetailsms.entity.FamilyEntity;
import com.adv.empdetailsms.exception.EmployeeDetailsException;
import com.adv.empdetailsms.model.ErrorMsgConst;
import com.adv.empdetailsms.repository.BasicDetailsRepository;
import com.adv.empdetailsms.repository.FamilyRepository;
import com.adv.empdetailsms.service.ContactDetailsService;

import io.micrometer.common.util.StringUtils;

@Service(value = "contactDetaisServiceImpl")
public class ContactDetailsServiceImpl implements ContactDetailsService {

	@Autowired
	private FamilyRepository familyRepository;

	@Autowired
	private BasicDetailsRepository basicDetailsRepository;

	private final static Logger LOGGER = LogManager.getLogger(ContactDetailsServiceImpl.class);

	@Override
	public void createContactDetails(String employeeId, ContactDetailsDto contactDetailsDto, String userId) {
		LOGGER.info("Inside ContactDetailServiceImpl :: registerContactDetails() ");
		if (employeeId != null) {
			BasicDetailsEntity basicDetailsEntity = basicDetailsRepository.findByIdAndIsDeleted(employeeId, false);
			LOGGER.info("BasicDetailsEntity from db::{} : employeeId : {} and userId : {}", basicDetailsEntity, userId,
					employeeId);
			if (contactDetailsDto != null && basicDetailsEntity != null) {
				List<FamilyDetailsDto> familyDetailsDtoList = contactDetailsDto.getFamilydetails();
				for (FamilyDetailsDto familyDetailsDto : familyDetailsDtoList) {
					FamilyEntity familyDtoToEntity = familyDtoToEntity(familyDetailsDto, userId, employeeId);
					LOGGER.info("familyDetails entity created :: {}", familyDtoToEntity);
					familyDtoToEntity.setEmployeeId(employeeId);
					FamilyEntity familyContactDetails = familyRepository.save(familyDtoToEntity);
					if (familyContactDetails != null) {
						LOGGER.info("Contact details is created :: FamilyContactDetailsEntity : {}",
								familyContactDetails);
					} else {
						LOGGER.info("Contact details not created :: FamilyContactDetailsDto", familyDetailsDtoList);
					}
				}
			} else {
				LOGGER.error(ErrorMsgConst.EC101MESSAGE);
				throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC103MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC103, ErrorMsgConst.EC103MESSAGE);
		}
	}

	@Override
	public ContactDetailsDto getParticularEmployeeContactDetails(String employeeId) {
		LOGGER.info("Get ContactDetails()");
		if (employeeId != null) {
			ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
			List<FamilyEntity> familyEntities = familyRepository.findByEmployeeIdAndIsDeleted(employeeId, false);
			LOGGER.info("Retrived Employee Basic Details from Db : {}", familyRepository);
			List<FamilyDetailsDto> familyDetailsDtos = new ArrayList<>();
			LOGGER.info("Entity from Db :: FamilyEntities{}", familyEntities);
			if (!familyEntities.isEmpty()) {
				LOGGER.info("family entity is not empty :: {familyEntity}:", familyEntities);
				for (FamilyEntity familyEntity : familyEntities) {
					FamilyDetailsDto familyDto = familyEntityToDto(familyEntity);
					LOGGER.info("Family details Dto created :{}", familyDto);
					familyDetailsDtos.add(familyDto);
				}
			} else {
				LOGGER.error(ErrorMsgConst.EC104MESSAGE);
				throw new EmployeeDetailsException(ErrorMsgConst.EC104, ErrorMsgConst.EC104MESSAGE);
			}
			contactDetailsDto.setFamilydetails(familyDetailsDtos);
			contactDetailsDto.setEmployeeId(employeeId);
			return contactDetailsDto;
		} else {
			LOGGER.error(ErrorMsgConst.EC101MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
		}
	}

	@Override
	public void updatePartiEmpContactDtls(String employeeId, ContactDetailsDto contactDetailsDto, String userId) {
		LOGGER.info("Inside ContactDetailsServiceImpl : updateParticularEmployeeContactDetails()");
		if (employeeId != null && contactDetailsDto != null) {
			LOGGER.info("The Employee id is :: {} ", employeeId);
			boolean isChanged = false;
			List<FamilyEntity> familyEntitiesFromDb = familyRepository.findByEmployeeIdAndIsDeleted(employeeId, false);
			LOGGER.info("Employee Contact details from db : {} : employeeId : {} and userId : {} ",
					familyEntitiesFromDb, employeeId, userId);
			// For FamilyEntity
			for (FamilyDetailsDto familyDetailsDto : contactDetailsDto.getFamilydetails()) {
				if (StringUtils.isNotEmpty(familyDetailsDto.getId())) {
					for (FamilyEntity familyEntity : familyEntitiesFromDb) {
						if (familyEntity.getId().equalsIgnoreCase(familyDetailsDto.getId())) {
							LOGGER.info("Id matches with existing data :: {}", familyDetailsDto.getId());
							if (!familyDetailsDto.getAadhar().equals(familyEntity.getAadhar())) {
								isChanged = true;
								familyEntity.setAadhar(familyDetailsDto.getAadhar());
							}
							if (!familyDetailsDto.getName().equals(familyEntity.getName())) {
								isChanged = true;
								familyEntity.setName(familyDetailsDto.getName());
							}
							if (!familyDetailsDto.getDob().equals(familyEntity.getDob())) {
								isChanged = true;
								familyEntity.setDob(familyDetailsDto.getDob());
							}
							if (!familyDetailsDto.getMobile().equals(familyEntity.getMobile())) {
								isChanged = true;
								familyEntity.setMobile(familyDetailsDto.getMobile());
								;
							}
							if (!familyDetailsDto.getRelation().equals(familyEntity.getRelation())) {
								isChanged = true;
								familyEntity.setRelation(familyDetailsDto.getRelation());
							}
							LOGGER.info("Is existing data updated :: {}", isChanged);
							familyEntity.setUpdatedBy(userId);
							familyEntity.setUpdatedDate(new Timestamp(new Date().getTime()));
							break;
						}
					}

				} else {
					isChanged = true;
					addNewFamilyEntity(familyEntitiesFromDb, familyDetailsDto, userId, employeeId);
				}
			}

			if (isChanged) {
				for (FamilyEntity familyEntity : familyEntitiesFromDb) {
					familyRepository.saveAndFlush(familyEntity);
				}
			}

		} else {
			LOGGER.error(ErrorMsgConst.EC101MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
		}
	}

	private void addNewFamilyEntity(List<FamilyEntity> familyEntitiesFromDb, FamilyDetailsDto familyDetailsDto,
			String userId, String employeeId) {
		familyEntitiesFromDb.add(familyDtoToEntity(familyDetailsDto, userId, employeeId));
	}

	@Override
	public void deleteParticularEmployeeContactDetails(String employeeId, String userId) {
		LOGGER.info("Inside the Employee Contact Details:: Delete Employee Contact Details ");

		if (employeeId != null) {
			List<FamilyEntity> deleteFamilyEntity = familyRepository.findByEmployeeIdAndIsDeleted(employeeId, false);
			LOGGER.info("Employee Contact details from db:{}", deleteFamilyEntity);
			for (FamilyEntity familyEntity : deleteFamilyEntity) {
				familyEntity.setDeleted(true);
				familyEntity.setUpdatedBy(userId);
				familyEntity.setUpdatedDate(new Timestamp(new Date().getTime()));

				familyRepository.saveAndFlush(familyEntity);
				LOGGER.info("The Employee Contact Details is Deleted :: employeeId{} : userId{}", employeeId, userId);
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC101MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
		}
	}

	private FamilyEntity familyDtoToEntity(FamilyDetailsDto familyDto, String userId, String employeeId) {
		LOGGER.info("familyDto fromDb::{} : userId :{}", familyDto, userId);
		FamilyEntity familyEntity = new FamilyEntity();
		familyEntity.setId(familyDto.getId());
		familyEntity.setEmployeeId(employeeId);
		familyEntity.setName(familyDto.getName());
		familyEntity.setAadhar(familyDto.getAadhar());
		familyEntity.setMobile(familyDto.getMobile());
		familyEntity.setRelation(familyDto.getRelation());
		familyEntity.setDob(familyDto.getDob());
		familyEntity.setCreatedBy(userId);
		familyEntity.setCreatedDate(new Timestamp(new Date().getTime()));
		LOGGER.info("familyDto to familyEntity::{} ", familyEntity);
		return familyEntity;
	}

	private FamilyDetailsDto familyEntityToDto(FamilyEntity familyEntity) {
		FamilyDetailsDto familyDto = new FamilyDetailsDto();
		familyDto.setId(familyEntity.getId());
		familyDto.setName(familyEntity.getName());
		familyDto.setAadhar(familyEntity.getAadhar());
		familyDto.setMobile(familyEntity.getMobile());
		familyDto.setRelation(familyEntity.getRelation());
		familyDto.setDob(familyEntity.getDob());
		LOGGER.info("familyEntity to familyDto::{} :", familyDto);
		return familyDto;
	}

}
