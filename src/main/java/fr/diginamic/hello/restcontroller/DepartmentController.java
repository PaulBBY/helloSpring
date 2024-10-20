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

import com.itextpdf.text.DocumentException;

import fr.diginamic.hello.dto.DepartmentDto;
import fr.diginamic.hello.exceptions.CustomEmptyDepartmentExceptions;
import fr.diginamic.hello.exceptions.CustomExceptions;
import fr.diginamic.hello.service.DepartmentService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	@GetMapping("/getPdf")
	public void exportToCsv(@RequestParam String departmentCode, HttpServletResponse response) throws  CustomEmptyDepartmentExceptions, DocumentException, IOException {
		departmentService.exportToPdf(departmentCode, response);
	}

	@GetMapping("/getDepartments")
	public Page<DepartmentDto> getDepartments(@PageableDefault(size = 10, page = 0) Pageable page)
			throws CustomEmptyDepartmentExceptions {

		return departmentService.extractDepartments(page);
	}

	@GetMapping("/getDepartmentById")
	public DepartmentDto getDepartmentById(@RequestParam Long departmentId) throws CustomEmptyDepartmentExceptions {
		return departmentService.extractDepartmentById(departmentId);
	}

	@GetMapping("/getDepartmentByCode")
	public DepartmentDto getDepartmentByCode(@RequestParam String departmentCode) throws CustomEmptyDepartmentExceptions {
		return departmentService.extractDepartmentByCode(departmentCode);
	}

	@PutMapping("/createDepartment")
	public DepartmentDto createDepartment(@Valid @RequestBody DepartmentDto department)
			throws CustomExceptions, CustomEmptyDepartmentExceptions {
		return departmentService.insertDepartment(department);
	}

	@PostMapping("/updateDepartment")
	public DepartmentDto updateDepartment(@RequestParam Long departmentId, @Valid @RequestBody DepartmentDto department)
			throws CustomEmptyDepartmentExceptions, CustomExceptions {
		return departmentService.updateDepartment(departmentId, department);
	}

	@DeleteMapping("/deleteDepartmnent")
	public boolean deleteDepartmnent(@RequestParam Long departmentId) {
		return departmentService.deleteDepartment(departmentId);
	}

}
