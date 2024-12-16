package com.adv.empdetailsms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "marital_status")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class MaritalStatusEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(name = "marital_status")
	private String martialStatus;
	
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
}
