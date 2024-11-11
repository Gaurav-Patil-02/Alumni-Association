package com.example.alumniassociation.dto;

public class UpdateProfileDTO {
	private String fullName;
    private Integer graduationYear;
    private String fieldOfStudy;
    private String currentOccupation;

    public UpdateProfileDTO(String fullName, Integer graduationYear, String fieldOfStudy, String currentOccupation) {
		super();
		this.fullName = fullName;
		this.graduationYear = graduationYear;
		this.fieldOfStudy = fieldOfStudy;
		this.currentOccupation = currentOccupation;
	}


	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getGraduationYear() {
		return graduationYear;
	}
	public void setGraduationYear(Integer graduationYear) {
		this.graduationYear = graduationYear;
	}
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}
	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}

	public String getCurrentOccupation() {
		return currentOccupation;
	}
	public void setCurrentOccupation(String currentOccupation) {
		this.currentOccupation = currentOccupation;
	}
}
