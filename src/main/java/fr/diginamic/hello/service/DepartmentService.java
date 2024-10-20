package fr.diginamic.hello.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import fr.diginamic.hello.dao.DepartmentDaoImp;
import fr.diginamic.hello.dao.TownDaoImp;
import fr.diginamic.hello.dto.DepartmentDto;
import fr.diginamic.hello.exceptions.CustomEmptyDepartmentExceptions;
import fr.diginamic.hello.exceptions.CustomExceptions;
import fr.diginamic.hello.mapper.DepartmentMapper;
import fr.diginamic.hello.model.Department;
import fr.diginamic.hello.model.Town;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class DepartmentService {

	@Autowired
	DepartmentDaoImp dao;

	@Autowired
	TownDaoImp townDao;

	@Autowired
	DepartmentMapper mapper;

	public void exportToPdf(String codeDepartment, HttpServletResponse response)
			throws CustomEmptyDepartmentExceptions, DocumentException, IOException {
		Department department = extractDepartmentModelByCode(codeDepartment);
		List<Town> towns = department.getTowns();

		response.setHeader("Content-Disposition", "attachment; filename=\"departement.pdf\"");
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		document.addTitle("Department");
		document.newPage();
		BaseFont baseFont = BaseFont.createFont();
		Phrase title = new Phrase(department.getName() + "\n", new Font(baseFont, 32.0f, 1));
		document.add(title);

		Phrase departmentCode = new Phrase("Department code: " + department.getName() + "\n",
				new Font(baseFont, 12.0f, 1));
		document.add(departmentCode);

		Phrase title2 = new Phrase("Towns list: \n", new Font(baseFont, 12.0f, 1));
		document.add(title2);

		for (Town town : towns) {
			Phrase cityLine = new Phrase("- " + town.getName() + "\n", new Font(baseFont, 12.0f, 1));
			document.add(cityLine);
		}

		document.close();

		response.flushBuffer();

	}

	public Page<DepartmentDto> extractDepartments(Pageable page) throws CustomEmptyDepartmentExceptions {
		Page<Department> departments = dao.getDepartments(page);
		if (departments.isEmpty()) {
			throw new CustomEmptyDepartmentExceptions("No departments have been found");
		}
		Page<DepartmentDto> departmentsAsDto = departments.map(mapper::toDto);
		return departmentsAsDto;
	}

	public List<DepartmentDto> extractDepartments() throws CustomEmptyDepartmentExceptions {
		List<Department> departments = dao.getDepartments();
		if (departments.isEmpty()) {
			throw new CustomEmptyDepartmentExceptions("No departments have been found");
		}
		List<DepartmentDto> departmentsAsDto = departments.stream().map(mapper::toDto).toList();
		return departmentsAsDto;
	}

	public DepartmentDto extractDepartmentById(Long departmentId) throws CustomEmptyDepartmentExceptions {

		Department department = dao.getDepartmentById(departmentId);
		if (department == null) {
			throw new CustomEmptyDepartmentExceptions("No department with id of " + departmentId + " has been found");
		}
		return mapper.toDto(department);
	}

	public DepartmentDto extractDepartmentByCode(String code) throws CustomEmptyDepartmentExceptions {

		Department department = dao.getDepartmentByCode(code);
		if (department == null) {
			throw new CustomEmptyDepartmentExceptions("No department with code of " + code + " has been found");
		}
		return mapper.toDto(department);
	}

	public Department extractDepartmentModelByCode(String code) throws CustomEmptyDepartmentExceptions {

		Department department = dao.getDepartmentByCode(code);
		if (department == null) {
			throw new CustomEmptyDepartmentExceptions("No department with code of " + code + " has been found");
		}
		return department;
	}

	public DepartmentDto extractDepartmentByCodeNoException(String code) throws CustomEmptyDepartmentExceptions {

		Department department = dao.getDepartmentByCode(code);
		if (department == null) {
			return null;
		}
		return mapper.toDto(department);
	}

	public DepartmentDto insertDepartment(DepartmentDto department)
			throws CustomExceptions, CustomEmptyDepartmentExceptions {
		checkCodeIsUnique(department.getDepartmentCode());
		return mapper.toDto(dao.createDepartment(mapper.toModel(department)));
	}

	public DepartmentDto updateDepartment(Long id, DepartmentDto newDepartment)
			throws CustomEmptyDepartmentExceptions, CustomExceptions {
		DepartmentDto oldDepartment = extractDepartmentById(id);
		if (oldDepartment.getDepartmentCode().equals(newDepartment.getDepartmentCode())) {
			checkCodeIsUnique(newDepartment.getDepartmentCode());
		}
		return mapper.toDto(dao.updateDepartmentById(mapper.toModel(newDepartment), id));
	}

	public boolean deleteDepartment(Long id) {
		return dao.deleteDepartmentById(id);
	}

	// otherServices

	public void checkCodeIsUnique(String code) throws CustomExceptions, CustomEmptyDepartmentExceptions {
		DepartmentDto department = extractDepartmentByCodeNoException(code);

		if (department != null) {
			throw new CustomExceptions("The code of the department must be unique !");
		}
	}
}
