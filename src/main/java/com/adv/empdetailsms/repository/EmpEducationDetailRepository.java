package com.adv.empdetailsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adv.empdetailsms.entity.EmpEducationDetailEntity;

@Repository
public interface EmpEducationDetailRepository
			extends JpaRepository<EmpEducationDetailEntity, String> {

	List<EmpEducationDetailEntity> findByIsDeleted(boolean value);

	List<EmpEducationDetailEntity> findByIsDeletedAndEmployeeId(boolean value, String id);

	EmpEducationDetailEntity findByIsDeletedAndId(boolean value, String id);

}
