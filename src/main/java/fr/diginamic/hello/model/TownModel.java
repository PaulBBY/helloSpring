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
public class TownModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull(message = "Please indicate a name for the town")
	@Column(name = "nom")
	private String name;

	@Min(value = 0, message = "There must be a number of inhabitants")
	@Column(name = "nb_habs")
	private int nbInhabitant;
	
	@ManyToOne
	@JoinColumn(name="id_dept")
	private DepartmentModel department;
	

	public TownModel() {
	}

	public TownModel(String name, int nbInhabitant) {
		this.name = name;
		this.nbInhabitant = nbInhabitant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNbInhabitant() {
		return nbInhabitant;
	}

	public void setNbInhabitant(int nbInhabitant) {
		this.nbInhabitant = nbInhabitant;
	}

	public Long getId() {
		return id;
	}

	public DepartmentModel getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentModel department) {
		this.department = department;
	}
}
