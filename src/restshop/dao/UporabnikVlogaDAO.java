package restshop.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import restshop.entities.UporabnikVloga;

public class UporabnikVlogaDAO extends DAO<UporabnikVloga> {

	@Override
	public UporabnikVloga create(UporabnikVloga entity) {
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
	public UporabnikVloga read(int id) {
		UporabnikVloga entity;	
		em=emf.createEntityManager();
		try {
			TypedQuery<UporabnikVloga> q=em.createQuery("SELECT x FROM UporabnikVloga x WHERE x.id_uporabnikvloga = :id ", UporabnikVloga.class);
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
	public boolean update(UporabnikVloga entity) {
		em=emf.createEntityManager();
		try {
			TypedQuery<UporabnikVloga> q=em.createQuery("SELECT x FROM UporabnikVloga x WHERE x.id_uporabnikvloga = :id ", UporabnikVloga.class);
			q.setParameter("id", entity.getId_uporabnikvloga());
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
			TypedQuery<UporabnikVloga> q=em.createQuery("SELECT x FROM UporabnikVloga x WHERE x.id_uporabnikvloga = :id ", UporabnikVloga.class);
			q.setParameter("id", id);
			UporabnikVloga entity=q.getSingleResult();
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
	public List<UporabnikVloga> list() {
		List<UporabnikVloga> entities;
		em=emf.createEntityManager();
		try {
			TypedQuery<UporabnikVloga> q=em.createQuery("SELECT x FROM UporabnikVloga x ", UporabnikVloga.class);
			entities=q.getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entities;
	}

}
