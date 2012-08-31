package restshop.api.resources;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import restshop.dao.VlogaDAO;
import restshop.entities.Vloga;
import restshop.entities.lists.VlogaList;

@Path("/vloge")
public class VlogaResource extends Resource<Vloga> {
	
	VlogaDAO vdao=new VlogaDAO();

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response create(Vloga entity) {
		vdao.create(entity);
		UriBuilder ub=uriInfo.getBaseUriBuilder();
		URI uri=ub.path(VlogaResource.class).path(Integer.toString(entity.getId_vloga().intValue())).build();
		return Response.created(uri).build();
	}

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response read(int id) {
		Vloga entity=vdao.read(id);
		if(entity!=null) {
			return Response.ok(entity).build();
		} else {
			return Response.status(404).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response update(int id, Vloga entity) {
		entity.setId_vloga(new Long(id));
		boolean updated=vdao.update(entity);
		if(updated) {
			return Response.ok().build();
		} else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response delete(int id) {
		boolean deleted=vdao.delete(id);
		if(deleted) {
			return Response.ok().build();
		} else {
			return Response.status(404).build();
		}
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response list() {
		VlogaList list=new VlogaList(vdao.list());
		return Response.ok(list).build();
	}

}
