package com.marlon.desafiopro.resources;

import javax.json.JsonArray;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.marlon.desafiopro.spot.SpotAnalyzer;
import com.marlon.desafiopro.spot.SpotResponse;
import com.marlon.desafiopro.validation.ValidSpot;

@Path("spot_check")
public class CheckSpot {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response check(@ValidSpot JsonArray toCheck) {
		
		SpotResponse entity = SpotAnalyzer.create(toCheck).transformList().run();
		
		return Response.ok(entity).build();
	}
}
