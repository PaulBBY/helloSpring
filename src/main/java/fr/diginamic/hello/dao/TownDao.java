package fr.diginamic.hello.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.diginamic.hello.model.Town;

public interface TownDao {

	Page<Town> getTowns(Pageable page);
	
	Town getTownById(Long townId);

	Page<Town> getTownByName(String townName, Pageable page);

	Town createTown(Town town);

	Town updateTownById(Town town, Long townId);
	
	boolean deleteTownById(Long townId);
}
