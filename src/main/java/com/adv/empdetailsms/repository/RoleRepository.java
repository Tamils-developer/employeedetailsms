package com.adv.empdetailsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adv.empdetailsms.entity.RoleEntity;
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

}
