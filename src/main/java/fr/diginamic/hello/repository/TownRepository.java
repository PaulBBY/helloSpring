package fr.diginamic.hello.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import fr.diginamic.hello.model.TownModel;

public interface TownRepository extends JpaRepository<TownModel, Long> {

	Page<TownModel>  findByNameStartingWith(String p, Pageable page);//
	
	Page<TownModel>  findByNbInhabitantGreaterThan(int min, Pageable page);//

	Page<TownModel> findByNbInhabitantBetween(int min, int max, Pageable page);//

	Page<TownModel>  findByDepartmentIdAndNbInhabitantGreaterThan(Long id, int min, Pageable page);//

	Page<TownModel>  findByDepartmentIdAndNbInhabitantBetween(Long id, int min, int max, Pageable page);//

	Page<TownModel>  findByDepartmentId(Long id, Pageable page);

}
