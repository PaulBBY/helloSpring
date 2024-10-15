package fr.diginamic.hello.controleurs;

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
import fr.diginamic.hello.dto.DepartmentDto;

import fr.diginamic.hello.service.DepartmentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@GetMapping("/getDepartments")
	public Page<DepartmentDto> getDepartments(@PageableDefault(size = 10, page = 0) Pageable page) {

		return departmentService.extractDepartments(page);
	}

	@GetMapping("/getDepartmentById")
	public DepartmentDto getDepartmentById(@RequestParam Long id) {
		return departmentService.extractDepartmentById(id);
	}

	@GetMapping("/getDepartmentByCode")
	public DepartmentDto getDepartmentByCode(@RequestParam String code) {
		return departmentService.extractDepartmentByCode(code);
	}

	@PutMapping("/createDepartment")
	public DepartmentDto createDepartment(@Valid @RequestBody DepartmentDto department) {
		System.out.println(department.toString());
		return departmentService.insertDepartment(department);
	}

	@PostMapping("/updateDepartment")
	public DepartmentDto updateDepartment(@RequestParam Long id, @Valid @RequestBody DepartmentDto department) {
		return departmentService.updateDepartment(id, department);
	}

	@DeleteMapping("/deleteDepartmnent")
	public boolean deleteDepartmnent(@RequestParam Long id) {
		return departmentService.deleteDepartment(id);
	}

}
