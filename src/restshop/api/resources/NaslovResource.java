package restshop.api.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import restshop.dao.NaslovDAO;
import restshop.entities.Naslov;

@Path("/naslovi")
public class NaslovResource {
	
	@Context UriInfo uriInfo;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Naslov> list() {
		NaslovDAO ndao=new NaslovDAO();
		return ndao.list();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Naslov read(@PathParam("id") int id) {
		NaslovDAO ndao=new NaslovDAO();
		UriBuilder ub=uriInfo.getBaseUriBuilder();
		ub.path(NaslovResource.class).path(Integer.toString(id));
		System.out.println(ub.build());
		return ndao.read(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Naslov naslov) {
		NaslovDAO ndao=new NaslovDAO();
		Naslov entity=ndao.create(naslov);
		UriBuilder ub=uriInfo.getBaseUriBuilder();
		URI uri=ub.path(NaslovResource.class).path(Integer.toString(entity.getId_naslov().intValue())).build();
		return Response.created(uri).build();
	}

}
