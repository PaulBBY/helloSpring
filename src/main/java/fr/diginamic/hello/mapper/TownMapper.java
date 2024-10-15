package fr.diginamic.hello.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.diginamic.hello.dao.DepartmentDao;
import fr.diginamic.hello.dto.TownDto;
import fr.diginamic.hello.model.TownModel;

@Component
public class TownMapper {

	@Autowired
	DepartmentDao departmentDao;

	public TownDto toDto(TownModel model) {
		TownDto dto = new TownDto();
		if (model.getDepartment() != null) {
			dto.setDepartmentCode(model.getDepartment().getCode());
			dto.setDepartmentName(model.getDepartment().getName());
		}
		dto.setTownName(model.getName());
		dto.setTownNbInhabitant(model.getNbInhabitant());
		return dto;
	}

	public TownModel toModel(TownDto dto) {

		TownModel model = new TownModel(dto.getTownName(), dto.getTownNbInhabitant());
		if (dto.getDepartmentCode() != null) {
			model.setDepartment(departmentDao.getDepartmentByCode(dto.getDepartmentCode()));
		}
		return model;
	}
}
