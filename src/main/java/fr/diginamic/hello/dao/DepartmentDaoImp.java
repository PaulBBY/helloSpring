package fr.diginamic.hello.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class DepartmentDaoImp implements DepartmentDao {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public Page<Department> getDepartments(Pageable page) {
		try {
			List<Department> departments = em.createQuery("select t from Department t", Department.class)
					.setFirstResult((int) page.getOffset()).setMaxResults(page.getPageSize()).getResultList();

			long nbDepartments = em.createQuery("select count(t) from Department t", Long.class).getSingleResult();
			return new PageImpl<>(departments, page, (int) nbDepartments);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Transactional
	public List<Department> getDepartments() {
		try {
			List<Department> departments = em.createQuery("select t from Department t", Department.class)
					.getResultList();

			return departments;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	@Transactional
	public Department getDepartmentById(Long departmentId) {
		try {
			return em.find(Department.class, departmentId);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	@Transactional
	public Department createDepartment(Department department) {
		try {
			em.persist(department);
			return department;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	@Override
	@Transactional
	public Department updateDepartmentById(Department department, Long departmentId) {
		Department departmentExists = getDepartmentById(departmentId);

		if (departmentExists == null) {
			return null;
		}
		if (department.getCode() != null) {
			departmentExists.setCode(department.getCode());
		}
		if (department.getTowns() != null) {
			departmentExists.setTowns(department.getTowns());
		}
		if (department.getName() != null) {
			departmentExists.setName(department.getName());
		}

		try {
			em.merge(departmentExists);
			return departmentExists;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	@Transactional
	public boolean deleteDepartmentById(Long departmentId) {
		Department departmentExists = getDepartmentById(departmentId);

		if (departmentExists == null) {
			return false;
		}

		try {
			em.remove(departmentExists);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Department getDepartmentByCode(String departmentCode) {
		try {
			return em.createQuery("select t from Department t where t.code = :p1", Department.class)
					.setParameter("p1", departmentCode).getSingleResult();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

}
