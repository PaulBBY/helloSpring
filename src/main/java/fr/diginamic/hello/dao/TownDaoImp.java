package fr.diginamic.hello.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.model.Town;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class TownDaoImp implements TownDao {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public Page<Town> getTowns(Pageable page) {
		try {
			List<Town> towns = em.createQuery("select t from Town t", Town.class).setFirstResult((int) page.getOffset())
					.setMaxResults(page.getPageSize()).getResultList();

			long nbTowns = em.createQuery("select count(t) from Town t", Long.class).getSingleResult();
			return new PageImpl<>(towns, page, (int) nbTowns);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public List<Town> getTowns() {
		try {
			List<Town> towns = em.createQuery("select t from Town t", Town.class).getResultList();

			return towns;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public Town getTownById(Long townId) {
		try {
			return em.find(Town.class, townId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public Town createTown(Town town) {
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
	public Town updateTownById(Town town, Long townId) {
		Town townExists = getTownById(townId);

		if (townExists == null) {
			return null;
		}

		if (town.getName() != null) {
			townExists.setName(town.getName());
		}
		if (town.getNbInhabitants() != null) {
			townExists.setNbInhabitants(town.getNbInhabitants());

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
	public boolean deleteTownById(Long townId) {
		Town townExists = getTownById(townId);

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
	@Transactional
	public Page<Town> getTownByName(String townName, Pageable page) {
		try {
			List<Town> towns = em.createQuery("select t from Town t " + "where t.name = :p1", Town.class)
					.setParameter("p1", townName).setFirstResult((int) page.getOffset())
					.setMaxResults(page.getPageSize()).getResultList();

			long nbTowns = em.createQuery("select count(t) from Town t " + "where t.name = :p1", Long.class)
					.setParameter("p1", townName).getSingleResult();
			return new PageImpl<>(towns, page, (int) nbTowns);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Transactional
	public List<Town> getTownByName(String townName) {
		try {
			List<Town> towns = em.createQuery("select t from Town t " + "where t.name = :p1", Town.class)
					.setParameter("p1", townName)
					.getResultList();

			return towns;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Page<Town> getNTopTownsFromDepartment(int topN, String departmentCode, Pageable page) {
		try {
			List<Town> towns = em
					.createQuery("select t from Department d join d.towns t " + "where d.code = :p1 "
							+ "order by t.nbInhabitants desc", Town.class)
					.setParameter("p1", departmentCode).setFirstResult((int) page.getOffset()).setMaxResults(topN)
					.getResultList();

			long nbTowns = em
					.createQuery("select count(t) from Department d join d.towns t " + "where d.code = :p1", Long.class)
					.setParameter("p1", departmentCode).getSingleResult();

			return new PageImpl<>(towns, page, (int) nbTowns);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Page<Town> getTownsMinMaxFromDepartment(Long minInhabitants, Long maxInhabitants, String departmentCode,
			Pageable page) {
		try {
			List<Town> towns = em
					.createQuery("select t from Department d " + "join d.towns t " + "where d.code = :p1 "
							+ "and (t.nbInhabitants >= :p2) " + "and (t.nbInhabitants <= :p3)", Town.class)
					.setParameter("p1", departmentCode).setParameter("p2", minInhabitants)
					.setParameter("p3", maxInhabitants).setFirstResult((int) page.getOffset())
					.setMaxResults(page.getPageSize()).getResultList();

			long nbTowns = em
					.createQuery("select count(t) from Department d join d.towns t " + "where d.code = :p1 "
							+ "and (t.nbInhabitants >= :p2) " + "and (t.nbInhabitants <= :p3)", Long.class)
					.setParameter("p1", departmentCode).setParameter("p2", minInhabitants)
					.setParameter("p3", maxInhabitants).getSingleResult();

			return new PageImpl<>(towns, page, (int) nbTowns);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
