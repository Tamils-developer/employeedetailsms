package com.adv.empdetailsms.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adv.empdetailsms.dto.DropDownDto;
import com.adv.empdetailsms.dto.NomineeDetailsDto;
import com.adv.empdetailsms.dto.NomineeDto;
import com.adv.empdetailsms.entity.BasicDetailsEntity;
import com.adv.empdetailsms.entity.FamilyEntity;
import com.adv.empdetailsms.exception.EmployeeDetailsException;
import com.adv.empdetailsms.model.ErrorMsgConst;
import com.adv.empdetailsms.repository.BasicDetailsRepository;
import com.adv.empdetailsms.repository.FamilyRepository;
import com.adv.empdetailsms.service.NomineeDetailsService;

@Service(value = "nomineeDetailsServiceImpl")
public class NomineeDetailsServiceImpl implements NomineeDetailsService {

	@Autowired
	private FamilyRepository familyRepository;

	@Autowired
	private BasicDetailsRepository basicDetailsRepository;

	private static final Logger LOGGER = LogManager.getLogger(NomineeDetailsServiceImpl.class);

	@Override
	public NomineeDto getNomineeDetails(String employeeId) {
		LOGGER.info("Inside NomineeDetailsServiceImpl  - getFamilyDetails() ");
		if (employeeId != null) {
			List<FamilyEntity> familyEntities = familyRepository.findByEmployeeIdAndIsDeleted(employeeId, false);
			BasicDetailsEntity basicDetailsEntity = basicDetailsRepository.findByIdAndIsDeleted(employeeId, false);
			LOGGER.info("Basic details :{} and Family details retrieved from Db : {}", basicDetailsEntity,
					familyEntities);
			NomineeDto nomineeDto = new NomineeDto();
			if (basicDetailsEntity != null) {
				nomineeDto.setUanNumber(basicDetailsEntity.getUanNum());
				if (!familyEntities.isEmpty()) {
					List<NomineeDetailsDto> dtos = new ArrayList<>();
					for (FamilyEntity familyEntity : familyEntities) {
						NomineeDetailsDto detailsDto = createFamilyDtoToEntity(familyEntity);
						dtos.add(detailsDto);
						nomineeDto.setNomineeDetailsDtoList(dtos);
					}
				} else {
					LOGGER.error(ErrorMsgConst.EC101MESSAGE);
					throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
				}
			} else {
				LOGGER.error(ErrorMsgConst.EC101MESSAGE);
				throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
			}
			LOGGER.info("Nominee details list response : {}", nomineeDto);
			return nomineeDto;
		} else {
			LOGGER.error(ErrorMsgConst.EC101MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
		}
	}

	@Override
	public void updateNomineeDetails(NomineeDto nomineeDtos, String employeeId, String userId) {
		LOGGER.info("Inside NomineeDetailsServiceImpl : updateFamilyDetails() ");
		if (employeeId != null && nomineeDtos != null) {
			LOGGER.info("Updating Nominee details with NomineeDetailsDto :{},employeeId :{}", nomineeDtos, employeeId);
			BasicDetailsEntity basicDetailsEntity = basicDetailsRepository.findByIdAndIsDeleted(employeeId, false);
			LOGGER.info("Retrieved Employee basic details from db : " + basicDetailsEntity);
			if (basicDetailsEntity != null) {
				LOGGER.info("Retrieved Employee basic details is not null : " + basicDetailsEntity);
				basicDetailsEntity.setUanNum(nomineeDtos.getUanNumber());
				basicDetailsEntity.setUpdatedBy(userId);
				basicDetailsEntity.setUpdatedDate(new Timestamp(new Date().getTime()));
				basicDetailsRepository.saveAndFlush(basicDetailsEntity);
			} else {
				LOGGER.error(ErrorMsgConst.EC101MESSAGE);
				throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
			}
			List<NomineeDetailsDto> nomineeDetailsdtoList = nomineeDtos.getNomineeDetailsDtoList();
			if (!nomineeDetailsdtoList.isEmpty()) {
				for (NomineeDetailsDto nomineeDetailsDto : nomineeDetailsdtoList) {
					FamilyEntity entity = familyRepository.findByIdAndIsDeleted(nomineeDetailsDto.getId(), false);
					entity.setNominee(true);
					entity.setPercentage(nomineeDetailsDto.getPercentage());
					entity.setUpdatedBy(userId);
					entity.setUpdatedDate(new Timestamp(new Date().getTime()));
					familyRepository.saveAndFlush(entity);
				}
			} else {
				LOGGER.error(ErrorMsgConst.EC101MESSAGE);
				throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC101MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
		}
	}

	@Override
	public void deleteNomineeDetails(String nomineeId, String userId) {
		LOGGER.info("Inside NomineeDetailsServiceImpl : deleteNomineeDetails() ");
		if (nomineeId != null) {
			LOGGER.info("Delete Nominee details with employeeId :{}", nomineeId);
			FamilyEntity entity = familyRepository.findByIdAndIsDeleted(nomineeId, false);
			entity.setNominee(false);
			entity.setPercentage(0);
			entity.setUpdatedBy(userId);
			entity.setUpdatedDate(new Timestamp(new Date().getTime()));
			familyRepository.saveAndFlush(entity);

		} else {
			LOGGER.error(ErrorMsgConst.EC101MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
		}
	}

	@Override
	public List<DropDownDto> getDropDownDetails(String employeeId) {
		LOGGER.info("Inside NomineeDetailsServiceImpl : getDropDownDetails() ");
		if (employeeId != null) {
			List<FamilyEntity> familyEntities = familyRepository.findByEmployeeIdAndIsDeleted(employeeId, false);
			LOGGER.info("Family details retrieved from Db :{} and ID With  : {}",familyEntities ,employeeId);
			if (!familyEntities.isEmpty()) {
				List<DropDownDto> downDtos = new ArrayList<>();
				for (FamilyEntity familyEntity : familyEntities) {
					DropDownDto downDto = createFamilyEntityToDropDownDto(familyEntity);
					downDtos.add(downDto);
				}
				return downDtos;
			} else {
				LOGGER.error(ErrorMsgConst.EC102MESSAGE);
				throw new EmployeeDetailsException(ErrorMsgConst.EC102, ErrorMsgConst.EC102MESSAGE);
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC101MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC101, ErrorMsgConst.EC101MESSAGE);
		}
	}

	private NomineeDetailsDto createFamilyDtoToEntity(FamilyEntity entity) {
		NomineeDetailsDto nomineeDetailsDto = new NomineeDetailsDto();
		nomineeDetailsDto.setId(entity.getId());
		nomineeDetailsDto.setName(entity.getName());
		nomineeDetailsDto.setRelation(entity.getRelation());
		nomineeDetailsDto.setPercentage(entity.getPercentage());
		return nomineeDetailsDto;
	}

	private DropDownDto createFamilyEntityToDropDownDto(FamilyEntity entity) {
		DropDownDto nomineeDetailsDto = new DropDownDto();
		nomineeDetailsDto.setId(entity.getId());
		nomineeDetailsDto.setName(entity.getName());
		nomineeDetailsDto.setRelation(entity.getRelation());
		return nomineeDetailsDto;
	}

}