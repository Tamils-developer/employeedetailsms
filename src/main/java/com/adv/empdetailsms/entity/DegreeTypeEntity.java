package com.adv.empdetailsms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "degree_type")
@Getter
@Setter
@ToString
@NoArgsConstructor

public class DegreeTypeEntity {

	@Id
	private String id;

	@Column(name = "degree_type")
	private String degreeType;

	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
}
