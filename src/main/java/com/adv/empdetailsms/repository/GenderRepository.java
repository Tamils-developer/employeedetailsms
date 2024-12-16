package com.adv.empdetailsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adv.empdetailsms.entity.GenderEntity;
@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, String> {

	List<GenderEntity> findByIsDeleted(boolean value);
}
