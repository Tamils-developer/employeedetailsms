package com.adv.empdetailsms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class RemindMyPageDto {

	
	private String basic_details;
	
	private Boolean contact_details;
	
	private String educational_details;
	
	private Boolean bank_details;
	
	private String documents_upload;
	
	private Boolean experience_details;
	
	private Boolean nominee_details;
	
	private String user_id;
}
