package fr.diginamic.hello.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.diginamic.hello.dao.DepartmentDao;
import fr.diginamic.hello.dto.TownDto;
import fr.diginamic.hello.model.Town;

@Component
public class TownMapper {

	@Autowired
	DepartmentDao departmentDao;

	public TownDto toDto(Town model) {
		TownDto dto = new TownDto();
		if (model.getDepartment() != null) {
			dto.setDepartmentCode(model.getDepartment().getCode());
			dto.setDepartmentName(model.getDepartment().getName());
		}
		dto.setTownName(model.getName());
		dto.setTownNbInhabitants(model.getNbInhabitants());
		dto.setTownCode(model.getId());
		return dto;
	}

	public Town toModel(TownDto dto) {

		Town model = new Town(dto.getTownName(), dto.getTownNbInhabitant());
		if (dto.getDepartmentCode() != null) {
			model.setDepartment(departmentDao.getDepartmentByCode(dto.getDepartmentCode()));
		}
		return model;
	}
}
