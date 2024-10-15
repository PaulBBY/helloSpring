package fr.diginamic.hello.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "DEPARTEMENT")
public class DepartmentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "nom")
	private String name;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TownModel> towns;

	public DepartmentModel() {
		super();
	}

	public DepartmentModel(String departmentcode, String name, List<TownModel> towns) {
		super();
		this.code = departmentcode;
		this.name = name;
		this.towns = towns;
	}

	public DepartmentModel(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<TownModel> getTowns() {
		return towns;
	}

	public void setTowns(List<TownModel> towns) {
		this.towns = towns;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DepartmentModel [id=" + id + ", code=" + code + ", name=" + name + ", towns=" + towns + "]";
	}

}
