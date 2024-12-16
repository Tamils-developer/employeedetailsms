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
@Table(name = "department")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DepartmentEntity {

	@Id
	private String id;

	@Column(name = "department")
	private String department;

	@Column(name = "is_deleted")
	private Boolean isDeleted;
}
