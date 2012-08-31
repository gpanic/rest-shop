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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import restshop.dao.UporabnikDAO;
import restshop.entities.Uporabnik;
import restshop.entities.lists.UporabnikList;

@Path("/uporabniki")
public class UporabnikResource extends Resource<Uporabnik> {
	
	UporabnikDAO udao=new UporabnikDAO();

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response create(Uporabnik entity) {
		udao.create(entity);
		UriBuilder ub=uriInfo.getBaseUriBuilder();
		URI uri=ub.path(UporabnikResource.class).path(Integer.toString(entity.getId_uporabnik().intValue())).build();
		return Response.created(uri).build();
	}

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response read(@PathParam("id") int id) {
		Uporabnik entity=udao.read(id);
		if(entity!=null) {
			return Response.ok(entity).build();
		} else {
			return Response.status(404).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response update(@PathParam("id") int id, Uporabnik entity) {
		entity.setId_uporabnik(new Long(id));
		boolean updated=udao.update(entity);
		if(updated) {
			return Response.ok().build();
		} else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		boolean deleted=udao.delete(id);
		if(deleted) {
			return Response.ok().build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response list() {
		UporabnikList list=new UporabnikList(udao.list());
		return Response.ok(list).build();
	}

}
