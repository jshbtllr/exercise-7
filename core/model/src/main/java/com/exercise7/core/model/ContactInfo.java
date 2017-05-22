package com.exercise7.core.model;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContactInfo {
	
	private String infoType;
	private String infoDetail;
	private Employee parentEmployee;

	public ContactInfo() {}
	public ContactInfo(String infoType, String infoDetail) {
		this.infoType = infoType;
		this.infoDetail = infoDetail;
	}

	@Column(name="contact_info_type")
	public String getInfoType() {
		return this.infoType;
	}

	@Column(name="contact_information")
	public String getInfoDetail() {
		return this.infoDetail;
	}

	public void setInfoType(String input) {
		this.infoType = input;
	}

	public void setInfoDetail(String input) {
		this.infoDetail = input;
	}
}