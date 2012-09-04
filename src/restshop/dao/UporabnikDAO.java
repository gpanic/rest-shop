package restshop.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import restshop.entities.Naslov;
import restshop.entities.Uporabnik;

public class UporabnikDAO extends DAO<Uporabnik> {

	@Override
	public Uporabnik create(Uporabnik entity) {
		em=emf.createEntityManager();
		try {
			TypedQuery<Uporabnik> q=em.createQuery("SELECT x FROM Uporabnik x WHERE x.up_ime = :up_ime ", Uporabnik.class);
			q.setParameter("up_ime", entity.getUp_ime());
			Uporabnik u=null;
			try {
				u=q.getSingleResult();
			} catch (NoResultException e) {
			}
			if(u==null) {
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
	public Uporabnik read(int id) {
		Uporabnik entity;	
		em=emf.createEntityManager();
		try {
			TypedQuery<Uporabnik> q=em.createQuery("SELECT x FROM Uporabnik x WHERE x.id_uporabnik = :id ", Uporabnik.class);
			q.setParameter("id", id);
			entity=q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entity;
	}
	
	public Uporabnik read(String up_ime) {
		Uporabnik entity;	
		em=emf.createEntityManager();
		try {
			TypedQuery<Uporabnik> q=em.createQuery("SELECT x FROM Uporabnik x WHERE x.up_ime = :up_ime ", Uporabnik.class);
			q.setParameter("up_ime", up_ime);
			entity=q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entity;
	}

	@Override
	public boolean update(Uporabnik entity) {
		em=emf.createEntityManager();
		try {
			TypedQuery<Uporabnik> q=em.createQuery("SELECT x FROM Uporabnik x WHERE x.id_uporabnik = :id ", Uporabnik.class);
			q.setParameter("id", entity.getId_uporabnik());
			Uporabnik old=q.getSingleResult();
			
			Naslov n=null;
			try {
				TypedQuery<Naslov> q2=em.createQuery("SELECT x FROM Naslov x WHERE x.id_naslov = :id ", Naslov.class);
				q2.setParameter("id", entity.getNaslov().getId_naslov());
				n=q2.getSingleResult();
			} catch (NoResultException e) {
				
			}

			em.getTransaction().begin();
			old.setE_naslov(entity.getE_naslov());
			old.setNaslov(n);
			old.setGeslo(entity.getGeslo());
			old.setIme(entity.getIme());
			old.setPriimek(entity.getPriimek());
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
			TypedQuery<Uporabnik> q=em.createQuery("SELECT x FROM Uporabnik x WHERE x.id_uporabnik = :id ", Uporabnik.class);
			q.setParameter("id", id);
			Uporabnik entity=q.getSingleResult();
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
	public List<Uporabnik> list() {
		List<Uporabnik> entities;
		em=emf.createEntityManager();
		try {
			TypedQuery<Uporabnik> q=em.createQuery("SELECT x FROM Uporabnik x ", Uporabnik.class);
			entities=q.getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return entities;
	}

}
