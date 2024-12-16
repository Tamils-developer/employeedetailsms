package com.adv.empdetailsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adv.empdetailsms.entity.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, String> {

	AddressEntity findByIdAndIsDeleted(String addressId, boolean isDeleted);

}