package restshop.api.resources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public abstract class Resource<Entity> {
	
	@Context UriInfo uriInfo;
	
	public abstract Response create(Entity entity);
	public abstract Response read(int id);
	public abstract Response update(int id, Entity entity);
	public abstract Response delete(int id);
	public abstract Response list();

}
