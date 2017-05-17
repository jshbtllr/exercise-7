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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="Employee")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Employee {
	private Long id;
	private String lastName;
	private String firstName;
	private String middleName;
	private String suffix;
	private String title;
	private Address address;
	private Date birthday;
	private Float gradeWeightAverage;
	private Date hireDate;
	private Boolean employed;
	private Set <ContactInfo> contactInfo; 
	private Set <Roles> role;

	public Employee() {}
	public Employee(String lastName, String firstName, String middleName, String suffix, String title, 
					Address address, Date birthday, Float gradeWeightAverage, Date hireDate, Boolean employed, 
					Set <ContactInfo> contactInfo, Set <Roles> role) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.suffix = suffix;
		this.title = title;
		this.address = address;
		this.birthday = birthday;
		this.gradeWeightAverage = gradeWeightAverage;
		this.hireDate = hireDate;
		this.employed = employed;
		this.contactInfo = contactInfo;
		this.role = role;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	public Long getId() {
		return this.id;
	}

	@Column(name="last_name")
	public String getLastName() {
		return this.lastName;
	}

	@Column(name="first_name")
	public String getFirstName() {
		return this.firstName;
	}

	@Column(name="middle_name")
	public String getMiddleName() {
		return this.middleName;
	}

	@Column(name="suffix")
	public String getSuffix() {
		return this.suffix;
	}

	@Column(name="title")
	public String getTitle() {
		return this.title;
	}

	@Embedded
	public Address getAddress() {
		return this.address;
	}

	@Column(name="birthday")
	public Date getBirthday() {
		return this.birthday;
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

	public void setId(Long input) {
		this.id = input;
	}

	public void setLastName(String input) {
		this.lastName = input;
	}

	public void setFirstName(String input) {
		this.firstName = input;
	}

	public void setMiddleName(String input) {
		this.middleName = input;
	}

	public void setSuffix(String input) {
		this.suffix = input;
	}

	public void setTitle(String input) {
		this.title = input;
	}

	public void setAddress(Address input) {
		this.address = input;
	}

	public void setBirthday(Date input) {
		this.birthday = input;
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