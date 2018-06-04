package com.interfell.fullstacktest.resources;

import com.interfell.fullstacktest.resources.dtos.LoginRequest;
import com.interfell.fullstacktest.resources.dtos.LoginResponse;
import com.interfell.fullstacktest.services.AuthenticationService;
import com.interfell.fullstacktest.services.dtos.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Component
@Path("/auth")
@Produces("application/json")
@Consumes("application/json")
public class AuthResources {

  @Autowired
  private AuthenticationService service;

  @POST
  @Path("/login")
  public Response login(
    LoginRequest loginRequest){

    Authentication body = service.login(loginRequest.getUsername(),loginRequest.getPassword());

    return Response.ok(body).build();
  }

}
