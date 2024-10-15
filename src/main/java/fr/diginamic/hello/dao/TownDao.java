package fr.diginamic.hello.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.diginamic.hello.model.TownModel;

public interface TownDao {

	Page<TownModel> getTowns(Pageable page);
	
	TownModel getTownById(Long id);

	Page<TownModel> getTownByName(String name, Pageable page);

	TownModel createTown(TownModel town);

	TownModel updateTownById(TownModel town, Long id);
	
	boolean deleteTownById(Long id);
}
