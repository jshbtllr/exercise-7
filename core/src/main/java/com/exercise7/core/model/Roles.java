package com.exercise7.core.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="ROLES")
public class Roles {
	
	private Long id;
	private String roleName;
	private String roleCode;

	public Roles() {}
	public Roles(String roleName, String roleCode) {
		this.roleName = roleName;
		this.roleCode = roleCode;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	public Long getId() {
		return this.id;
	}

	@Column(name="role_name")
	public String getRoleName() {
		return this.roleName;
	}

	@Column(name="role_code")
	public String getRoleCode() {
		return this.roleCode;
	}

	public void setId(Long input) {
		this.id = input;
	}

	public void setRoleName(String input) {
		this.roleName = input;
	}

	public void setRoleCode(String input) {
		this.roleCode = input;
	}


}