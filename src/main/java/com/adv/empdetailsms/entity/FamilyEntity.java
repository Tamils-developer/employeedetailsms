package com.adv.empdetailsms.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "emp_contact_details")
@Getter
@Setter
@NoArgsConstructor
public class FamilyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "aadhar")
	private String aadhar;
	
	@Column(name = "mobile")
	private String mobile;
	

	@Column(name = "relation")
	private String relation;

	@Column(name = "is_nominee")
	private boolean isNominee;

	@Column(name = "percentage")
	private int percentage;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "emp_basic_details_id")
	private String employeeId;



	@Override
	public String toString() {
		return "FamilyEntity [id=" + id + ", name=" + name + ", dob=" + dob + ", aadhar=" + aadhar + ", mobile="
				+ mobile + ", relation=" + relation + ", isNominee=" + isNominee + ", percentage=" + percentage
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", updatedDate=" + updatedDate
				+ ", updatedBy=" + updatedBy + ", isDeleted=" + isDeleted + ", employeeId=" + employeeId + "]";
	}

}