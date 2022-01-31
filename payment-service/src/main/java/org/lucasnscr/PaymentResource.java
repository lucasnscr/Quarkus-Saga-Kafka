package org.lucasnscr;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lucasnscr.service.PaymentService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/payments")
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class PaymentResource {

    private final PaymentService paymentService;

    @GET
    public Response getPayments() {

        log.info("Find All payments");

        return Response.status(Response.Status.OK)
                .entity(paymentService.findAll()).build();
    }

}