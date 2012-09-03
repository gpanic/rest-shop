package restshop.api.resources;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;

import restshop.dao.StanjeDAO;
import restshop.entities.Stanje;
import restshop.entities.lists.StanjeList;

@Path("/stanja")
public class StanjeResource extends Resource<Stanje> {
	
	StanjeDAO sdao=new StanjeDAO();

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response create(@Context SecurityContext sc, Stanje entity) {
		Stanje s=sdao.create(entity);
		if(s!=null) {
			UriBuilder ub=uriInfo.getBaseUriBuilder();
			URI uri=ub.path(StanjeResource.class).path(Integer.toString(entity.getId_stanje())).build();
			return Response.created(uri).entity(entity).build();
		} else {
			return Response.status(400).entity("State not provided or already exists").build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response read(@Context SecurityContext sc, @PathParam("id") int id) {
		Stanje entity=sdao.read(id);
		if(entity!=null) {
			return Response.ok(entity).build();
		} else {
			return Response.status(404).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response update(@Context SecurityContext sc, @PathParam("id") int id, Stanje entity) {
		entity.setId_stanje(id);
		boolean updated=sdao.update(entity);
		if(updated) {
			return Response.ok().entity("Resource updated").build();
		} else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@Context SecurityContext sc, @PathParam("id") int id) {
		boolean deleted=sdao.delete(id);
		if(deleted) {
			return Response.ok().entity("Resource deleted").build();
		} else {
			return Response.status(404).build();
		}
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response list(@Context SecurityContext sc) {
		StanjeList list=new StanjeList(sdao.list());
		return Response.ok(list).build();
	}

}
