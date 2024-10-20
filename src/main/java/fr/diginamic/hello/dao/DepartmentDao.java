package fr.diginamic.hello.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.diginamic.hello.model.Department;

public interface DepartmentDao {

	Page<Department> getDepartments(Pageable page);

	Department getDepartmentById(Long departmentId);

	Department getDepartmentByCode(String codeId);

	Department createDepartment(Department departmentId);

	Department updateDepartmentById(Department department, Long departmentId);

	boolean deleteDepartmentById(Long id);
}
