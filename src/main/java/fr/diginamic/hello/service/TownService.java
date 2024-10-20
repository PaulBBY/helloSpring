package fr.diginamic.hello.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.dao.TownDaoImp;
import fr.diginamic.hello.dto.TownDto;
import fr.diginamic.hello.exceptions.CustomEmptyTownExceptions;
import fr.diginamic.hello.exceptions.CustomExceptions;
import fr.diginamic.hello.mapper.TownMapper;
import fr.diginamic.hello.model.Town;
import fr.diginamic.hello.repository.TownRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Service
public class TownService {

	@Autowired
	TownDaoImp dao;

	@Autowired
	TownRepository repo;

	@Autowired
	TownMapper mapper;

	public void exportToCsv(HttpServletResponse response, Long minInhabitants)
			throws CustomEmptyTownExceptions, IOException {
		List<TownDto> towns = extractByNbInhabitantsGreaterThan(minInhabitants);

		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment;filename=cities.csv");
		PrintWriter writer = response.getWriter();
		writer.println("Name,Inhabitants,Department_Code,Department_Name");
		for (TownDto town : towns) {
			writer.printf("%s,%d,%s,%s%n", town.getTownName(), town.getTownNbInhabitant(), town.getDepartmentName(),
					town.getDepartmentCode()

			);

		}

		writer.flush();
		writer.close();

	}

	public Page<TownDto> extractByNameStartingWith(String startsWith, Pageable page) throws CustomEmptyTownExceptions {

		Page<Town> towns = repo.findByNameStartingWith(startsWith, page);
		if (towns.isEmpty()) {
			throw new CustomEmptyTownExceptions("No town starting with " + startsWith + " has been found");
		}
		Page<TownDto> townsAsDto = towns.map(mapper::toDto);
		return townsAsDto;
	}

	public Page<TownDto> extractByNbInhabitantsGreaterThan(Long minInhabitants, Pageable page)
			throws CustomEmptyTownExceptions {
		Page<Town> towns = repo.findByNbInhabitantsGreaterThan(minInhabitants, page);
		if (towns.isEmpty()) {
			throw new CustomEmptyTownExceptions(
					"No town with population above " + minInhabitants + " people has been found");
		}
		Page<TownDto> townsAsDto = towns.map(mapper::toDto);
		return townsAsDto;
	}

	public List<TownDto> extractByNbInhabitantsGreaterThan(Long minInhabitants) throws CustomEmptyTownExceptions {
		List<Town> towns = repo.findByNbInhabitantsGreaterThan(minInhabitants);
		if (towns.isEmpty()) {
			throw new CustomEmptyTownExceptions(
					"No town with population above " + minInhabitants + " people has been found");
		}
		return towns.stream().map(mapper::toDto).toList();
	}

	public Page<TownDto> extractByNbInhabitantBetween(Long maxPopulation, Long minPopulation, Pageable page)
			throws CustomEmptyTownExceptions {
		Page<Town> towns = repo.findByNbInhabitantsBetween(maxPopulation, minPopulation, page);
		if (towns.isEmpty()) {
			throw new CustomEmptyTownExceptions(
					"No town with population between " + minPopulation + " and " + maxPopulation + " has been found");
		}
		Page<TownDto> townsAsDto = towns.map(mapper::toDto);
		return townsAsDto;

	}

	public Page<TownDto> extractByDepartmentIdAndNbInhabitantsGreaterThan(Long departmentId, Long townMinPopulation,
			Pageable page) throws CustomEmptyTownExceptions {
		Page<Town> towns = repo.findByDepartmentIdAndNbInhabitantsGreaterThan(departmentId, townMinPopulation, page);
		if (towns.isEmpty()) {
			throw new CustomEmptyTownExceptions("No town with population above" + townMinPopulation
					+ " from department having a id of : " + departmentId + " has been found");
		}
		Page<TownDto> townsAsDto = towns.map(mapper::toDto);
		return townsAsDto;
	}

	public Page<TownDto> extractByDepartmentIdAndNbInhabitantsBetween(Long departmentId, Long townMinPopulation,
			Long townMaxPopulation, Pageable page) throws CustomEmptyTownExceptions {
		Page<Town> towns = repo.findByDepartmentIdAndNbInhabitantsBetween(departmentId, townMinPopulation, townMaxPopulation,
				page);
		if (towns.isEmpty()) {
			throw new CustomEmptyTownExceptions("No town with population between " + townMinPopulation + " and "
					+ townMaxPopulation + " from department having a id of : " + departmentId + " has been found");
		}
		Page<TownDto> townsAsDto = towns.map(mapper::toDto);
		return townsAsDto;
	}

	public Page<TownDto> extractTopNByDepartmentId(Long deparmtentId, int topN, Pageable page)
			throws CustomEmptyTownExceptions {
		page = PageRequest.of(0, topN);
		Page<Town> towns = repo.findByDepartmentId(deparmtentId, page);
		if (towns.isEmpty()) {
			throw new CustomEmptyTownExceptions(
					"No top " + topN + " towns of department with id : " + deparmtentId + " has been found");
		}
		Page<TownDto> townsAsDto = towns.map(mapper::toDto);
		return townsAsDto;
	}

	public Page<TownDto> extractTowns(Pageable page) throws CustomEmptyTownExceptions {
		Page<Town> towns = dao.getTowns(page);
		if (towns.isEmpty()) {
			throw new CustomEmptyTownExceptions("No towns have been found");
		}
		Page<TownDto> townsAsDto = towns.map(mapper::toDto);
		return townsAsDto;
	}

	public List<TownDto> extractTowns() throws CustomEmptyTownExceptions {
		List<Town> towns = dao.getTowns();
		if (towns.isEmpty()) {
			throw new CustomEmptyTownExceptions("No towns have been found");
		}
		List<TownDto> townsAsDto = towns.stream().map(mapper::toDto).toList();
		return townsAsDto;
	}

	public TownDto extractTownById(Long id) throws CustomEmptyTownExceptions {
		Town town = dao.getTownById(id);
		if (town == null) {
			throw new CustomEmptyTownExceptions("No town with id : " + id + " has been found");
		}
		return mapper.toDto(town);

	}

	public Page<TownDto> extractTownByName(String name, Pageable page) throws CustomEmptyTownExceptions {

		Page<Town> towns = dao.getTownByName(name, page);
		if (towns.isEmpty()) {
			throw new CustomEmptyTownExceptions("No town name " + name + " has been found");
		}
		Page<TownDto> townsAsDto = towns.map(mapper::toDto);
		return townsAsDto;
	}

	public TownDto insertTown(@Valid TownDto town) throws CustomExceptions, CustomEmptyTownExceptions {
		this.checkAlreadyExistingTownInDepartment(town);
		TownDto dto = mapper.toDto(dao.createTown(mapper.toModel(town)));
		return dto;
	}

	public TownDto updateTownById(Long id, @Valid TownDto newTown) throws CustomExceptions, CustomEmptyTownExceptions {
		TownDto oldTown = extractTownById(id);

		if (!oldTown.getTownName().equals(newTown.getTownName())) {
			this.checkAlreadyExistingTownInDepartment(newTown);
		}
		TownDto dto = mapper.toDto(dao.updateTownById(mapper.toModel(newTown), id));
		return dto;
	}

	public boolean deleteTownById(Long id) {
		return dao.deleteTownById(id);

	}

	public Page<TownDto> extractNTopTownsFromDepartment(int n, String departmentCode, Pageable page)
			throws CustomEmptyTownExceptions {
		Page<Town> towns = dao.getNTopTownsFromDepartment(n, departmentCode, page);
		if (towns.isEmpty()) {
			throw new CustomEmptyTownExceptions(
					"No top " + n + " towns from department with code " + departmentCode + " has been found");
		}
		Page<TownDto> townsAsDto = towns.map(mapper::toDto);
		return townsAsDto;
	}

	public Page<TownDto> extractMinMaxTownsFromDepartment(Long minPopulation, Long maxPopulation, String departmentCode,
			Pageable page) throws CustomEmptyTownExceptions {
		Page<Town> towns = dao.getTownsMinMaxFromDepartment(minPopulation, maxPopulation, departmentCode, page);
		if (towns.isEmpty()) {
			throw new CustomEmptyTownExceptions("No towns having between " + minPopulation + " and " + maxPopulation
					+ " from department with code " + departmentCode + " has been found");
		}
		Page<TownDto> townsAsDto = towns.map(mapper::toDto);
		return townsAsDto;
	}
	// other methods
	
	public List<TownDto> extractTownByNameNoException(String name) {

		List<Town> towns = dao.getTownByName(name);
		if(towns==null) {
			return null;
		}
		List<TownDto> townsAsDto = towns.stream().map(mapper::toDto).toList();
		return townsAsDto;
	}

	public void checkAlreadyExistingTownInDepartment(TownDto town) throws CustomExceptions, CustomEmptyTownExceptions {
		List<TownDto> towns = extractTownByNameNoException(town.getDepartmentName());
		if(towns.isEmpty()) {
			return;
		}
		if (towns.stream().anyMatch(c -> c.getDepartmentCode().equals(town.getDepartmentCode()))) {
			throw new CustomExceptions("This town already exists for this department");
		}
	}

}
