package restshop.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import restshop.entities.Narocilo;
import restshop.entities.Stanje;

public class NarociloDAO extends DAO<Narocilo> {

	@Override
	public Narocilo create(Narocilo entity) {
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
	
	public Narocilo read(int id, int id_uporabnik) {
		Narocilo entity;	
		em=emf.createEntityManager();
		try {
			TypedQuery<Narocilo> q=em.createQuery("SELECT x FROM Narocilo x WHERE x.id_narocilo = :id AND x.uporabnik.id_uporabnik = :id_uporabnik ", Narocilo.class);
			q.setParameter("id", id);
			q.setParameter("id_uporabnik", id_uporabnik);
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
			Narocilo old=q.getSingleResult();
			
			Stanje s=null;
			try {
				TypedQuery<Stanje> q2=em.createQuery("SELECT x FROM Stanje x WHERE x.id_stanje = :id ", Stanje.class);
				q2.setParameter("id", entity.getStanje().getId_stanje());
				s=q2.getSingleResult();
			} catch (NoResultException e) {
				
			}
			
			em.getTransaction().begin();
			old.setStanje(s);
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
	
	public List<Narocilo> list(int id_uporabnik) {
		List<Narocilo> entities;
		em=emf.createEntityManager();
		try {
			TypedQuery<Narocilo> q=em.createQuery("SELECT x FROM Narocilo x WHERE x.uporabnik.id_uporabnik = :id ", Narocilo.class);
			q.setParameter("id", id_uporabnik);
			entities=q.getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entities;
	}

}
