package com.exercise7.core.model;

public class EmployeeGradeDTO {
	
	private String firstName;
	private String lastName;
	private Float gradeWeightAverage;

	public EmployeeGradeDTO() {}
	public EmployeeGradeDTO(String firstName, String lastName, Float gradeWeightAverage) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gradeWeightAverage = gradeWeightAverage;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String input) {
		this.firstName = input;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String input) {
		this.lastName = input;
	}
	public Float getGradeWeightAverage() {
		return this.gradeWeightAverage;
	}

	public void setGradeWeightAverage(Float input) {
		this.gradeWeightAverage = input;
	}
}