package fr.diginamic.hello.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.hello.model.Town;

public interface TownRepository extends JpaRepository<Town, Long> {

	Page<Town>  findByNameStartingWith(String startsWith, Pageable page);//
	
	Page<Town>  findByNbInhabitantsGreaterThan(Long minInhabitants, Pageable page);//
	
	List<Town>  findByNbInhabitantsGreaterThan(Long minInhabitants);//

	Page<Town> findByNbInhabitantsBetween(Long minInhabitants, Long maxInhabitants, Pageable page);//
	
	Page<Town>  findByDepartmentIdAndNbInhabitantsGreaterThan(Long departmentId, Long townMinInhabitants, Pageable page);//

	Page<Town>  findByDepartmentIdAndNbInhabitantsBetween(Long departmentId, Long townMinInhabitants, Long townMaxInhabitants, Pageable page);//

	Page<Town>  findByDepartmentId(Long departmentId, Pageable page);

}
