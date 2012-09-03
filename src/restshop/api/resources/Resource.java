package restshop.api.resources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

public abstract class Resource<Entity> {
	
	@Context UriInfo uriInfo;
	
	public abstract Response create(SecurityContext sc, Entity entity);
	public abstract Response read(SecurityContext sc, int id);
	public abstract Response update(SecurityContext sc, int id, Entity entity);
	public abstract Response delete(SecurityContext sc, int id);
	public abstract Response list(SecurityContext sc);

}
