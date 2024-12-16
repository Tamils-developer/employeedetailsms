package com.adv.empdetailsms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
@NoArgsConstructor
public class EmpBankDetailsDto {

	private String id;

	private String accountHolderName;

	private String accountNumber;

	private String ifscCode;

	private String bankName;

	private String branchName;

	private boolean isDeleted;

	private String employeeId;
}