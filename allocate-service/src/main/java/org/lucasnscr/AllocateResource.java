package org.lucasnscr;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lucasnscr.service.SeatService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/allocates")
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class AllocateResource {

    private final SeatService seatService;

    @GET
    @Path("/{id}")
    public Response allocateSeat(@PathParam("id") Long id) {

        log.info("Seat status by id");

        return Response.status(Response.Status.OK)
                .entity(seatService.findById(id)).build();
    }


}
