package com.interfell.fullstacktest.resources;

import com.interfell.fullstacktest.resources.dtos.LoginRequest;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Component
@Path("/auth")
@Produces("application/json")
public class AuthResources {

    public Response login(
            LoginRequest loginRequest){

    }

}
