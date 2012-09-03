package restshop.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import restshop.entities.Vloga;

public class VlogaDAO extends DAO<Vloga> {

	@Override
	public Vloga create(Vloga entity) {
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
	public Vloga read(int id) {
		Vloga entity;	
		em=emf.createEntityManager();
		try {
			TypedQuery<Vloga> q=em.createQuery("SELECT x FROM Vloga x WHERE x.id_vloga = :id ", Vloga.class);
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
	public boolean update(Vloga entity) {
		em=emf.createEntityManager();
		try {
			TypedQuery<Vloga> q=em.createQuery("SELECT x FROM Vloga x WHERE x.id_vloga = :id ", Vloga.class);
			q.setParameter("id", entity.getId_vloga());
			Vloga old=q.getSingleResult();
			
			em.getTransaction().begin();
			old.setNaziv(entity.getNaziv());
			old.setOpis(entity.getOpis());
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
			TypedQuery<Vloga> q=em.createQuery("SELECT x FROM Vloga x WHERE x.id_vloga = :id ", Vloga.class);
			q.setParameter("id", id);
			Vloga entity=q.getSingleResult();
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
	public List<Vloga> list() {
		List<Vloga> entities;
		em=emf.createEntityManager();
		try {
			TypedQuery<Vloga> q=em.createQuery("SELECT x FROM Vloga x ", Vloga.class);
			entities=q.getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entities;
	}

}
