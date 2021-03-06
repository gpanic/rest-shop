package restshop.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import restshop.entities.Artikel;
import restshop.entities.Proizvajalec;

public class ArtikelDAO extends DAO<Artikel> {

	@Override
	public Artikel create(Artikel entity) {
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
	public Artikel read(int id) {
		Artikel entity;	
		em=emf.createEntityManager();
		try {
			TypedQuery<Artikel> q=em.createQuery("SELECT x FROM Artikel x WHERE x.id_artikel = :id ", Artikel.class);
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
	public boolean update(Artikel entity) {
		em=emf.createEntityManager();
		try {
			TypedQuery<Artikel> q=em.createQuery("SELECT x FROM Artikel x WHERE x.id_artikel = :id ", Artikel.class);
			q.setParameter("id", entity.getId_artikel());
			Artikel old=q.getSingleResult();
			
			Proizvajalec p=null;
			try {
				TypedQuery<Proizvajalec> q2=em.createQuery("SELECT x FROM Proizvajalec x WHERE x.id_proizvajalec = :id ", Proizvajalec.class);
				q2.setParameter("id", entity.getProizvajalec().getId_proizvajalec());
				p=q2.getSingleResult();
			} catch (NoResultException e) {
				
			}

			em.getTransaction().begin();
			old.setCena(entity.getCena());
			old.setNaziv(entity.getNaziv());
			old.setProizvajalec(p);
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
			TypedQuery<Artikel> q=em.createQuery("SELECT x FROM Artikel x WHERE x.id_artikel = :id ", Artikel.class);
			q.setParameter("id", id);
			Artikel entity=q.getSingleResult();
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
	public List<Artikel> list() {
		List<Artikel> entities;
		em=emf.createEntityManager();
		try {
			TypedQuery<Artikel> q=em.createQuery("SELECT x FROM Artikel x ", Artikel.class);
			entities=q.getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entities;
	}

}
