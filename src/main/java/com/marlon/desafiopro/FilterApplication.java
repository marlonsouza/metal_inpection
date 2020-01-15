package com.marlon.desafiopro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@Provider
public class FilterApplication implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        
        headers.putSingle("Access-Control-Allow-Origin", "*");
        headers.putSingle("Access-Control-Allow-Headers", "Authorization, Origin, X-Requested-With, Content-Type, Accept");
        headers.putSingle("Access-Control-Expose-Headers", "Location, Content-Disposition");
        headers.putSingle("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE, HEAD, OPTIONS");
        
        List<String> reqHead=requestContext.getHeaders()
                     .get("Access-Control-Request-Headers");
        if(null != reqHead){
             responseContext.getHeaders()
                .put("Access-Control-Allow-Headers", 
                     new ArrayList<>(reqHead));
        }

	}

}
