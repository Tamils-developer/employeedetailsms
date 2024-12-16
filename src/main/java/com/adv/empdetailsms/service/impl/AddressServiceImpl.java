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
import com.adv.empdetailsms.entity.AddressEntity;
import com.adv.empdetailsms.entity.BasicDetailsEntity;
import com.adv.empdetailsms.exception.EmployeeDetailsException;
import com.adv.empdetailsms.model.ErrorMsgConst;
import com.adv.empdetailsms.repository.AddressRepository;
import com.adv.empdetailsms.repository.BasicDetailsRepository;
import com.adv.empdetailsms.service.AddressService;

@Service(value = "addressServiceImpl")
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private BasicDetailsRepository basicDetailsRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);

	@Override
	public void deleteParticularAddress(String addressId, String userId) {
		LOGGER.info("Inside AddressServiceImpl : deleteParticularAddress() ");
		if (addressId != null) {
			AddressEntity addressEntity = addressRepository.findByIdAndIsDeleted(addressId, false);
			LOGGER.info("Delete request entity retrived from DB : {} ", addressEntity);
			if (addressEntity != null) {
				addressEntity.setDeleted(true);
				addressEntity.setUpdatedBy(userId);
				addressEntity.setUpdatedDate(new Timestamp(new Date().getTime()));
				AddressEntity deletedAddressDetailsEntity = addressRepository.saveAndFlush(addressEntity);
				if (deletedAddressDetailsEntity != null) {
					LOGGER.info("Deleted address details entity : {} ", addressEntity);
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

	private List<AddressDto> transformAddressDtlsEntityToDto(BasicDetailsEntity basicDetailsEntity) {
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
		return addressDtos;
	}

	@Override
	public List<AddressDto> getAllAddressesOfParticularEmp(String employeeId, String userId) {
		LOGGER.info("Inside AddressServiceImpl : getAllAddressesOfParticularEmp() ");
		if (employeeId != null) {
			BasicDetailsEntity basicDetailsEntity = basicDetailsRepository.findAllAddressesByIdAndIsDeleted(employeeId,
					false);
			LOGGER.info("Requested basic details Addresses retrived from DB: {} ", basicDetailsEntity);
			if (basicDetailsEntity != null) {
				List<AddressDto> addressDtos = transformAddressDtlsEntityToDto(basicDetailsEntity);
				return addressDtos;
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
