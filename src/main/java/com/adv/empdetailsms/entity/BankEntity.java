package com.adv.empdetailsms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "bank")
@Getter
@Setter
@ToString
public class BankEntity {

	@Id
	private String id;

	@Column(name = "name")
	private String bankName;

	@Column(name = "is_deleted")
	private Boolean isDeleted;
}
