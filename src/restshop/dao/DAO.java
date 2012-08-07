package restshop.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import restshop.persistence.PersistenceManager;

public abstract class DAO<Entity> {
	
	protected EntityManagerFactory emf;
	protected EntityManager em;
	
	public DAO() {
		emf=PersistenceManager.getEntityManagerFactoryInstance();
	}
	
	abstract public boolean create(Entity entity);
	
	abstract public Entity read(int id);
	
	abstract public boolean update(Entity entity);
	
	abstract public boolean delete(int id);

}
