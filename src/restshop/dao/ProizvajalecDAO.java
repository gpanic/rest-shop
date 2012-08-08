package restshop.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import restshop.entities.Proizvajalec;

public class ProizvajalecDAO extends DAO<Proizvajalec> {

	@Override
	public Proizvajalec create(Proizvajalec entity) {
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
	public Proizvajalec read(int id) {
		Proizvajalec entity;	
		em=emf.createEntityManager();
		try {
			TypedQuery<Proizvajalec> q=em.createQuery("SELECT x FROM Proizvajalec x WHERE x.id_proizvajalec = :id_proizvajalec ", Proizvajalec.class);
			q.setParameter("id_proizvajalec", id);
			entity=q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entity;
	}

	@Override
	public boolean update(Proizvajalec entity) {
		em=emf.createEntityManager();
		try {
			TypedQuery<Proizvajalec> q=em.createQuery("SELECT x FROM Proizvajalec x WHERE x.id_proizvajalec = :id ", Proizvajalec.class);
			q.setParameter("id", entity.getId_proizvajalec());
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
			TypedQuery<Proizvajalec> q=em.createQuery("SELECT x FROM Proizvajalec x WHERE x.id_proizvajalec = :id ", Proizvajalec.class);
			q.setParameter("id", id);
			Proizvajalec entity=q.getSingleResult();
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
	public List<Proizvajalec> list() {
		List<Proizvajalec> entities;
		em=emf.createEntityManager();
		try {
			TypedQuery<Proizvajalec> q=em.createQuery("SELECT x FROM Proizvajalec x ", Proizvajalec.class);
			entities=q.getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entities;
	}

}
