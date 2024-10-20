package fr.diginamic.hello.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DepartmentDto {

	@Size(min=02, max=03, message="Le code département fait au maximum 3 caractères et au minimum 2")
	private String departmentCode;
	
	@NotNull(message="Le nom du département est obligatoire")
	@Size(min=03, message="Le nom du département comporte au moins 3 lettres")
	private String departmentName;
	private Long departmentNbInhabitants;

	public DepartmentDto() {
		super();
	}

	public DepartmentDto(String departmentCode, String departmentName, Long departmentNbInhabitants) {
		super();
		this.departmentCode = departmentCode;
		this.departmentName = departmentName;
		this.departmentNbInhabitants = departmentNbInhabitants;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Long getDepartmentNbInhabitants() {
		return departmentNbInhabitants;
	}

	public void setDepartmentNbInhabitants(Long departmentNbInhabitants) {
		this.departmentNbInhabitants = departmentNbInhabitants;
	}

}
