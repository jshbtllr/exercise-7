package com.exercise7.core.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Person {
	private String firstName;
	private String lastName;
	private String middleName;
	private String suffix;
	private String title;
	private Date birthday;

	public Person(){}
	public Person(String firstName, String lastName, String middleName, String suffix, String title, Date birthday) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.suffix = suffix;
		this.title = title;
		this.birthday = birthday;
	}

	@Column(name="first_name")
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String input) {
		this.firstName = input;
	}

	@Column(name="last_name")
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String input) {
		this.lastName = input;
	}

	@Column(name="middle_name")
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String input) {
		this.middleName = input;
	}

	@Column(name="suffix")
	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(String input) {
		this.suffix = input;
	}

	@Column(name="title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String input) {
		this.title = input;
	}	

	@Column(name="birthday")
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date input) {
		this.birthday = input;
	}
}