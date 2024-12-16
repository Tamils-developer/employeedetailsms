
package com.adv.empdetailsms.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address_details")
@Getter
@Setter
@NoArgsConstructor
public class AddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(name = "door_no")
	private String doorNo;

	@Column(name = "street")
	private String street;

	@Column(name = "pincode")
	private String pincode;

	@Column(name = "landmark")
	private String landMark;

	@Column(name = "city")
	private String city;

	@Column(name = "district")
	private String district;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;
	
	@Column(name = "type")
	private String type;
	
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
	
	@Column(name = "isSameAsCommu")
	private boolean sameAsCommu;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "addressEntities")
	private List<BasicDetailsEntity> basicDeatilsEntities;


	@Override
	public String toString() {
		return "AddressEntity [id=" + id + ", doorNo=" + doorNo + ", street=" + street + ", pincode=" + pincode
				+ ", landMark=" + landMark + ", city=" + city + ", district=" + district + ", state=" + state
				+ ", country=" + country + ", type=" + type + ", isDeleted=" + isDeleted + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + ", updatedDate=" + updatedDate + ", updatedBy=" + updatedBy
				+ ", sameAsCommu=" + sameAsCommu + "]";
	}
	

}
