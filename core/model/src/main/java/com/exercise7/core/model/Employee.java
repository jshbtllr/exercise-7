package com.exercise7.core.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.GenerationType;
import java.util.Set;
import java.util.Date;

@Entity
@Table(name="Employee")
public class Employee extends BaseEntity {
	private Name name;
	private Address address;
	private Date birthday;
	private Float gradeWeightAverage;
	private Date hireDate;
	private Boolean employed;
	private Set <ContactInfo> contactInfo; 
	private Set <Roles> role;

	public Employee() {}
	public Employee(Name name, Address address, Date birthday, Float gradeWeightAverage, Date hireDate, Boolean employed, 
					Set <ContactInfo> contactInfo, Set <Roles> role) {
		this.name = name;
		this.address = address;
		this.birthday = birthday;
		this.gradeWeightAverage = gradeWeightAverage;
		this.hireDate = hireDate;
		this.employed = employed;
		this.contactInfo = contactInfo;
		this.role = role;
	}

	@Embedded
	public Name getName() {
		return this.name;
	}

	@Embedded
	public Address getAddress() {
		return this.address;
	}

	@Column(name="birthday")
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date input) {
		this.birthday = input;
	}

	@Column(name="gwa")
	public Float getGradeWeightAverage() {
		return this.gradeWeightAverage;
	}

	@Column(name="hire_date")
	public Date getHireDate() {
		return this.hireDate;
	}

	@Column(name="employed")
	public Boolean getEmployed() {
		return this.employed;
	}

	@ElementCollection(fetch=FetchType.LAZY)
	@CollectionTable(
		name="CONTACTINFO",
		joinColumns=@JoinColumn(name="employee_id"))
	public Set <ContactInfo> getContactInfo() {
		return this.contactInfo;
	}

	@ManyToMany(
		cascade=CascadeType.ALL,
		fetch=FetchType.LAZY)
	@JoinTable(
		name="employeerole",
		joinColumns=@JoinColumn(name="employee_id", referencedColumnName="id"),
		inverseJoinColumns=@JoinColumn(name="role_id", referencedColumnName="id"))
	public Set <Roles> getRole() {
		return this.role;
	}

	public void setName(Name input) {
		this.name = input;
	}


	public void setAddress(Address input) {
		this.address = input;
	}

	public void setGradeWeightAverage(Float input) {
		this.gradeWeightAverage = input;
	}

	public void setHireDate(Date input) {
		this.hireDate = input;
	}

	public void setEmployed(Boolean input) {
		this.employed = input;
	}

	public void setContactInfo(Set <ContactInfo> input) {
		this.contactInfo = input;
	}

	public void setRole(Set <Roles> input) {
		this.role = input;
	}

}