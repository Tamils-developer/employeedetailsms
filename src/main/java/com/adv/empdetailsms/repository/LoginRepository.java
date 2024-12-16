package com.adv.empdetailsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adv.empdetailsms.entity.LoginEntity;

public interface LoginRepository extends JpaRepository<LoginEntity,String>{
	
	LoginEntity	findByUserId(String userId);
}
