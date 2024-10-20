package fr.diginamic.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.hello.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
