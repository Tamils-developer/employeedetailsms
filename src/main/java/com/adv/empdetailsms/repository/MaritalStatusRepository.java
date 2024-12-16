package com.adv.empdetailsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adv.empdetailsms.entity.MaritalStatusEntity;
@Repository
public interface MaritalStatusRepository extends JpaRepository<MaritalStatusEntity, String> {

	List<MaritalStatusEntity> findByIsDeleted(boolean value);
}
