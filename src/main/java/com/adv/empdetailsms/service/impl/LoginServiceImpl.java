package com.adv.empdetailsms.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adv.empdetailsms.entity.LoginEntity;
import com.adv.empdetailsms.exception.EmployeeDetailsException;
import com.adv.empdetailsms.model.ErrorMsgConst;
import com.adv.empdetailsms.repository.LoginRepository;
import com.adv.empdetailsms.service.LoginService;

@Service(value = "loginServiceImpl")
public class LoginServiceImpl implements LoginService{ 

	@Autowired
	private LoginRepository loginRepository;
	
	private static final Logger LOGGER = LogManager.getLogger(LoginServiceImpl.class);
	
	@Override
	public 	String checkCredentials(String userId, String password) {
	LoginEntity loginEntity =	loginRepository.findByUserId(userId);

	if(loginEntity!=null) {
		if(loginEntity.getPassword().equals(password)) {
			return userId;
		}else {
			return "accessDenied";
		}
	}else {
		LOGGER.error(ErrorMsgConst.EC102MESSAGE);
		throw new EmployeeDetailsException(ErrorMsgConst.EC102, ErrorMsgConst.EC102MESSAGE);
	}
	
	}

}
