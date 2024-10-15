package fr.diginamic.hello.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class TownDto {

	@Size(min = 2, message="La ville doit avoir un nom contenant au moins 2 lettres")
	private String townNname;
	
	@Min(value=10, message="La ville doit avoir au moins 10 habitants")
	private int townNbInhabitant;
	
	@Size(min=2, message="Le code département doit obligatoire 2 caractères")
	private String departmentCode;
	
	private String departmentName;

	public TownDto() {
		super();
	}

	public TownDto(String townNname, int townNbInhabitant, String departmentCode, String departmentName) {
		super();
		this.townNname = townNname;
		this.townNbInhabitant = townNbInhabitant;
		this.departmentCode = departmentCode;
		this.departmentName = departmentName;
	}

	public String getTownName() {
		return townNname;
	}

	public void setTownName(String townNname) {
		this.townNname = townNname;
	}

	public int getTownNbInhabitant() {
		return townNbInhabitant;
	}

	public void setTownNbInhabitant(int nbInhabitant) {
		this.townNbInhabitant = nbInhabitant;
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
