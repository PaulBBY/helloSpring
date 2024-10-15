package fr.diginamic.hello.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.model.TownModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class TownDaoImp implements TownDao {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public Page<TownModel> getTowns(Pageable page) {
		try {
			List<TownModel> towns = em.createQuery("select t from TownModel t", TownModel.class)
					.setFirstResult((int) page.getOffset()).setMaxResults(page.getPageSize()).getResultList();

			long nbTowns = em.createQuery("select count(t) from TownModel t", Long.class).getSingleResult();
			return new PageImpl<>(towns, page, (int) nbTowns);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public TownModel getTownById(Long id) {
		try {
			return em.find(TownModel.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public TownModel createTown(TownModel town) {
		try {
			em.persist(town);
			return town;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public TownModel updateTownById(TownModel town, Long id) {
		TownModel townExists = getTownById(id);

		if (townExists == null) {
			return null;
		}

		if (town.getName() != null) {
			townExists.setName(town.getName());
		}
		if (town.getNbInhabitant() != 0) {
			townExists.setNbInhabitant(town.getNbInhabitant());

		}
		if (town.getDepartment() != null) {
			townExists.setDepartment(town.getDepartment());
		}

		try {
			em.merge(townExists);
			return townExists;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public boolean deleteTownById(Long id) {
		TownModel townExists = getTownById(id);

		if (townExists == null) {
			return false;
		}
		try {
			em.remove(townExists);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Page<TownModel> getTownByName(String name, Pageable page) {
		try {
			List<TownModel> town = em.createQuery("select t from TownModel t " + "where t.name = :p1", TownModel.class)
					.setParameter("p1", name).setFirstResult((int) page.getOffset()).setMaxResults(page.getPageSize())
					.getResultList();

			long nbTowns = em.createQuery("select count(t) from TownModel t " + "where t.name = :p1", Long.class)
					.setParameter("p1", name)
					.getSingleResult();
			return new PageImpl<>(town, page, (int) nbTowns);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Page<TownModel> getNTopTownsFromDepartment(int n, String code, Pageable page) {
		try {
			List<TownModel> towns = em
					.createQuery("select t from DepartmentModel d join d.towns t " + "where d.code = :p1 "
							+ "order by t.nbInhabitant desc", TownModel.class)
					.setParameter("p1", code).setFirstResult((int) page.getOffset()).setMaxResults(n).getResultList();

			long nbTowns = em
					.createQuery("select count(t) from DepartmentModel d join d.towns t "
							+ "where d.code = :p1", Long.class)
					.setParameter("p1", code)
					.getSingleResult();

			return new PageImpl<>(towns, page, (int) nbTowns);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Page<TownModel> getTownsMinMaxFromDepartment(int min, int max, String code, Pageable page) {
		try {
			List<TownModel> towns = em
					.createQuery(
							"select t from DepartmentModel d " + "join d.towns t " + "where d.code = :p1 "
									+ "and (t.nbInhabitant >= :p2) " + "and (t.nbInhabitant <= :p3)",
							TownModel.class)
					.setParameter("p1", code).setParameter("p2", min).setParameter("p3", max)
					.setFirstResult((int) page.getOffset()).setMaxResults(page.getPageSize()).getResultList();

			long nbTowns = em
					.createQuery(
							"select count(t) from DepartmentModel d join d.towns t " + "where d.code = :p1 "
									+ "and (t.nbInhabitant >= :p2) " + "and (t.nbInhabitant <= :p3)",
							Long.class)
					.setParameter("p1", code).setParameter("p2", min).setParameter("p3", max)
					.getSingleResult();

			return new PageImpl<>(towns, page, (int) nbTowns);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
