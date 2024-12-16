package com.adv.empdetailsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adv.empdetailsms.entity.BasicDetailsEntity;

@Repository
public interface BasicDetailsRepository extends JpaRepository<BasicDetailsEntity, String> {

	BasicDetailsEntity findByIdAndIsDeleted(String employeeId, boolean isDeleted);
	
	List<BasicDetailsEntity> findAllByIsDeleted(boolean isDeleted);
	
	BasicDetailsEntity findAllAddressesByIdAndIsDeleted(String employeeId, boolean isDeleted);

}