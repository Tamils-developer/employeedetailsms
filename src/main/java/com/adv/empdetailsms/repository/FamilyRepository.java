package com.adv.empdetailsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adv.empdetailsms.entity.FamilyEntity;

@Repository
public interface FamilyRepository extends JpaRepository<FamilyEntity, String> {

	List<FamilyEntity> findByEmployeeIdAndIsDeleted(String empId, boolean isDeleted);

	FamilyEntity findByIdAndIsDeleted(String id, boolean isDeleted);
}
