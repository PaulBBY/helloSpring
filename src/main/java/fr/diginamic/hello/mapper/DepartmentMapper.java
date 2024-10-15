package fr.diginamic.hello.mapper;

import org.springframework.stereotype.Component;

import fr.diginamic.hello.dto.DepartmentDto;
import fr.diginamic.hello.model.DepartmentModel;

@Component
public class DepartmentMapper {

	public DepartmentDto toDto(DepartmentModel department) {
		
		DepartmentDto dto = new DepartmentDto();
		dto.setDepartmentCode(department.getCode());
		dto.setDepartmentName(department.getName());
		if(department.getTowns() != null) {
			dto.setDepartmentPopulation(department.getTowns().stream().mapToInt(t -> t.getNbInhabitant()).sum());
		}
		return dto;
	}
	
	public DepartmentModel toModel(DepartmentDto dto) {
		DepartmentModel model = new DepartmentModel();
		model.setCode(dto.getDepartmentCode());
		model.setName(dto.getDepartmentName());
		return model;
		
	}
}
