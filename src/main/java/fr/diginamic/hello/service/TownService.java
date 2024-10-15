package fr.diginamic.hello.service;

import java.io.Writer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.dao.TownDaoImp;
import fr.diginamic.hello.dto.TownDto;
import fr.diginamic.hello.mapper.TownMapper;
import fr.diginamic.hello.repository.TownRepository;
import jakarta.validation.Valid;

@Service
public class TownService {

	@Autowired
	TownDaoImp dao;

	@Autowired
	TownRepository repo;

	@Autowired
	TownMapper mapper;
	
	public void writeToCsv (Pageable page, Writer writer, int n) {
		CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
		Page<TownDto> dto = extractByNbInhabitantGreaterThan(n, page);
	}

	public Page<TownDto> extractByNameStartingWith(String s, Pageable page) {

		Page<TownDto> dto = repo.findByNameStartingWith(s, page).map(mapper::toDto);
		return dto;
	}

	public Page<TownDto> extractByNbInhabitantGreaterThan(int i, Pageable page) {
		Page<TownDto> dto = repo.findByNbInhabitantGreaterThan(i, page).map(mapper::toDto);
		return dto;
	}

	public Page<TownDto> extractByNbInhabitantBetween(int max, int min, Pageable page) {
		Page<TownDto> dto = repo.findByNbInhabitantBetween(max, min, page).map(mapper::toDto);
		return dto;

	}

	public Page<TownDto> extractByDepartmentIdAndNbInhabitantGreaterThan(Long id, int min, Pageable page) {
		Page<TownDto> dto = repo.findByDepartmentIdAndNbInhabitantGreaterThan(id, min, page).map(mapper::toDto);
		return dto;
	}

	public Page<TownDto> extractByDepartmentIdAndNbInhabitantBetween(Long id, int min, int max, Pageable page) {
		Page<TownDto> dto = repo.findByDepartmentIdAndNbInhabitantBetween(id, min, max, page).map(mapper::toDto);
		return dto;
	}

	public Page<TownDto> extractTopNByDepartmentId(Long id, int n, Pageable page) {	
		page = PageRequest.of(0, n);
		Page<TownDto> dto = repo.findByDepartmentId(id, page).map(mapper::toDto);
		return dto;
	}

	public Page<TownDto> extractTowns(Pageable page) {
		Page<TownDto> dto = dao.getTowns(page).map(mapper::toDto);
		return dto;
	}

	public TownDto extractTownById(Long id) {

		return mapper.toDto(dao.getTownById(id));

	}

	public Page<TownDto> extractTownByName(String name, Pageable page) {

		Page<TownDto> dto = dao.getTownByName(name, page).map(mapper::toDto);
		return dto;
	}

	public TownDto insertTown(@Valid TownDto town) {
		TownDto dto = mapper.toDto(dao.createTown(mapper.toModel(town)));
		return dto;
	}

	public TownDto updateTownById(Long id, @Valid TownDto town) {

		TownDto dto = mapper.toDto(dao.updateTownById(mapper.toModel(town), id));
		return dto;
	}

	public boolean deleteTownById(Long id) {
		return dao.deleteTownById(id);

	}

	public Page<TownDto> extractNTopTownsFromDepartment(int n, String code, Pageable page) {
		Page<TownDto> dto = dao.getNTopTownsFromDepartment(n, code, page).map(mapper::toDto);
		return dto;
	}

	public Page<TownDto> extractMinMaxTownsFromDepartment(int min, int max, String code, Pageable page) {
		Page<TownDto> dto = dao.getTownsMinMaxFromDepartment(min, max, code, page).map(mapper::toDto);
		return dto;
	}
	
	//other service
	public 
}
