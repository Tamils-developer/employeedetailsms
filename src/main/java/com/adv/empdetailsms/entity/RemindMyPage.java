package com.adv.empdetailsms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "remind_my_page")
@Getter
@Setter
@ToString
public class RemindMyPage {

	
	@Id
	private String id;

	@Column(name = "basic_details")
	private String basic_details;

	@Column(name = "contact_details")
	private Boolean contact_details;
	
	@Column(name = "educational_details")
	private String educational_details;

	@Column(name = "bank_details")
	private Boolean bank_details;
	
	@Column(name = "documents_upload")
	private String documents_upload;

	@Column(name = "experience_details")
	private Boolean experience_details;
	
	@Column(name = "nominee_details")
	private Boolean nominee_details;
	
	@Column(name = "reguest_for_approval")
	private Boolean reguestForApproval;
	
	@Column(name = "user_id")
	private String user_id;
	
}
