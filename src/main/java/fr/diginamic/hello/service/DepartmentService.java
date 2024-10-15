package fr.diginamic.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.dao.DepartmentDaoImp;
import fr.diginamic.hello.dto.DepartmentDto;
import fr.diginamic.hello.mapper.DepartmentMapper;

@Service
public class DepartmentService {

	@Autowired
	DepartmentDaoImp dao;
	
	@Autowired
	DepartmentMapper mapper;
	
	public Page<DepartmentDto> extractDepartments(Pageable page){
		Page<DepartmentDto> dto = dao.getDepartments(page).map(mapper::toDto);
		return dto;
	}
	
	public DepartmentDto extractDepartmentById(Long id) {

		return mapper.toDto(dao.getDepartmentById(id));
	}
	
	public DepartmentDto extractDepartmentByCode(String code) {

		return mapper.toDto(dao.getDepartmentByCode(code));
	}

	public DepartmentDto insertDepartment(DepartmentDto department) {
	
		return mapper.toDto(dao.createDepartment(mapper.toModel(department)));
	}
	
	public DepartmentDto updateDepartment(Long id, DepartmentDto department) {
	
		return mapper.toDto(dao.updateDepartmentById(mapper.toModel(department), id));
	}

	public boolean deleteDepartment(Long id) {
		return dao.deleteDepartmentById(id);
	}
}
