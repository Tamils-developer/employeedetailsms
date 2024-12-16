package com.adv.empdetailsms.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adv.empdetailsms.dto.AddressDto;
import com.adv.empdetailsms.dto.BasicDetailsDto;
import com.adv.empdetailsms.entity.AddressEntity;
import com.adv.empdetailsms.entity.BasicDetailsEntity;
import com.adv.empdetailsms.exception.EmployeeDetailsException;
import com.adv.empdetailsms.model.ErrorMsgConst;
import com.adv.empdetailsms.repository.BasicDetailsRepository;
import com.adv.empdetailsms.service.BasicDetailsService;

@Service(value = "basicDetaisServiceImpl")
public class BasicDetailsServiceImpl implements BasicDetailsService {

	@Autowired
	private BasicDetailsRepository basicDetailsRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(BasicDetailsServiceImpl.class);

	public String createEmployee(BasicDetailsDto basicDetailsDto, String userId) {
		LOGGER.info("Inside BasicDetailsServiceImpl : createEmployee() ");
		if (basicDetailsDto != null) {
			BasicDetailsEntity basicDetailsEntity = transformBasicDetailsDtoToEntity(basicDetailsDto, userId);
			BasicDetailsEntity employeeBasicDetailsEntity = basicDetailsRepository
						.saveAndFlush(basicDetailsEntity);
			if (employeeBasicDetailsEntity != null) {
				LOGGER.info("Created basic details :: employee Id :{} and employee basic detail entity:{}", employeeBasicDetailsEntity
							.getId(), basicDetailsEntity);
				return employeeBasicDetailsEntity.getId();
			} else {
				LOGGER.info("Employee details not created for the employee dto : {}", basicDetailsDto);
				throw new EmployeeDetailsException(ErrorMsgConst.EC103, ErrorMsgConst.EC103MESSAGE);
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC103MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC103, ErrorMsgConst.EC103MESSAGE);
		}
	}

	private BasicDetailsEntity transformBasicDetailsDtoToEntity(BasicDetailsDto basicDetailsDto,
				String userId) {
		BasicDetailsEntity basicDetailsEntity = setBasicDetailsEntity(basicDetailsDto, userId);
		if (basicDetailsDto.getAddresses() != null) {
			List<AddressDto> addressDtos = basicDetailsDto.getAddresses();
			List<AddressEntity> addressEntities = new ArrayList<AddressEntity>();
			if (!addressDtos.isEmpty()) {
				for (AddressDto addressDto : addressDtos) {
					AddressEntity addressEntity = new AddressEntity();
					addressEntity.setDoorNo(addressDto.getDoorNo());
					addressEntity.setStreet(addressDto.getStreet());
					addressEntity.setLandMark(addressDto.getLandMark());
					addressEntity.setCity(addressDto.getCity());
					addressEntity.setDistrict(addressDto.getDistrict());
					addressEntity.setPincode(addressDto.getPincode());
					addressEntity.setState(addressDto.getState());
					addressEntity.setCountry(addressDto.getCountry());
					addressEntity.setType(addressDto.getType());
					addressEntity.setSameAsCommu(addressDto.isSameAsCommu());
					addressEntity.setCreatedBy(userId);
					addressEntity.setCreatedDate(new Timestamp(new Date().getTime()));
					addressEntities.add(addressEntity);
				}
			} else {
				LOGGER.error(ErrorMsgConst.EC103MESSAGE);
				throw new EmployeeDetailsException(ErrorMsgConst.EC103, ErrorMsgConst.EC103MESSAGE);
			}
			basicDetailsEntity.setAddressEntities(addressEntities);
			return basicDetailsEntity;
		} else {
			LOGGER.error(ErrorMsgConst.EC103MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC103, ErrorMsgConst.EC103MESSAGE);
		}
	}

	private BasicDetailsEntity setBasicDetailsEntity(BasicDetailsDto basicDetailsDto,
				String userId) {
		BasicDetailsEntity basicDetailsEntity = new BasicDetailsEntity();
		basicDetailsEntity.setFirstName(basicDetailsDto.getFirstName());
		basicDetailsEntity.setLastName(basicDetailsDto.getLastName());
		basicDetailsEntity.setEmail(basicDetailsDto.getEmail());
		basicDetailsEntity.setMobile(basicDetailsDto.getMobileNum());
		basicDetailsEntity.setAlterMobileNum(basicDetailsDto.getAlterMobileNum());
		basicDetailsEntity.setDob(basicDetailsDto.getDob());
		basicDetailsEntity.setGender(basicDetailsDto.getGender());
		basicDetailsEntity.setBloodGroup(basicDetailsDto.getBloodGroup());
		basicDetailsEntity.setAadharNum(basicDetailsDto.getAadharNum());
		basicDetailsEntity.setPanNum(basicDetailsDto.getPanNum());
		basicDetailsEntity.setPassportNum(basicDetailsDto.getPassportNum());
		basicDetailsEntity.setMaritalStatus(basicDetailsDto.getMaritalStatus());
		basicDetailsEntity.setCreatedBy(userId);
		basicDetailsEntity.setCreatedDate(new Timestamp(new Date().getTime()));
		return basicDetailsEntity;
	}

	@Override
	public BasicDetailsDto getParticularEmployee(String employeeId) {
		LOGGER.info("Inside BasicDetailsServiceImpl : getParticularEmployee() ");
		if (employeeId != null) {
			BasicDetailsEntity basicDetailsEntity = basicDetailsRepository
						.findByIdAndIsDeleted(employeeId, false);
			LOGGER.info("Requested basic details entity retrived from DB: {} ", basicDetailsEntity);
			if (basicDetailsEntity != null) {
				BasicDetailsDto basicDetailsDto = transformBasicDtlsEntityToDto(basicDetailsEntity);
				return basicDetailsDto;
			} else {
				LOGGER.error(ErrorMsgConst.EC102MESSAGE);
				throw new EmployeeDetailsException(ErrorMsgConst.EC102, ErrorMsgConst.EC102MESSAGE);
			}
		} else {
			LOGGER.error(ErrorMsgConst.EC102MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC102, ErrorMsgConst.EC102MESSAGE);
		}
	}

	private BasicDetailsDto transformBasicDtlsEntityToDto(BasicDetailsEntity basicDetailsEntity) {
		BasicDetailsDto basicDetailsDto = setBasicDetailsDto(basicDetailsEntity);
		List<AddressEntity> addressEntities = basicDetailsEntity.getAddressEntities();
		List<AddressDto> addressDtos = new ArrayList<AddressDto>();
		for (AddressEntity addressEntity : addressEntities) {
			if (!addressEntity.isDeleted()) {
				AddressDto addressDto = new AddressDto();
				addressDto.setId(addressEntity.getId());
				addressDto.setDoorNo(addressEntity.getDoorNo());
				addressDto.setStreet(addressEntity.getStreet());
				addressDto.setLandMark(addressEntity.getLandMark());
				addressDto.setCity(addressEntity.getCity());
				addressDto.setDistrict(addressEntity.getDistrict());
				addressDto.setPincode(addressEntity.getPincode());
				addressDto.setState(addressEntity.getState());
				addressDto.setCountry(addressEntity.getCountry());
				addressDto.setType(addressEntity.getType());
				addressDto.setSameAsCommu(addressEntity.isSameAsCommu());
				addressDtos.add(addressDto);
			}
		}
		basicDetailsDto.setAddresses(addressDtos);
		return basicDetailsDto;
	}

	private BasicDetailsDto setBasicDetailsDto(BasicDetailsEntity basicDetailsEntity) {
		BasicDetailsDto basicDetailsDto = new BasicDetailsDto();
		basicDetailsDto.setId(basicDetailsEntity.getId());
		basicDetailsDto.setFirstName(basicDetailsEntity.getFirstName());
		basicDetailsDto.setLastName(basicDetailsEntity.getLastName());
		basicDetailsDto.setEmail(basicDetailsEntity.getEmail());
		basicDetailsDto.setMobileNum(basicDetailsEntity.getMobile());
		basicDetailsDto.setAlterMobileNum(basicDetailsEntity.getAlterMobileNum());
		basicDetailsDto.setDob(basicDetailsEntity.getDob());
		basicDetailsDto.setGender(basicDetailsEntity.getGender());
		basicDetailsDto.setBloodGroup(basicDetailsEntity.getBloodGroup());
		basicDetailsDto.setAadharNum(basicDetailsEntity.getAadharNum());
		basicDetailsDto.setPanNum(basicDetailsEntity.getPanNum());
		basicDetailsDto.setPassportNum(basicDetailsEntity.getPassportNum());
		basicDetailsDto.setMaritalStatus(basicDetailsEntity.getMaritalStatus());
		return basicDetailsDto;
	}

	@Override
	public void updateParticularEmpDetails(String employeeId, BasicDetailsDto basicDetailsDto,
				String userId) {
		LOGGER.info("Inside BasicDetailsServiceImpl : updateParticularEmpDetails() ");
		if (employeeId != null && basicDetailsDto != null
					&& basicDetailsDto.getAddresses() != null) {
			BasicDetailsEntity basicDetailsEntity = basicDetailsRepository
						.findByIdAndIsDeleted(employeeId, false);
			if (basicDetailsEntity != null && employeeId.equals(basicDetailsEntity.getId())) {
				LOGGER.info("update request  Employee Id retrived from DB :{}", basicDetailsEntity
							.getId());
				compareAndSetBasicDtlsEntityAndDto(basicDetailsDto, basicDetailsEntity);
				List<AddressDto> addressDtos = basicDetailsDto.getAddresses();
				if (!addressDtos.isEmpty()) {
					List<AddressEntity> addressEntities = basicDetailsEntity.getAddressEntities();
					for (AddressDto addressDto : addressDtos) {
						for (AddressEntity addressEntity : addressEntities) {
							if (addressDto.getId() != null) {
								if (addressDto.getId().equals(addressEntity.getId())) {
									compareAddressDtoAndEntity(addressDto, addressEntity);
									addressEntity.setUpdatedBy(userId);
									addressEntity.setUpdatedDate(new Timestamp(
												new Date().getTime()));
									break;
								} else {
									LOGGER.error(ErrorMsgConst.EC101MESSAGE);
								}
							} else {
								setNewAddress(basicDetailsDto, addressEntities, addressDto, userId);
								break;
							}
						}
					}
				} else {
					LOGGER.error(ErrorMsgConst.EC103MESSAGE);
				}
				basicDetailsEntity.setUpdatedBy(userId);
				basicDetailsEntity.setUpdatedDate(new Timestamp(new Date().getTime()));
				BasicDetailsEntity basicDetailEntityFromDB = basicDetailsRepository
							.saveAndFlush(basicDetailsEntity);
				if (basicDetailEntityFromDB != null) {
					LOGGER.info("Updated Basic details entity :{}", basicDetailEntityFromDB);
				} else {
					LOGGER.error(ErrorMsgConst.EC101MESSAGE);
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

	private void setNewAddress(BasicDetailsDto basicDetailsDto, List<AddressEntity> addressEntities,
				AddressDto addressDto, String userId) {
		AddressEntity newAddressEntity = new AddressEntity();
		newAddressEntity.setDoorNo(addressDto.getDoorNo());
		newAddressEntity.setLandMark(addressDto.getLandMark());
		newAddressEntity.setStreet(addressDto.getStreet());
		newAddressEntity.setDistrict(addressDto.getDistrict());
		newAddressEntity.setCity(addressDto.getCity());
		newAddressEntity.setPincode(addressDto.getPincode());
		newAddressEntity.setState(addressDto.getState());
		newAddressEntity.setCountry(addressDto.getCountry());
		newAddressEntity.setType(addressDto.getType());
		newAddressEntity.setSameAsCommu(addressDto.isSameAsCommu());
		newAddressEntity.setCreatedBy(userId);
		newAddressEntity.setCreatedDate(new Timestamp(new Date().getTime()));
		addressEntities.add(newAddressEntity);
	}

	private void compareAddressDtoAndEntity(AddressDto addressDto, AddressEntity addressEntity) {
		if (!addressDto.getDoorNo().equalsIgnoreCase(addressEntity.getDoorNo())) {
			addressEntity.setDoorNo(addressDto.getDoorNo());
		}
		if (!addressDto.getStreet().equalsIgnoreCase(addressEntity.getStreet())) {
			addressEntity.setStreet(addressDto.getStreet());
		}
		if (!addressDto.getLandMark().equalsIgnoreCase(addressEntity.getLandMark())) {
			addressEntity.setLandMark(addressDto.getLandMark());
		}
		if (!addressDto.getCity().equalsIgnoreCase(addressEntity.getCity())) {
			addressEntity.setCity(addressDto.getCity());
		}
		if (!addressDto.getDistrict().equalsIgnoreCase(addressEntity.getDistrict())) {
			addressEntity.setDistrict(addressDto.getDistrict());
		}
		if (!addressDto.getPincode().equals(addressEntity.getPincode())) {
			addressEntity.setPincode(addressDto.getPincode());
		}
		if (!addressDto.getState().equalsIgnoreCase(addressEntity.getState())) {
			addressEntity.setState(addressDto.getState());
		}
		if (!addressDto.getCountry().equalsIgnoreCase(addressEntity.getCountry())) {
			addressEntity.setCountry(addressDto.getCountry());
		}
		if (!addressDto.getType().equalsIgnoreCase(addressEntity.getType())) {
			addressEntity.setType(addressDto.getType());
		}
		if (addressDto.isSameAsCommu() != addressEntity.isSameAsCommu()) {
			addressEntity.setSameAsCommu(addressDto.isSameAsCommu());
		}
	}

	private void compareAndSetBasicDtlsEntityAndDto(BasicDetailsDto basicDetailDto,
				BasicDetailsEntity basicDetailsEntity) {
		if (!basicDetailDto.getFirstName().equalsIgnoreCase(basicDetailsEntity.getFirstName())) {
			basicDetailsEntity.setFirstName(basicDetailDto.getFirstName());
		}
		if (!basicDetailDto.getLastName().equalsIgnoreCase(basicDetailsEntity.getLastName())) {
			basicDetailsEntity.setLastName(basicDetailDto.getLastName());
		}
		if (!basicDetailDto.getDob().equals(basicDetailsEntity.getDob())) {
			basicDetailsEntity.setDob(basicDetailDto.getDob());
		}
		if (!basicDetailDto.getEmail().equalsIgnoreCase(basicDetailsEntity.getEmail())) {
			basicDetailsEntity.setEmail(basicDetailDto.getEmail());
		}
		if (!basicDetailDto.getAadharNum().equals(basicDetailsEntity.getAadharNum())) {
			basicDetailsEntity.setAadharNum(basicDetailDto.getAadharNum());
		}
		if (!basicDetailDto.getPanNum().equals(basicDetailsEntity.getPanNum())) {
			basicDetailsEntity.setPanNum(basicDetailDto.getPanNum());
		}
		if (!basicDetailDto.getPassportNum().equals(basicDetailsEntity.getPassportNum())) {
			basicDetailsEntity.setPassportNum(basicDetailDto.getPassportNum());
		}
		if (!basicDetailDto.getMobileNum().equals(basicDetailsEntity.getMobile())) {
			basicDetailsEntity.setMobile(basicDetailDto.getMobileNum());
		}
		if (!basicDetailDto.getAlterMobileNum().equals(basicDetailsEntity.getAlterMobileNum())) {
			basicDetailsEntity.setAlterMobileNum(basicDetailDto.getAlterMobileNum());
		}
		if (!basicDetailDto.getGender().equalsIgnoreCase(basicDetailsEntity.getGender())) {
			basicDetailsEntity.setGender(basicDetailDto.getGender());
		}
		if (!basicDetailDto.getBloodGroup().equalsIgnoreCase(basicDetailsEntity.getBloodGroup())) {
			basicDetailsEntity.setBloodGroup(basicDetailDto.getBloodGroup());
		}
		if (!basicDetailDto.getMaritalStatus()
					.equalsIgnoreCase(basicDetailsEntity.getMaritalStatus())) {
			basicDetailsEntity.setMaritalStatus(basicDetailDto.getMaritalStatus());
		}

	}

	@Override
	public void deleteParticularEmployee(String employeeId, String userId) {
		LOGGER.info("Inside BasicDetailsServiceImpl : deleteParticularEmployee() ");
		if (employeeId != null) {
			BasicDetailsEntity basicDetailsEntity = basicDetailsRepository
						.findByIdAndIsDeleted(employeeId, false);
			LOGGER.info("Delete request entity retrived from DB : {} ", basicDetailsEntity);
			if (basicDetailsEntity != null) {
				basicDetailsEntity.setDeleted(true);
				basicDetailsEntity.setUpdatedBy(userId);
				basicDetailsEntity.setUpdatedDate(new Timestamp(new Date().getTime()));
				List<AddressEntity> addressEntities = basicDetailsEntity.getAddressEntities();
				for (AddressEntity addressEntity : addressEntities) {
					addressEntity.setDeleted(true);
					addressEntity.setUpdatedBy(userId);
					addressEntity.setUpdatedDate(new Timestamp(new Date().getTime()));
				}
				BasicDetailsEntity deletedBasicDetailsEntity = basicDetailsRepository
							.saveAndFlush(basicDetailsEntity);
				if (deletedBasicDetailsEntity != null) {
					LOGGER.info("Deleted basicdetails entity : {} ", basicDetailsEntity);
				} else {
					LOGGER.error(ErrorMsgConst.EC101MESSAGE);
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
	public List<BasicDetailsDto> getAllEmployees() {
		LOGGER.info("Inside BasicDetailsServiceImpl : getAllEmployees() ");
		List<BasicDetailsEntity> listOfBasicDetailsEntity = basicDetailsRepository
					.findAllByIsDeleted(false);
		List<BasicDetailsDto> listOfBasicDetailsDtos = new ArrayList<>();
		if (!listOfBasicDetailsEntity.isEmpty()) {
			for (BasicDetailsEntity basicDetailsEntity : listOfBasicDetailsEntity) {
				BasicDetailsDto basicDetailsDto = setBasicDetailsDto(basicDetailsEntity);
				List<AddressEntity> addressEntities = basicDetailsEntity.getAddressEntities();
				List<AddressDto> addressDtos = new ArrayList<AddressDto>();
				for (AddressEntity addressEntity : addressEntities) {
					if (!addressEntity.isDeleted()) {
						AddressDto addressDto = new AddressDto();
						addressDto.setId(addressEntity.getId());
						addressDto.setDoorNo(addressEntity.getDoorNo());
						addressDto.setStreet(addressEntity.getStreet());
						addressDto.setLandMark(addressEntity.getLandMark());
						addressDto.setCity(addressEntity.getCity());
						addressDto.setDistrict(addressEntity.getDistrict());
						addressDto.setPincode(addressEntity.getPincode());
						addressDto.setState(addressEntity.getState());
						addressDto.setCountry(addressEntity.getCountry());
						addressDto.setType(addressEntity.getType());
						addressDto.setSameAsCommu(addressEntity.isSameAsCommu());
						addressDtos.add(addressDto);
					}
				}
				basicDetailsDto.setAddresses(addressDtos);
				listOfBasicDetailsDtos.add(basicDetailsDto);
			}
			return listOfBasicDetailsDtos;
		} else {
			LOGGER.error(ErrorMsgConst.EC103MESSAGE);
			throw new EmployeeDetailsException(ErrorMsgConst.EC103, ErrorMsgConst.EC103MESSAGE);
		}
	}
}
