package restshop.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import restshop.entities.Uporabnik;
import restshop.entities.UporabnikVloga;
import restshop.entities.Vloga;

public class UporabnikVlogaDAO extends DAO<UporabnikVloga> {

	@Override
	public UporabnikVloga create(UporabnikVloga entity) {
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
			TypedQuery<UporabnikVloga> q=em.createQuery("SELECT x FROM UporabnikVloga x WHERE x.id_uporabnikbloga = :id ", UporabnikVloga.class);
			q.setParameter("id", entity.getId_uporabnikvloga());
			UporabnikVloga old=q.getSingleResult();
			
			Vloga v=null;
			Uporabnik u=null;
			try {
				TypedQuery<Vloga> q2=em.createQuery("SELECT x FROM Vloga x WHERE x.id_vloga = :id ", Vloga.class);
				q2.setParameter("id", entity.getVloga().getId_vloga());
				v=q2.getSingleResult();
				TypedQuery<Uporabnik> q3=em.createQuery("SELECT x FROM Uporabnik x WHERE x.id_uporabnik = :id ", Uporabnik.class);
				q3.setParameter("id", entity.getUporabnik().getId_uporabnik());
				u=q3.getSingleResult();
			} catch (NoResultException e) {
				
			}

			em.getTransaction().begin();
			old.setNaziv(entity.getNaziv());
			old.setUp_ime(entity.getUp_ime());
			old.setUporabnik(u);
			old.setVloga(v);
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
