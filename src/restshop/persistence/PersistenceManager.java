package restshop.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {
	
	private static final String PERSISTENCE_UNIT_NAME = "restshop";
	private static EntityManagerFactory emf;
	
	public static EntityManagerFactory getEntityManagerFactoryInstance() {
		if(emf!=null) {
			return emf;
		} else {
			return emf=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
	}

}
