package restshop.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import restshop.entities.Uporabnik;

public class UporabnikDAO extends DAO<Uporabnik> {

	@Override
	public Uporabnik create(Uporabnik entity) {
		em=emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return entity;
	}

	@Override
	public Uporabnik read(int id) {
		Uporabnik entity;	
		em=emf.createEntityManager();
		try {
			TypedQuery<Uporabnik> q=em.createQuery("SELECT x FROM Uporabnik x WHERE x.id_uporabnik = :id ", Uporabnik.class);
			q.setParameter("id", id);
			entity=q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entity;
	}

	@Override
	public boolean update(Uporabnik entity) {
		em=emf.createEntityManager();
		try {
			TypedQuery<Uporabnik> q=em.createQuery("SELECT x FROM Uporabnik x WHERE x.id_uporabnik = :id ", Uporabnik.class);
			q.setParameter("id", entity.getId_uporabnik());
			q.getSingleResult();
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} catch (NoResultException e) {
			return false;
		} finally {
			em.close();
		}
		return true;
	}

	@Override
	public boolean delete(int id) {
		em=emf.createEntityManager();
		try {
			TypedQuery<Uporabnik> q=em.createQuery("SELECT x FROM Uporabnik x WHERE x.id_uporabnik = :id ", Uporabnik.class);
			q.setParameter("id", id);
			Uporabnik entity=q.getSingleResult();
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
		} catch (NoResultException e) {
			return false;
		} finally {
			em.close();
		}
		return true;
	}

	@Override
	public List<Uporabnik> list() {
		List<Uporabnik> entities;
		em=emf.createEntityManager();
		try {
			TypedQuery<Uporabnik> q=em.createQuery("SELECT x FROM Uporabnik x ", Uporabnik.class);
			entities=q.getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entities;
	}

}
