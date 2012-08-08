package restshop.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import restshop.entities.Narocilo;

public class NarociloDAO extends DAO<Narocilo> {

	@Override
	public Narocilo create(Narocilo entity) {
		em=emf.createEntityManager();
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
	public Narocilo read(int id) {
		Narocilo entity;	
		em=emf.createEntityManager();
		try {
			TypedQuery<Narocilo> q=em.createQuery("SELECT x FROM Narocilo x WHERE x.id_narocilo = :id ", Narocilo.class);
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
	public boolean update(Narocilo entity) {
		em=emf.createEntityManager();
		try {
			TypedQuery<Narocilo> q=em.createQuery("SELECT x FROM Narocilo x WHERE x.id_narocilo = :id ", Narocilo.class);
			q.setParameter("id", entity.getId_narocilo());
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
			TypedQuery<Narocilo> q=em.createQuery("SELECT x FROM Narocilo x WHERE x.id_narocilo = :id ", Narocilo.class);
			q.setParameter("id", id);
			Narocilo entity=q.getSingleResult();
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
	public List<Narocilo> list() {
		List<Narocilo> entities;
		em=emf.createEntityManager();
		try {
			TypedQuery<Narocilo> q=em.createQuery("SELECT x FROM Narocilo x ", Narocilo.class);
			entities=q.getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entities;
	}

}
