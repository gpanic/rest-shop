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

import restshop.dao.ProizvajalecDAO;
import restshop.entities.Proizvajalec;
import restshop.entities.lists.ProizvajalecList;

@Path("/proizvajalci")
public class ProizvajalecResource extends Resource<Proizvajalec> {
	
	ProizvajalecDAO pdao=new ProizvajalecDAO();

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response create(Proizvajalec entity) {
		pdao.create(entity);
		UriBuilder ub=uriInfo.getBaseUriBuilder();
		URI uri=ub.path(ProizvajalecResource.class).path(Integer.toString(entity.getId_proizvajalec().intValue())).build();
		return Response.created(uri).build();
	}

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response read(@PathParam("id") int id) {
		Proizvajalec entity=pdao.read(id);
		if(entity!=null) {
			return Response.ok(entity).build();
		} else {
			return Response.status(404).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response update(@PathParam("id") int id, Proizvajalec entity) {
		entity.setId_proizvajalec(new Long(id));
		boolean updated=pdao.update(entity);
		if(updated) {
			return Response.ok().build();
		} else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		boolean deleted=pdao.delete(id);
		if(deleted) {
			return Response.ok().build();
		} else {
			return Response.status(404).build();
		}
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response list() {
		ProizvajalecList list=new ProizvajalecList(pdao.list());
		return Response.ok(list).build();
	}

}
