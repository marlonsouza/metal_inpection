package com.marlon.desafiopro;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import com.marlon.desafiopro.spot.SpotResponse;

public class SpotIT {

	private Client client;
	private WebTarget target;

	@Before
	public void initClient() {
		
		  this.client = ClientBuilder.newClient(); 
		  this.target = this.client.target("http://localhost:8080/desafio/spot_check");
		  
	}
	
	private Response doRequestPost(String jsonFile) {
		InputStream jsonStream = SpotIT.class.getResourceAsStream(jsonFile);
		
		Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.post(Entity.json(jsonStream));
		
		return response;
	}
	
	@Test
	public void methodInvalid() {
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		
		assertThat(response.getStatus(), is(Status.METHOD_NOT_ALLOWED.getStatusCode()));
	}
	
	@Test
	public void invalidStructure() {
		Response response = doRequestPost("/json/invalid_spot_structure.json");
		
		assertThat(response.getStatus(), is(Status.BAD_REQUEST.getStatusCode()));
	}

	
	@Test
	public void zeroSpot() {
					
		Response response = doRequestPost("/json/zero_spot.json");
		
		assertThat(response.getStatus(), is(Status.OK.getStatusCode()));
			
		SpotResponse entity = response.readEntity(SpotResponse.class);
		
		assertNotNull(entity);
		assertThat(entity.getTotalArea(), is(0));
		assertThat(entity.getNumberSpots(), is(0));
		assertThat(entity.getAverageArea(), is(BigDecimal.ZERO));
		assertThat(entity.getBiggestArea(), is(BigDecimal.ZERO));
	}

	
	@Test 
	public void invalidSpot() throws IOException { 
		  
		Response response = doRequestPost("/json/invalid_spot.json");
		  
		assertThat(response.getStatus(), is(Status.BAD_REQUEST.getStatusCode())); 
	}
	
	@Test
	public void matrixValid() {
		
		Response response = doRequestPost("/json/matriz_valid.json");
		
		SpotResponse entity = response.readEntity(SpotResponse.class);
		
		assertNotNull(entity);
		assertThat(entity.getTotalArea(), is(8));
		assertThat(entity.getNumberSpots(), is(2));
		
		response = doRequestPost("/json/matriz_valid2.json");
		
		entity = response.readEntity(SpotResponse.class);
		
		assertNotNull(entity);
		assertThat(entity.getTotalArea(), is(5));
		assertThat(entity.getNumberSpots(), is(3));
		
	}
	 
	
}
