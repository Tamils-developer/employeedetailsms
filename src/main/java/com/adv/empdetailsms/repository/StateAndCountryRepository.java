package com.adv.empdetailsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adv.empdetailsms.entity.StateAndCountryEntity;

@Repository
public interface StateAndCountryRepository extends JpaRepository<StateAndCountryEntity, Integer>{

	List<StateAndCountryEntity> findByCountry(String country);
	List<StateAndCountryEntity> findByState(String state);
}
