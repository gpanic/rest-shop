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

import restshop.dao.UporabnikDAO;
import restshop.dao.UporabnikVlogaDAO;
import restshop.dao.VlogaDAO;
import restshop.entities.UporabnikVloga;
import restshop.entities.lists.UporabnikVlogaList;

@Path("uporabnik_vloge")
public class UporabnikVlogaResource extends Resource<UporabnikVloga> {
	
	UporabnikVlogaDAO uvdao=new UporabnikVlogaDAO();
	UporabnikDAO udao=new UporabnikDAO();
	VlogaDAO vdao=new VlogaDAO();

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response create(@Context SecurityContext sc, UporabnikVloga entity) {
		String uporabnik_up_ime=udao.read(entity.getUporabnik().getId_uporabnik()).getUp_ime();
		String vloga_naziv=vdao.read(entity.getVloga().getId_vloga()).getNaziv();
		entity.setUp_ime(uporabnik_up_ime);
		entity.setNaziv(vloga_naziv);
		uvdao.create(entity);
		UriBuilder ub=uriInfo.getBaseUriBuilder();
		URI uri=ub.path(UporabnikVlogaResource.class).path(Integer.toString(entity.getId_uporabnikvloga())).build();
		return Response.created(uri).entity(entity).build();
	}

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response read(@Context SecurityContext sc, @PathParam("id") int id) {
		UporabnikVloga entity=uvdao.read(id);
		if(entity!=null) {
			return Response.ok(entity).build();
		} else {
			return Response.status(404).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response update(@Context SecurityContext sc, @PathParam("id") int id, UporabnikVloga entity) {
		String uporabnik_up_ime=udao.read(entity.getUporabnik().getId_uporabnik()).getUp_ime();
		String vloga_naziv=vdao.read(entity.getVloga().getId_vloga()).getNaziv();
		if(uporabnik_up_ime!=null && vloga_naziv!=null) {
			entity.setUp_ime(uporabnik_up_ime);
			entity.setNaziv(vloga_naziv);
			entity.setId_uporabnikvloga(id);
			boolean updated=uvdao.update(entity);
			if(updated) {
				return Response.ok().entity("Resource updated").build();
			} else {
				return Response.status(404).build();
			}
		} else {
			return Response.ok().entity("User or Role does not exist").build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@Context SecurityContext sc, @PathParam("id") int id) {
		boolean deleted=uvdao.delete(id);
		if(deleted) {
			return Response.ok().entity("Resource deleted").build();
		} else {
			return Response.status(404).build();
		}
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response list(@Context SecurityContext sc) {
		UporabnikVlogaList list=new UporabnikVlogaList(uvdao.list());
		return Response.ok(list).build();
	}

}
