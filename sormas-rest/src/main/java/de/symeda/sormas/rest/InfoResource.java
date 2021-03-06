package de.symeda.sormas.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.symeda.sormas.api.utils.InfoProvider;


@Path("/info")
@Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
@RolesAllowed("USER")
public class InfoResource {

	@GET
	@Path("/version")
	public String getVersion() {	
		return InfoProvider.getVersion();
	}
}
