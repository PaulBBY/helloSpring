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
	public Page<DepartmentModel> getDepartments(Pageable page) {
		try {
			List<DepartmentModel> departments = em.createQuery("select t from DepartmentModel t", DepartmentModel.class)
					.setFirstResult((int) page.getOffset()).setMaxResults(page.getPageSize()).getResultList();

			long nbDepartments = em.createQuery("select count(t) from DepartmentModel t", Long.class).getSingleResult();
			return new PageImpl<>(departments, page, (int) nbDepartments);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	@Transactional
	public DepartmentModel getDepartmentById(Long id) {
		try {
			return em.find(DepartmentModel.class, id);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	@Transactional
	public DepartmentModel createDepartment(DepartmentModel department) {
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
	public DepartmentModel updateDepartmentById(DepartmentModel department, Long id) {
		DepartmentModel departmentExists = getDepartmentById(id);

		if (departmentExists == null) {
			return null;
		}
		if (department.getCode() != null) {
			departmentExists.setCode(department.getCode());
		}
		if (department.getTowns() != null) {
			departmentExists.setTowns(department.getTowns());
		}
		if(department.getName() != null) {
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
	public boolean deleteDepartmentById(Long id) {
		DepartmentModel departmentExists = getDepartmentById(id);

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
	public DepartmentModel getDepartmentByCode(String code) {
		try {
			return em.createQuery("select t from DepartmentModel t where t.code = :p1", DepartmentModel.class)
					.setParameter("p1", code).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
