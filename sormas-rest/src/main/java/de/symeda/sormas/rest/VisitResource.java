package de.symeda.sormas.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.visit.VisitDto;
import de.symeda.sormas.api.visit.VisitFacade;

/**
 * @see <a href="https://jersey.java.net/documentation/latest/">Jersey documentation</a>
 * @see <a href="https://jersey.java.net/documentation/latest/jaxrs-resources.html#d0e2051">Jersey documentation HTTP Methods</a>
 *
 */
@Path("/visits")
@Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
@Consumes({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
public class VisitResource {

	@GET
	@Path("/all/{user}/{since}")
	public List<VisitDto> getAllVisits(@PathParam("user") String userUuid, @PathParam("since") long since) {
		
		List<VisitDto> result = FacadeProvider.getVisitFacade().getAllVisitsAfter(new Date(since), userUuid);
		return result;
	}
	
	@POST 
	@Path("/push")
	public Long postVisits(List<VisitDto> dtos) {
		
		VisitFacade visitFacade = FacadeProvider.getVisitFacade();
		for (VisitDto dto : dtos) {
			visitFacade.saveVisit(dto);
		}
		
		return new Date().getTime();
	}
}