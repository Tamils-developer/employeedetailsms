package com.adv.empdetailsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adv.empdetailsms.entity.BankEntity;


@Repository
public interface BankNameRepository  extends JpaRepository<BankEntity, String>  {

	List<BankEntity> findByIsDeleted(boolean value);
}
