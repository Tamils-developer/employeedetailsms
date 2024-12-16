package com.adv.empdetailsms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="role_or_desigination")
@ToString
public class RoleEntity {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="role_or_desigination")
	private String role;
}