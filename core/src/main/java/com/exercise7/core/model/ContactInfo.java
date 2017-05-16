package com.exercise7.core.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

@Entity
@Table(name="CONTACTINFO")
public class ContactInfo {
	
	private Long id;
	private String infoType;
	private String infoDetail;
	private Employee parentEmployee;

	public ContactInfo() {}
	public ContactInfo(String infoType, String infoDetail) {
		this.infoType = infoType;
		this.infoDetail = infoDetail;
	}

	@Id @GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public Long getId() {
		return this.id;
	}

	@Column(name="contact_info_type")
	public String getInfoType() {
		return this.infoType;
	}

	@Column(name="contact_information")
	public String getInfoDetail() {
		return this.infoDetail;
	}

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="employee_id", insertable=false, updatable=false)
	public Employee getParentEmployee() {
		return this.parentEmployee;
	}

	public void setId(Long input) {
		this.id = input;
	}

	public void setInfoType(String input) {
		this.infoType = input;
	}

	public void setInfoDetail(String input) {
		this.infoDetail = input;
	}

	public void setParentEmployee(Employee input) {
		this.parentEmployee = input;
	}
	
}