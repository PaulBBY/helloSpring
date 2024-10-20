package fr.diginamic.hello.mapper;

import org.springframework.stereotype.Component;

import fr.diginamic.hello.dto.DepartmentDto;
import fr.diginamic.hello.model.Department;

@Component
public class DepartmentMapper {

	public DepartmentDto toDto(Department model) {

		DepartmentDto dto = new DepartmentDto();
		dto.setDepartmentCode(model.getCode());
		dto.setDepartmentName(model.getName());
		if (model.getTowns() != null) {
			dto.setDepartmentNbInhabitants(model.getTowns().stream().mapToLong(t -> t.getNbInhabitants()).sum());
		}
		return dto;
	}

	public Department toModel(DepartmentDto dto) {
		Department model = new Department();
		model.setCode(dto.getDepartmentCode());
		model.setName(dto.getDepartmentName());
		return model;

	}
}
