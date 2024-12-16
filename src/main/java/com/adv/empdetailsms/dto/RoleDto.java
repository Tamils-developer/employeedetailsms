package com.adv.empdetailsms.dto;

import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RoleDto implements Comparable<RoleDto> {

	private int id;
	private String role;

	@Override
	public int hashCode() {
		return Objects.hash(role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleDto other = (RoleDto) obj;
		return Objects.equals(role, other.role);
	}

	@Override
	public int compareTo(RoleDto dto) {
		return this.role.compareTo(dto.getRole());
	}

}
