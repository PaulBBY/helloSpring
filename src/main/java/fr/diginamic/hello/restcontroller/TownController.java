package fr.diginamic.hello.restcontroller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.dto.TownDto;
import fr.diginamic.hello.exceptions.CustomEmptyTownExceptions;
import fr.diginamic.hello.exceptions.CustomExceptions;
import fr.diginamic.hello.service.TownService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/town")
public class TownController {

	@Autowired
	TownService townService;

	
	@GetMapping("/getCsv")//ok
	public void exportToCsv(@RequestParam Long minInhabitants, HttpServletResponse response) throws CustomEmptyTownExceptions, IOException {
		 townService.exportToCsv(response, minInhabitants);
	}

	// related to repository
	@GetMapping("/getByNameStartingWith")//ok
	public Page<TownDto> getByNameStartingWith(@RequestParam String startsWith,
			@PageableDefault(size = 10, page = 0) Pageable page) throws CustomEmptyTownExceptions {
		return townService.extractByNameStartingWith(startsWith, page);
	}

	@GetMapping("/getByNbInhabitantsGreaterThan")//ok
	public Page<TownDto> getByNbInhabitantsGreaterThan(@RequestParam Long minInhabitants,
			@PageableDefault(size = 10, page = 0) Pageable page) throws CustomEmptyTownExceptions {
		return townService.extractByNbInhabitantsGreaterThan(minInhabitants, page);
	}

	@GetMapping("/getByNbInhabitantBetween")//ok
	public Page<TownDto> getByNbInhabitantBetween(@RequestParam Long minInhabitants, @RequestParam Long maxInhabitants,
			@PageableDefault(size = 10, page = 0) Pageable page) throws CustomEmptyTownExceptions {
		return townService.extractByNbInhabitantBetween(minInhabitants, maxInhabitants, page);
	}

	@GetMapping("/getByDepartmentIdAndNbInhabitantsGreaterThan")//ok
	public Page<TownDto> getByDepartmentIdAndNbInhabitantsGreaterThan(@RequestParam Long departmentId, @RequestParam Long minTownInhabitants,
			@PageableDefault(size = 10, page = 0) Pageable page) throws CustomEmptyTownExceptions {
		return townService.extractByDepartmentIdAndNbInhabitantsGreaterThan(departmentId, minTownInhabitants, page);
	}

	@GetMapping("/getByDepartmentIdAndNbInhabitantsBetween")//ok
	public Page<TownDto> getByDepartmentIdAndNbInhabitantsBetween(@RequestParam Long departmentId, @RequestParam Long minTownInhabitants,
			@RequestParam Long maxTownInhabitants, @PageableDefault(size = 10, page = 0) Pageable page)
			throws CustomEmptyTownExceptions {
		return townService.extractByDepartmentIdAndNbInhabitantsBetween(departmentId, minTownInhabitants, maxTownInhabitants, page);
	}

	@GetMapping("/getTopNByDepartmentId")//ok
	public Page<TownDto> getTopNByDepartmentId(@RequestParam Long departmentId, @RequestParam int topN,
			@PageableDefault(size = 10, page = 0) Pageable page) throws CustomEmptyTownExceptions {
		return townService.extractTopNByDepartmentId(departmentId, topN, page);
	}

	// related to DAO
	@GetMapping("/getNTopTownsByDepartmentCode")//ok
	public Page<TownDto> getNTopTownsFromDepartment(@RequestParam int topN, @RequestParam String departmentCode,
			@PageableDefault(size = 10, page = 0) Pageable page) throws CustomEmptyTownExceptions {
		return townService.extractNTopTownsFromDepartment(topN, departmentCode, page);
	}

	@GetMapping("/getTownsMinMaxByDepartmentCode")//ok
	public Page<TownDto> getTownsMinMaxFromDepartment(@RequestParam Long minTownInhabitants, @RequestParam Long maxTownInhabitants,
			@RequestParam String departmentCode, @PageableDefault(size = 10, page = 0) Pageable page)
			throws CustomEmptyTownExceptions {
		return townService.extractMinMaxTownsFromDepartment(minTownInhabitants, maxTownInhabitants, departmentCode, page);
	}

	@GetMapping("/getAllTowns")//ok
	public Page<TownDto> getAllTowns(@PageableDefault(size = 10, page = 0) Pageable page)
			throws CustomEmptyTownExceptions {

		return townService.extractTowns(page);
	}

	@GetMapping("/getTownById")//ok
	public TownDto getTownById(@RequestParam Long townId) throws CustomEmptyTownExceptions {
		return townService.extractTownById(townId);
	}

	@PutMapping("/createTown")
	public TownDto createTown(@Valid @RequestBody TownDto town) throws CustomExceptions, CustomEmptyTownExceptions {

		return townService.insertTown(town);
	}

	@PostMapping("/update")
	public TownDto updateTown(@RequestParam Long townId, @Valid @RequestBody TownDto town)
			throws CustomExceptions, CustomEmptyTownExceptions {
		return townService.updateTownById(townId, town);
	}

	@DeleteMapping("/delete")
	public boolean deleteTown(@RequestParam Long townId) {
		return townService.deleteTownById(townId);
	}

	@GetMapping("/getTownByName")
	public Page<TownDto> getTownByName(@RequestParam String name, @PageableDefault(size = 10, page = 0) Pageable page)
			throws CustomEmptyTownExceptions {
		return townService.extractTownByName(name, page);
	}

}
