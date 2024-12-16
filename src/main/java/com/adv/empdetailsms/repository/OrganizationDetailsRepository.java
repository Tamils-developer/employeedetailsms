package com.adv.empdetailsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adv.empdetailsms.entity.OrganizationDetailsEntity;

@Repository
public interface OrganizationDetailsRepository extends JpaRepository<OrganizationDetailsEntity, String> {

	List<OrganizationDetailsEntity> findByEmployeeIdAndIsDeleted(String empId, boolean isDeleted);

	OrganizationDetailsEntity findByIdAndIsDeleted(String empId, boolean isDeleted);
}
