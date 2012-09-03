package restshop.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import restshop.entities.Stanje;

public class StanjeDAO extends DAO<Stanje> {

	@Override
	public Stanje create(Stanje entity) {
		em=emf.createEntityManager();
		try {
			TypedQuery<Stanje> q=em.createQuery("SELECT x FROM Stanje x WHERE x.naziv = :naziv ", Stanje.class);
			q.setParameter("naziv", entity.getNaziv());
			Stanje s=null;
			try {
				s=q.getSingleResult();
			} catch (NoResultException e) {
			}
			if(s==null) {
				em.getTransaction().begin();
				em.persist(entity);
				em.getTransaction().commit();
			} else {
				return null;
			}
		} finally {
			em.close();
		}
		return entity;
	}

	@Override
	public Stanje read(int id) {
		Stanje entity;	
		em=emf.createEntityManager();
		try {
			TypedQuery<Stanje> q=em.createQuery("SELECT x FROM Stanje x WHERE x.id_stanje = :id ", Stanje.class);
			q.setParameter("id", id);
			entity=q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entity;
	}
	
	public Stanje read(String naziv) {
		Stanje entity;	
		em=emf.createEntityManager();
		try {
			TypedQuery<Stanje> q=em.createQuery("SELECT x FROM Stanje x WHERE x.naziv = :id ", Stanje.class);
			q.setParameter("id", naziv);
			entity=q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entity;
	}

	@Override
	public boolean update(Stanje entity) {
		em=emf.createEntityManager();
		try {
			TypedQuery<Stanje> q=em.createQuery("SELECT x FROM Stanje x WHERE x.id_stanje = :id ", Stanje.class);
			q.setParameter("id", entity.getId_stanje());
			Stanje old=q.getSingleResult();
			
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
			TypedQuery<Stanje> q=em.createQuery("SELECT x FROM Stanje x WHERE x.id_stanje = :id ", Stanje.class);
			q.setParameter("id", id);
			Stanje entity=q.getSingleResult();
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
	public List<Stanje> list() {
		List<Stanje> entities;
		em=emf.createEntityManager();
		try {
			TypedQuery<Stanje> q=em.createQuery("SELECT x FROM Stanje x ", Stanje.class);
			entities=q.getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entities;
	}

}
