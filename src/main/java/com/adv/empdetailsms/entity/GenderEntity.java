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

@Entity
@Table(name = "Gender")
@Getter
@Setter
@NoArgsConstructor
public class GenderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
}
