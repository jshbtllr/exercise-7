package com.exercise7.core.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="ROLES")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Roles extends BaseEntity {
	
	private String roleName;
	private String roleCode;

	public Roles() {}
	public Roles(String roleName, String roleCode) {
		this.roleName = roleName;
		this.roleCode = roleCode;
	}

	@Column(name="role_name")
	public String getRoleName() {
		return this.roleName;
	}

	@Column(name="role_code")
	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleName(String input) {
		this.roleName = input;
	}

	public void setRoleCode(String input) {
		this.roleCode = input;
	}
}