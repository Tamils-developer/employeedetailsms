package com.adv.empdetailsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adv.empdetailsms.entity.EmpBankDetailsEntity;

@Repository
public interface EmpBankDetailRepository  extends JpaRepository<EmpBankDetailsEntity, String>{

	List<EmpBankDetailsEntity>	findByIsDeleted(boolean value);
	
	EmpBankDetailsEntity findByEmployeeIdAndIsDeleted(String empId,boolean value);
	
	EmpBankDetailsEntity findByIdAndIsDeleted(String bankdetailId,boolean value);
	
}
