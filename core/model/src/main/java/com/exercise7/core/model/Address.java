package com.exercise7.core.model;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String streetNumber;
	private String barangay;
	private String city;
	private String zipcode;
	private String country;

	public Address() {}
	public Address(String streetNumber, String barangay, String city, String country, String zipcode) {
		this.streetNumber = streetNumber;
		this.barangay = barangay;
		this.city = city;
		this.zipcode = zipcode;
		this.country = country;
	}

	@Column(name="street")
	public String getStreetNumber() {
		return this.streetNumber;
	}

	@Column(name="barangay")
	public String getBarangay() {
		return this.barangay;
	}

	@Column(name="city")
	public String getCity() {
		return this.city;
	}

	@Column(name="zipcode")
	public String getZipcode() {
		return this.zipcode;
	}

	@Column(name="country")
	public String getCountry() {
		return this.country;
	}

	public void setStreetNumber(String input) {
		this.streetNumber = input;
	}

	public void setBarangay(String input) {
		this.barangay = input;
	}

	public void setCity(String input) {
		this.city = input;
	}

	public void setZipcode(String input) {
		this.zipcode = input;
	}

	public void setCountry(String input) {
		this.country = input;
	}
}