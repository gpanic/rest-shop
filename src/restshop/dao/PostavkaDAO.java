package restshop.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import restshop.entities.Artikel;
import restshop.entities.Postavka;

public class PostavkaDAO extends DAO<Postavka> {

	@Override
	public Postavka create(Postavka entity) {
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
	public Postavka read(int id) {
		Postavka entity;	
		em=emf.createEntityManager();
		try {
			TypedQuery<Postavka> q=em.createQuery("SELECT x FROM Postavka x WHERE x.id_postavka = :id ", Postavka.class);
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
	public boolean update(Postavka entity) {
		em=emf.createEntityManager();
		try {
			TypedQuery<Postavka> q=em.createQuery("SELECT x FROM Postavka x WHERE x.id_postavka = :id ", Postavka.class);
			q.setParameter("id", entity.getId_postavka());
			Postavka old=q.getSingleResult();
			
			Artikel a=null;
			try {
				TypedQuery<Artikel> q2=em.createQuery("SELECT x FROM Artikel x WHERE x.id_artikel = :id ", Artikel.class);
				q2.setParameter("id", entity.getArtikel().getId_artikel());
				a=q2.getSingleResult();
			} catch (NoResultException e) {
				
			}

			em.getTransaction().begin();
			old.setArtikel(a);
			old.setKolicina(entity.getKolicina());
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
			TypedQuery<Postavka> q=em.createQuery("SELECT x FROM Postavka x WHERE x.id_postavka = :id ", Postavka.class);
			q.setParameter("id", id);
			Postavka entity=q.getSingleResult();
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
	public List<Postavka> list() {
		List<Postavka> entities;
		em=emf.createEntityManager();
		try {
			TypedQuery<Postavka> q=em.createQuery("SELECT x FROM Postavka x ", Postavka.class);
			entities=q.getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entities;
	}
	
	public List<Postavka> list(int id_uporabnik) {
		List<Postavka> entities;
		em=emf.createEntityManager();
		try {
			TypedQuery<Postavka> q=em.createQuery("SELECT x FROM Postavka x WHERE x.narocilo.uporabnik.id_uporabnik = :id ", Postavka.class);
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
