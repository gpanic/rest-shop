package restshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import restshop.entities.Naslov;
import restshop.entities.Uporabnik;

public class NaslovDAO extends DAO<Naslov> {

	@Override
	public Naslov create(Naslov entity) {
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
	public Naslov read(int id) {
		Naslov entity;	
		em=emf.createEntityManager();
		try {
			TypedQuery<Naslov> q=em.createQuery("SELECT x FROM Naslov x WHERE x.id_naslov = :id ", Naslov.class);
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
	public boolean update(Naslov entity) {
		em=emf.createEntityManager();
		try {
			TypedQuery<Naslov> q=em.createQuery("SELECT x FROM Naslov x WHERE x.id_naslov = :id ", Naslov.class);
			q.setParameter("id", entity.getId_naslov());
			Naslov old=q.getSingleResult();

			em.getTransaction().begin();
			old.setDrzava(entity.getDrzava());
			old.setKraj(entity.getKraj());
			old.setPosta(entity.getPosta());
			old.setUlica(entity.getUlica());
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
			TypedQuery<Naslov> q=em.createQuery("SELECT x FROM Naslov x WHERE x.id_naslov = :id ", Naslov.class);
			q.setParameter("id", id);
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

	@Override
	public List<Naslov> list() {
		List<Naslov> entities;
		em=emf.createEntityManager();
		try {
			TypedQuery<Naslov> q=em.createQuery("SELECT x FROM Naslov x ", Naslov.class);
			entities=q.getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entities;
	}
	
	public List<Naslov> list(int id_uporabnik) {
		List<Naslov> entities;
		em=emf.createEntityManager();
		try {
			TypedQuery<Uporabnik> q=em.createQuery("SELECT x FROM Uporabnik x where x.id_uporabnik = :id ", Uporabnik.class);
			q.setParameter("id", id_uporabnik);
			Uporabnik u=q.getSingleResult();
			entities=new ArrayList<Naslov>();
			entities.add(u.getNaslov());
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entities;
	}
}
