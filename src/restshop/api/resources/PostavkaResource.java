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
import restshop.dao.PostavkaDAO;
import restshop.dao.UporabnikDAO;
import restshop.entities.Narocilo;
import restshop.entities.Postavka;
import restshop.entities.Uporabnik;
import restshop.entities.lists.PostavkaList;

@Path("/postavke")
public class PostavkaResource extends Resource<Postavka> {
	
	PostavkaDAO pdao=new PostavkaDAO();
	UporabnikDAO udao=new UporabnikDAO();
	NarociloDAO ndao=new NarociloDAO();

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response create(@Context SecurityContext sc, Postavka entity) {
		if(entity.getNarocilo()!=null && entity.getNarocilo()!=null) {
			Uporabnik u=udao.read(sc.getUserPrincipal().getName());
			Narocilo n=ndao.read(entity.getNarocilo().getId_narocilo(), u.getId_uporabnik());
			if(n!=null && n.getStanje().getId_stanje()==1) {
				entity.setNarocilo(n);
				pdao.create(entity);
				UriBuilder ub=uriInfo.getBaseUriBuilder();
				URI uri=ub.path(PostavkaResource.class).path(Integer.toString(entity.getId_postavka())).build();
				return Response.created(uri).entity(entity).build();
			}
		}
		return Response.status(400).entity("Cannot create this resource").build();
	}

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response read(@Context SecurityContext sc, @PathParam("id") int id) {
		Postavka entity=pdao.read(id);
		if(entity!=null) {
			return Response.ok(entity).build();
		} else {
			return Response.status(404).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response update(@Context SecurityContext sc, @PathParam("id") int id, Postavka entity) {
		entity.setId_postavka(id);
		Postavka old=pdao.read(id);
		Narocilo n=old.getNarocilo();
		boolean updated=false;
		if(sc.isUserInRole("admin")) {
			updated=pdao.update(entity);
		} else if(sc.isUserInRole("uporabnik")) {
			if(sc.getUserPrincipal().getName().equals(n.getUporabnik().getUp_ime())) {
				if(n.getStanje().getId_stanje()==1 && entity.getArtikel()!=null) {
					updated=pdao.update(entity);
				} else {
					return Response.ok().entity("Cannot update this resource").build();
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
			deleted=pdao.delete(id);
		} else if(sc.isUserInRole("uporabnik")) {
			Postavka p=pdao.read(id);
			Narocilo n=p.getNarocilo();
			if(sc.getUserPrincipal().getName().equals(n.getUporabnik().getUp_ime())) {
				if(n.getStanje().getId_stanje()==1) {
					deleted=pdao.delete(id);
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
		PostavkaList list=null;
		if(sc.isUserInRole("admin")) {
			list=new PostavkaList(pdao.list());
			return Response.ok(list).build();
		} else if(sc.isUserInRole("uporabnik")) {
			Uporabnik u=udao.read(sc.getUserPrincipal().getName());
			list=new PostavkaList(pdao.list(u.getId_uporabnik()));
			return Response.ok(list).build();
		} else {
			return null;
		}
	}

}
