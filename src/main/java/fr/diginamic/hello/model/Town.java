package fr.diginamic.hello.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
@Entity
@Table(name="VILLE")
public class Town {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull(message = "Please indicate a name for the town")
	@Column(name = "nom")
	private String name;

	@Min(value = 0, message = "There must be a number of inhabitants")
	@Column(name = "nb_habs")
	private Long nbInhabitants;
	
	@ManyToOne
	@JoinColumn(name="id_dept")
	private Department department;
	

	public Town() {
	}

	public Town(String name, Long nbInhabitants) {
		this.name = name;
		this.nbInhabitants = nbInhabitants;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNbInhabitants() {
		return nbInhabitants;
	}

	public void setNbInhabitants(Long nbInhabitant) {
		this.nbInhabitants = nbInhabitant;
	}

	public Long getId() {
		return id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
