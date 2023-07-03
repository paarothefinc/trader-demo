package com.paaro.traderdemo.rest;

import com.paaro.traderdemo.dto.BetDto;
import com.paaro.traderdemo.dto.TaxationDto;
import com.paaro.traderdemo.exception.TraderNotFoundException;
import com.paaro.traderdemo.service.TaxationService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("taxation")
@Produces(MediaType.APPLICATION_JSON)
public class TaxationResource {

    @Inject
    TaxationService taxationService;

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTaxation(@Valid final BetDto betDto) {
        final TaxationDto taxation;

        try {
            taxation = taxationService.getTaxation(betDto);
        } catch (final TraderNotFoundException e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }

        return Response.ok(taxation).build();
    }
}
