package com.adv.empdetailsms.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adv.empdetailsms.dto.EmpBankDetailsDto;
import com.adv.empdetailsms.entity.BasicDetailsEntity;
import com.adv.empdetailsms.entity.EmpBankDetailsEntity;
import com.adv.empdetailsms.exception.EmployeeDetailsException;
import com.adv.empdetailsms.model.ErrorMsgConst;
import com.adv.empdetailsms.repository.BasicDetailsRepository;
import com.adv.empdetailsms.repository.EmpBankDetailRepository;
import com.adv.empdetailsms.service.EmpBankDetailsService;

@Service(value = "empBankDetailsServiceImpl")
public class EmpBankDetailsServiceImpl implements EmpBankDetailsService {

	@Autowired
	private EmpBankDetailRepository empBankDetailRepository;

	@Autowired
	private BasicDetailsRepository basicDetailsRepository;

	private static final Logger LOGGER = LogManager.getLogger(EmpBankDetailsServiceImpl.class);

	@Override
	public void registerEmpBankDetails(String empId, EmpBankDetailsDto empBankDetailsDto,
				String userId) {
		LOGGER.info("Inside EmpBankDetailsServiceImpl : registerEmpBankDetails()");
		if (empId!= null && empBankDetailsDto != null) {
			BasicDetailsEntity basicDetailsEntity = basicDetailsRepository
						.findByIdAndIsDeleted(empId, false);
			if (basicDetailsEntity != null) {
				EmpBankDetailsEntity empBankDetailsEntity = transformDtoToEntity(empId, empBankDetailsDto);
				empBankDetailsEntity.setCreatedBy(userId);
				empBankDetailsEntity.setCreatedDate(new Timestamp(new java.util.Date().getTime()));
				EmpBankDetailsEntity empBankDetailEntityNew = empBankDetailRepository
							.saveAndFlush(empBankDetailsEntity);
				if (empBankDetailEntityNew != null) {
					LOGGER.info("Bank Details created with Id : {}", empBankDetailEntityNew
								.getId());
				} else {
					LOGGER.info("Bank Details not created for empBankDetailsDto : {}", empBankDetailsDto);
					throw new EmployeeDetailsException(ErrorMsgConst.EC102,
								ErrorMsgConst.EC102MESSAGE);
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
	public List<EmpBankDetailsDto> getAllBankDetails() {
		LOGGER.info("Inside EmpBankDetailsServiceImpl : getAllBankDetails() ");
		List<EmpBankDetailsDto> bankDetailsDtos = new ArrayList<>();
		List<EmpBankDetailsEntity> bankDetailsEntityList = empBankDetailRepository
					.findByIsDeleted(false);
		for (EmpBankDetailsEntity empBankDetailsEntity : bankDetailsEntityList) {
			EmpBankDetailsDto empBankDetailsDto = transformEntityToDto(empBankDetailsEntity);
			LOGGER.info("Bank Details created with Id: {}", empBankDetailsDto.getId());
			bankDetailsDtos.add(empBankDetailsDto);
		}
		LOGGER.info("All employee bank details List response : {} ", bankDetailsDtos);
		return bankDetailsDtos;
	}

	private EmpBankDetailsDto transformEntityToDto(EmpBankDetailsEntity empBankDetailsEntity) {
		EmpBankDetailsDto empBankDetailsDto = new EmpBankDetailsDto();
		empBankDetailsDto.setAccountHolderName(empBankDetailsEntity.getAccountHolderName());
		empBankDetailsDto.setAccountNumber(empBankDetailsEntity.getAccountNumber());
		empBankDetailsDto.setBankName(empBankDetailsEntity.getBankName());
		empBankDetailsDto.setBranchName(empBankDetailsEntity.getBranchName());
		empBankDetailsDto.setIfscCode(empBankDetailsEntity.getIfscCode());
		empBankDetailsDto.setId(empBankDetailsEntity.getId());
		empBankDetailsDto.setEmployeeId(empBankDetailsEntity.getEmployeeId());

		LOGGER.info("EmpBankDetailsDto created : {}", empBankDetailsDto);
		return empBankDetailsDto;

	}

	private EmpBankDetailsEntity transformDtoToEntity(String empId,
				EmpBankDetailsDto empBankDetailsDto) {
		EmpBankDetailsEntity empBankDetailsEntity = new EmpBankDetailsEntity();
		empBankDetailsEntity.setAccountHolderName(empBankDetailsDto.getAccountHolderName());
		empBankDetailsEntity.setAccountNumber(empBankDetailsDto.getAccountNumber());
		empBankDetailsEntity.setBankName(empBankDetailsDto.getBankName());
		empBankDetailsEntity.setBranchName(empBankDetailsDto.getBranchName());
		empBankDetailsEntity.setIfscCode(empBankDetailsDto.getIfscCode());
		empBankDetailsEntity.setId(empBankDetailsDto.getId());
		empBankDetailsEntity.setEmployeeId(empId);
		LOGGER.info("Entity created from DTO : {}", empBankDetailsEntity);
		return empBankDetailsEntity;

	}

	@Override
	public void updateEmpBankDetails(String empId, EmpBankDetailsDto empBankDetailsDto,
				String userId) {
		LOGGER.info("Inside EmpBankDetailsServiceImpl : updateEmpBankDetails()");
		if (empId!= null && empBankDetailsDto != null) {
			EmpBankDetailsEntity empBankDetailsEntity = empBankDetailRepository
						.findByEmployeeIdAndIsDeleted(empId, false);
			if (empBankDetailsEntity != null) {
				LOGGER.info("Inside empBankDetailsEntity :{}",empBankDetailsEntity);
				if (empBankDetailsEntity.getId().equals(empBankDetailsDto.getId())) {
					if (!empBankDetailsEntity.getAccountHolderName()
								.equals(empBankDetailsDto.getAccountHolderName())) {
						empBankDetailsEntity
									.setAccountHolderName(empBankDetailsDto.getAccountHolderName());
					}
					if (!empBankDetailsEntity.getAccountNumber()
								.equals(empBankDetailsDto.getAccountNumber())) {
						empBankDetailsEntity.setAccountNumber(empBankDetailsDto.getAccountNumber());
					}
					if (!empBankDetailsEntity.getBankName()
								.equals(empBankDetailsDto.getBankName())) {
						empBankDetailsEntity.setBankName(empBankDetailsDto.getBankName());
					}
					if (!empBankDetailsEntity.getBranchName()
								.equals(empBankDetailsDto.getBranchName())) {
						empBankDetailsEntity.setBranchName(empBankDetailsDto.getBranchName());
					}
					if (!empBankDetailsEntity.getIfscCode()
								.equals(empBankDetailsDto.getIfscCode())) {
						empBankDetailsEntity.setIfscCode(empBankDetailsDto.getIfscCode());
					}
					empBankDetailsEntity.setUpdatedBy(userId);
					empBankDetailsEntity
								.setUpdatedDate(new Timestamp(new java.util.Date().getTime()));
					EmpBankDetailsEntity empBankDetailsEntityNew = empBankDetailRepository
								.saveAndFlush(empBankDetailsEntity);
					if (empBankDetailsEntityNew != null) {
						LOGGER.info("Bank Details Updated With bank detail Id : {}", empBankDetailsEntityNew
									.getId());
					} else {
						LOGGER.info("Bank Details Not Updated With employee Id : {}", empId);
					}
				} else {
					LOGGER.error(ErrorMsgConst.EC102MESSAGE);
					throw new EmployeeDetailsException(ErrorMsgConst.EC102,
								ErrorMsgConst.EC102MESSAGE);
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
	public void deleteEmpBankDetailsWithBankDetailId(String bankDetailId, String userId) {
		LOGGER.info("Inside EmpBankDetailsServiceImpl : deleteEmpBankDetailsWithBankDetailId()");
		if (bankDetailId!= null) {
			EmpBankDetailsEntity empBankDetailsEntity = empBankDetailRepository
						.findByIdAndIsDeleted(bankDetailId, false);
			if (empBankDetailsEntity != null) {
				empBankDetailsEntity.setDeleted(true);
				empBankDetailsEntity.setUpdatedBy(userId);
				empBankDetailsEntity.setUpdatedDate(new Timestamp(new java.util.Date().getTime()));
				EmpBankDetailsEntity empBankDetailsEntityNew = empBankDetailRepository
							.saveAndFlush(empBankDetailsEntity);
				if (empBankDetailsEntityNew != null) {
					LOGGER.info("Bank Details Deleted With Id : {}", bankDetailId);
				} else {
					LOGGER.info("Bank Details Not Deleted With Id : {}", bankDetailId);
				}
			} else {
				LOGGER.error(ErrorMsgConst.EC102MESSAGE);
				throw new EmployeeDetailsException(ErrorMsgConst.EC102, ErrorMsgConst.EC102MESSAGE);
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC102MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC102, ErrorMsgConst.EC102MESSAGE);
		}
	}

	@Override
	public EmpBankDetailsDto getParticularEmpBankDetail(String empId) {
		LOGGER.info("Inside EmpBankDetailsServiceImpl : getParticularEmpBankDetail()");
		if (empId!= null) {
			EmpBankDetailsEntity empBankDetailsEntity = empBankDetailRepository
						.findByEmployeeIdAndIsDeleted(empId, false);
			if (empBankDetailsEntity != null) {
				EmpBankDetailsDto empBankDetailsDto = transformEntityToDto(empBankDetailsEntity);
				return empBankDetailsDto;
			} else {
				LOGGER.error(ErrorMsgConst.EC102MESSAGE);
				throw new EmployeeDetailsException(ErrorMsgConst.EC102, ErrorMsgConst.EC102MESSAGE);
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC102MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC102, ErrorMsgConst.EC102MESSAGE);
		}
	}

	@Override
	public void deleteEmpBankDetailsWithEmpId(String empId, String userId) {
		LOGGER.info("Inside EmpBankDetailsServiceImpl : deleteEmpBankDetailsWithEmpId()");
		if (empId!= null) {
			EmpBankDetailsEntity empBankDetailsEntity = empBankDetailRepository
						.findByEmployeeIdAndIsDeleted(empId, false);
			if (empBankDetailsEntity != null) {
				empBankDetailsEntity.setDeleted(true);
				empBankDetailsEntity.setUpdatedBy(userId);
				empBankDetailsEntity.setUpdatedDate(new Timestamp(new java.util.Date().getTime()));
				EmpBankDetailsEntity empBankDetailsEntityNew = empBankDetailRepository
							.saveAndFlush(empBankDetailsEntity);
				if (empBankDetailsEntityNew != null) {
					LOGGER.info("Bank Details Deleted With Id : {}", empId);
				} else {
					LOGGER.info("Bank Details Not Deleted With Id : {}", empId);
				}
			} else {
				LOGGER.error(ErrorMsgConst.EC102MESSAGE);
				throw new EmployeeDetailsException(ErrorMsgConst.EC102, ErrorMsgConst.EC102MESSAGE);
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC102MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC102, ErrorMsgConst.EC102MESSAGE);
		}
	}
}
