package com.adv.empdetailsms.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "emp_basic_details")
@Getter
@Setter
@NoArgsConstructor
public class BasicDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "alternate_mobile ")
	private String alterMobileNum;

	@Column(name = "date_of_birth")
	private Date dob;

	@Column(name = " gender")
	private String gender;

	@Column(name = "aadhar_number")
	private String aadharNum;

	@Column(name = "pan_number")
	private String panNum;
	
	@Column(name = "passport_number")
	private String passportNum;

	@Column(name = "marital_status")
	private String maritalStatus;

	@Column(name = "blood_group")
	private String bloodGroup;

	@Column(name = "UAN_number")
	private String uanNum;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "user_id")
	private String user_id;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "emp_address_details_mapping", joinColumns = {
				@JoinColumn(name = "emp_basic_details_id") }, inverseJoinColumns = {
							@JoinColumn(name = "address_details_id") })
	private List<AddressEntity> addressEntities;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_basic_details_id", referencedColumnName = "id")
	private List<OrganizationDetailsEntity> organizationDetailsEntities;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_basic_details_id", referencedColumnName = "id")
	private List<EmpEducationDetailEntity> empEducationDetailEntities;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id", referencedColumnName = "employee_basic_details_id")
	private EmpBankDetailsEntity empBankDetailsEntity;

	@Override
	public String toString() {
		return "BasicDetailsEntity [id=" + id + ", firstName=" + firstName + ", lastName="
					+ lastName + ", email=" + email + ", mobile=" + mobile + ", alterMobileNum="
					+ alterMobileNum + ", dob=" + dob + ", gender=" + gender + ", aadharNum="
					+ aadharNum + ", panNum=" + panNum +", passportNum="+ passportNum+ ", maritalStatus=" + maritalStatus
					+ ", bloodGroup=" + bloodGroup + ", uanNum=" + uanNum + ", isDeleted="
					+ isDeleted + ", createdDate=" + createdDate + ", createdBy=" + createdBy
					+ ", updatedDate=" + updatedDate + ", updatedBy=" + updatedBy + "]";
	}

	
}
