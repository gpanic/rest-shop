package restshop.api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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

import restshop.dao.UporabnikDAO;
import restshop.entities.Uporabnik;
import restshop.entities.lists.UporabnikList;
import restshop.security.Hasher;

@Path("/uporabniki")
public class UporabnikResource extends Resource<Uporabnik> {
	
	UporabnikDAO udao=new UporabnikDAO();

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response create(@Context SecurityContext sc, Uporabnik entity) {
		Hasher h=new Hasher("SHA-256");
		entity.setGeslo(h.hash(entity.getGeslo()));
		Uporabnik u=udao.create(entity);
		if(u!=null) {
			UriBuilder ub=uriInfo.getBaseUriBuilder();
			URI uri=ub.path(UporabnikResource.class).path(Integer.toString(entity.getId_uporabnik())).build();
			return Response.created(uri).entity(entity).build();
		} else {
			return Response.status(400).entity("Username not provided or already exists").build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response read(@Context SecurityContext sc, @PathParam("id") int id) {
		Uporabnik entity=udao.read(id);
		if(entity!=null) {
			if(sc.isUserInRole("admin")) {
				return Response.ok(entity).build();
			} else if(sc.isUserInRole("uporabnik")) {
				if(entity.getUp_ime().equals(sc.getUserPrincipal().getName())) {
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
	}

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response update(@Context SecurityContext sc, @PathParam("id") int id, Uporabnik entity) {
		entity.setId_uporabnik(id);
		Uporabnik old=udao.read(id);
		boolean updated=false;
		if(sc.isUserInRole("admin")) {
			updated=udao.update(entity);
		} else if(sc.isUserInRole("uporabnik")) {
			if(old.getUp_ime().equals(sc.getUserPrincipal().getName())) {
				if(entity.getGeslo()!=null) {
					if(!entity.getGeslo().equals("")) {
						Hasher h=new Hasher("SHA-256");
						entity.setNaslov(old.getNaslov());
						entity.setGeslo(h.hash(entity.getGeslo()));
						updated=udao.update(entity);
					} else {
						return Response.status(400).entity("State invalid or missing").build();
					}
				} else {
					return Response.status(400).entity("Password invalid or missing").build();
				}
			}
		}
		if(updated) {
			return Response.ok().entity("Resource updated").build();
		} else {
			return Response.status(404).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@Context SecurityContext sc, @PathParam("id") int id) {
		boolean deleted=udao.delete(id);
		if(deleted) {
			return Response.ok().entity("Resource deleted").build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response list(@Context SecurityContext sc) {
		UporabnikList list=null;
		System.out.println("TU SEM");
		System.out.println(sc.isUserInRole("admin"));
		if(sc.isUserInRole("admin")) {
			System.out.println("ZAJ PA TU");
			list=new UporabnikList(udao.list());
			return Response.ok(list).build();
		} else if(sc.isUserInRole("uporabnik")) {
			Uporabnik u=udao.read(sc.getUserPrincipal().getName());
			List<Uporabnik> l=new ArrayList<Uporabnik>();
			l.add(u);
			list=new UporabnikList(l);
			return Response.ok(list).build();
		} else {
			return null;
		}
	}

}
