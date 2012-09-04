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
import javax.ws.rs.core.UriInfo;

import restshop.dao.NaslovDAO;
import restshop.dao.UporabnikDAO;
import restshop.entities.Naslov;
import restshop.entities.Uporabnik;
import restshop.entities.lists.NaslovList;

@Path("/naslovi")
public class NaslovResource extends Resource<Naslov> {

	@Context UriInfo uriInfo;
	NaslovDAO ndao=new NaslovDAO();
	UporabnikDAO udao=new UporabnikDAO();
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response create(@Context SecurityContext sc, Naslov entity) {
		if(sc.isUserInRole("admin")) {
			ndao.create(entity);
			UriBuilder ub=uriInfo.getBaseUriBuilder();
			URI uri=ub.path(NaslovResource.class).path(Integer.toString(entity.getId_naslov())).build();
			return Response.created(uri).entity(entity).build();
		} else if(sc.isUserInRole("uporabnik")) {
			Uporabnik u=udao.read(sc.getUserPrincipal().getName());
			if(u.getNaslov()==null) {
				ndao.create(entity);
				u.setNaslov(entity);
				udao.update(u);
				UriBuilder ub=uriInfo.getBaseUriBuilder();
				URI uri=ub.path(NaslovResource.class).path(Integer.toString(entity.getId_naslov())).build();
				return Response.created(uri).entity(entity).build();
			} else {
				return Response.status(400).entity("You already have an address").build();
			}
		} else {
			return Response.status(404).build();
		}
	}
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response read(@Context SecurityContext sc, @PathParam("id") int id) {
		Naslov entity=ndao.read(id);
		if(entity!=null) {
			if(sc.isUserInRole("admin")) {
				return Response.ok(entity).build();
			} else if(sc.isUserInRole("uporabnik")) {
				Uporabnik u=udao.read(sc.getUserPrincipal().getName());
				if(u.getNaslov()!=null) {
					if(u.getNaslov().getId_naslov()==id) {
						return Response.ok(entity).build();
					} else {
						return Response.status(404).build();
					}
				} else {
					return Response.status(404).build();
				}
			} else {
				return Response.status(404).build();
			}
		} else {
			return Response.status(404).build();
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response update(@Context SecurityContext sc, @PathParam("id") int id, Naslov entity) {
		entity.setId_naslov(id);
		Naslov old=ndao.read(id);
		boolean updated=false;
		if(sc.isUserInRole("admin")) {
			updated=ndao.update(entity);
		} else if(sc.isUserInRole("uporabnik")) {
			Uporabnik u=udao.read(sc.getUserPrincipal().getName());
			if(u.getNaslov()!=null) {
				if(u.getNaslov().getId_naslov()==old.getId_naslov()) {
					updated=ndao.update(entity);
				}
			}
			
		}
		if(updated) {
			return Response.ok("Resource updated").build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@Context SecurityContext sc, @PathParam("id") int id) {
		boolean deleted=ndao.delete(id);
		if(deleted) {
			return Response.ok().entity("Resource deleted").build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response list(@Context SecurityContext sc) {
		NaslovList list=null;
		if(sc.isUserInRole("admin")) {
			list=new NaslovList(ndao.list());
			return Response.ok(list).build();
		} else if(sc.isUserInRole("uporabnik")) {
			Uporabnik u=udao.read(sc.getUserPrincipal().getName());
			list=new NaslovList(ndao.list(u.getId_uporabnik()));
			return Response.ok(list).build();
		} else {
			return null;
		}
	}
	
}
