package restshop.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import restshop.entities.Naslov;

public class NaslovDAO extends DAO<Naslov> {

	@Override
	public boolean create(Naslov entity) {
		em=emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return true;
	}

	@Override
	public Naslov read(int id) {
		Naslov entity;	
		em=emf.createEntityManager();
		try {
			TypedQuery<Naslov> q=em.createQuery("SELECT x FROM Naslov x WHERE x.id_naslov = :id_naslov ", Naslov.class);
			q.setParameter("id_naslov", id);
			entity=q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entity;
	}

	@Override
	public boolean update(Naslov entity) {
		em=emf.createEntityManager();
		try {
			TypedQuery<Naslov> q=em.createQuery("SELECT x FROM Naslov x WHERE x.id_naslov = :id_naslov ", Naslov.class);
			q.setParameter("id_naslov", entity.getId_naslov());
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
			TypedQuery<Naslov> q=em.createQuery("SELECT x FROM Naslov x WHERE x.id_naslov = :id_naslov ", Naslov.class);
			q.setParameter("id_naslov", id);
			Naslov entity=q.getSingleResult();
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
}
