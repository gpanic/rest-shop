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

import restshop.dao.NaslovDAO;
import restshop.entities.Naslov;
import restshop.entities.lists.NaslovList;

@Path("/naslovi")
public class NaslovResource extends Resource<Naslov> {

	NaslovDAO ndao=new NaslovDAO();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Naslov entity) {
		ndao.create(entity);
		UriBuilder ub=uriInfo.getBaseUriBuilder();
		URI uri=ub.path(NaslovResource.class).path(Integer.toString(entity.getId_naslov().intValue())).build();
		return Response.created(uri).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("id") int id) {
		Naslov entity=ndao.read(id);
		if(entity!=null) {
			return Response.ok(entity).build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Naslov entity) {
		entity.setId_naslov(new Long(id));
		boolean updated=ndao.update(entity);
		if(updated) {
			return Response.ok().build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		boolean deleted=ndao.delete(id);
		if(deleted) {
			return Response.ok().build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {
		NaslovList list=new NaslovList(ndao.list());
		return Response.ok(list).build();
	}
	
}
