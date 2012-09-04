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

import restshop.dao.NarociloDAO;
import restshop.dao.StanjeDAO;
import restshop.dao.UporabnikDAO;
import restshop.entities.Narocilo;
import restshop.entities.Uporabnik;
import restshop.entities.lists.NarociloList;

@Path("/narocila")
public class NarociloResource extends Resource<Narocilo> {
	
	NarociloDAO ndao=new NarociloDAO();
	UporabnikDAO udao=new UporabnikDAO();
	StanjeDAO sdao=new StanjeDAO();

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response create(@Context SecurityContext sc, Narocilo entity) {
		Uporabnik u=udao.read(sc.getUserPrincipal().getName());
		entity.setUporabnik(u);
		entity.setStanje(sdao.read("kosarica"));
		ndao.create(entity);
		UriBuilder ub=uriInfo.getBaseUriBuilder();
		URI uri=ub.path(NarociloResource.class).path(Integer.toString(entity.getId_narocilo())).build();
		return Response.created(uri).entity(entity).build();
	}

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response read(@Context SecurityContext sc, @PathParam("id") int id) {
		Narocilo entity=ndao.read(id);
		if(entity!=null) {
			if(sc.isUserInRole("admin")) {
				return Response.ok(entity).build();
			} else if(sc.isUserInRole("uporabnik")) {
				if(entity.getUporabnik().getUp_ime().equals(sc.getUserPrincipal().getName())) {
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
	public Response update(@Context SecurityContext sc, @PathParam("id") int id, Narocilo entity) {
		entity.setId_narocilo(id);
		Narocilo old=ndao.read(id);
		boolean updated=false;
		if(sc.isUserInRole("admin")) {
			updated=ndao.update(entity);
		} else if(sc.isUserInRole("uporabnik")) {
			if(sc.getUserPrincipal().getName().equals(old.getUporabnik().getUp_ime())) {
				if(old.getStanje().getId_stanje()==1 && entity.getStanje().getId_stanje()==2) {
					updated=ndao.update(entity);
				} else {
					return Response.status(400).entity("State invalid or missing").build();
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
		boolean deleted=false;
		if(sc.isUserInRole("admin")) {
			deleted=ndao.delete(id);
		} else if(sc.isUserInRole("uporabnik")) {
			Narocilo n=ndao.read(id);
			if(sc.getUserPrincipal().getName().equals(n.getUporabnik().getUp_ime())) {
				if(n.getStanje().getId_stanje()==1) {
					deleted=ndao.delete(id);
				} else {
					return Response.status(400).entity("Cannot delete this resource").build();
				}
			}
		}
		if(deleted) {
			return Response.ok().entity("Resource deleted").build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response list(@Context SecurityContext sc) {
		NarociloList list=null;
		if(sc.isUserInRole("admin")) {
			list=new NarociloList(ndao.list());
			return Response.ok(list).build();
		} else if(sc.isUserInRole("uporabnik")) {
			Uporabnik u=udao.read(sc.getUserPrincipal().getName());
			list=new NarociloList(ndao.list(u.getId_uporabnik()));
			return Response.ok(list).build();
		} else {
			return null;
		}
	}

}
