package com.adv.empdetailsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adv.empdetailsms.entity.BloodGroupEntity;

@Repository
public interface BloodGroupRepository extends JpaRepository<BloodGroupEntity, String> {

	List<BloodGroupEntity> findByIsDeleted(boolean value);

}
