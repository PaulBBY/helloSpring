package fr.diginamic.hello.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.diginamic.hello.model.DepartmentModel;

public interface DepartmentDao {

	Page<DepartmentModel> getDepartments(Pageable page);

	DepartmentModel getDepartmentById(Long id);

	DepartmentModel getDepartmentByCode(String code);

	DepartmentModel createDepartment(DepartmentModel department);

	DepartmentModel updateDepartmentById(DepartmentModel department, Long id);

	boolean deleteDepartmentById(Long id);
}
