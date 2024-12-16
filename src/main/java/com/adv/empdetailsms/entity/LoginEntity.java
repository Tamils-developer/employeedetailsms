package com.adv.empdetailsms.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "login_details")
@Getter
@Setter
@ToString
public class LoginEntity {
	
	@Id
	private String id;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "password")
	private String password;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id",referencedColumnName = "remind_my_page_id")
	private RemindMyPage remind_my_page_id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id",referencedColumnName = "basic_details_id")
	private BasicDetailsEntity basic_details_id;
}
