package fr.diginamic.hello.controleurs;

import java.awt.Font;
import java.io.IOException;
import java.util.List;

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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import fr.diginamic.hello.dto.TownDto;
import fr.diginamic.hello.exceptions.CustomException;
import fr.diginamic.hello.service.TownService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/town")
public class TownController {

	@Autowired
	TownService townService;
	
	@GetMapping("/getTownCsv")
	public void exportTownCsv (HttpServletResponse response, @RequestParam int n, @PageableDefault(size = Integer.MAX_VALUE, page = 0) Pageable page) throws IOException, CustomException {
		
		Page<TownDto> dtoPage = townService.extractByNbInhabitantGreaterThan(n, page);
		List<TownDto> dtoList = dtoPage.getContent();
		
		response.setHeader("Content-Disposition", "attachment; filename=\"fichier.pdf\"");
		
	}
		
	

	// related to repository
	@GetMapping("/getByNameStartingWith")
	public Page<TownDto> getByNameStartingWith(@RequestParam String name,
			@PageableDefault(size = 10, page = 0) Pageable page) {
		return townService.extractByNameStartingWith(name, page);
	}

	@GetMapping("/getByNbInhabitantGreaterThan")
	public Page<TownDto> getByNbInhabitantGreaterThan(@RequestParam int n,
			@PageableDefault(size = 10, page = 0) Pageable page) {
		return townService.extractByNbInhabitantGreaterThan(n, page);
	}

	@GetMapping("/getByNbInhabitantBetween")
	public Page<TownDto> getByNbInhabitantBetween(@RequestParam int min, @RequestParam int max,
			@PageableDefault(size = 10, page = 0) Pageable page) {
		return townService.extractByNbInhabitantBetween(min, max, page);
	}

	@GetMapping("/getByDepartmentIdAndNbInhabitantGreaterThan")
	public Page<TownDto> getByDepartmentIdAndNbInhabitantGreaterThan(@RequestParam Long id, @RequestParam int min,
			@PageableDefault(size = 10, page = 0) Pageable page) {
		return townService.extractByDepartmentIdAndNbInhabitantGreaterThan(id, min, page);
	}

	@GetMapping("/getByDepartmentIdAndNbInhabitantBetween")
	public Page<TownDto> getByDepartmentIdAndNbInhabitantBetween(@RequestParam Long id, @RequestParam int min,
			@RequestParam int max, @PageableDefault(size = 10, page = 0) Pageable page) {
		return townService.extractByDepartmentIdAndNbInhabitantBetween(id, min, max, page);
	}

	@GetMapping("/getTopNByDepartmentId")
	public Page<TownDto> getTopNByDepartmentId(@RequestParam Long id, @RequestParam int n,
			@PageableDefault(size = 10, page = 0) Pageable page) {
		return townService.extractTopNByDepartmentId(id, n, page);
	}

	// related to DAO
	@GetMapping("/getNTopTownsFromDepartment")
	public Page<TownDto> getNTopTownsFromDepartment(@RequestParam int n, @RequestParam String code,
			@PageableDefault(size = 10, page = 0) Pageable page) {
		return townService.extractNTopTownsFromDepartment(n, code, page);
	}

	@GetMapping("/getTownsMinMaxFromDepartment")
	public Page<TownDto> getTownsMinMaxFromDepartment(@RequestParam int min, @RequestParam int max,
			@RequestParam String code, @PageableDefault(size = 10, page = 0) Pageable page) {
		return townService.extractMinMaxTownsFromDepartment(min, max, code, page);
	}

	@GetMapping("/getAllTowns")
	public Page<TownDto> getAllTowns(@PageableDefault(size = 10, page = 0) Pageable page) {

		return townService.extractTowns(page);
	}

	@GetMapping("/getTownById")
	public TownDto getTownById(@RequestParam Long id) {
		return townService.extractTownById(id);
	}

	@PutMapping("/createTown")
	public TownDto createTown(@Valid @RequestBody TownDto town) {

		return townService.insertTown(town);
	}

	@PostMapping("/update")
	public TownDto updateTown(@Valid @RequestParam Long id, @RequestBody TownDto town) {
		return townService.updateTownById(id, town);
	}

	@DeleteMapping("/delete")
	public boolean deleteTown(@RequestParam Long id) {
		return townService.deleteTownById(id);
	}

	@GetMapping("/getTownByName")
	public Page<TownDto> getTownByName(@RequestParam String name, @PageableDefault(size = 10, page = 0) Pageable page) {
		return townService.extractTownByName(name, page);
	}

}
