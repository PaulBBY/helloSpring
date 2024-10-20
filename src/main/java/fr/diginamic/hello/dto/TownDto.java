package fr.diginamic.hello.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class TownDto {

	@Size(min = 2, message = "La ville doit avoir un nom contenant au moins 2 lettres")
	private String townName;

	@Min(value = 10, message = "La ville doit avoir au moins 10 habitants")
	private Long townNbInhabitants;

	private Long townCode;

	@Size(min = 2, message = "Le code département doit obligatoire 2 caractères")
	private String departmentCode;

	private String departmentName;

	public TownDto() {
		super();
	}

	public TownDto(@Size(min = 2, message = "La ville doit avoir un nom contenant au moins 2 lettres") String townName,
			@Min(value = 10, message = "La ville doit avoir au moins 10 habitants") Long townNbInhabitants, Long townCode,
			@Size(min = 2, message = "Le code département doit obligatoire 2 caractères") String departmentCode,
			String departmentName) {
		super();
		this.townName = townName;
		this.townNbInhabitants = townNbInhabitants;
		this.townCode = townCode;
		this.departmentCode = departmentCode;
		this.departmentName = departmentName;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public Long getTownNbInhabitant() {
		return townNbInhabitants;
	}

	public void setTownNbInhabitants(Long townNbInhabitants) {
		this.townNbInhabitants = townNbInhabitants;
	}

	public long getTownCode() {
		return townCode;
	}

	public void setTownCode(Long townCode) {
		this.townCode = townCode;
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

}
