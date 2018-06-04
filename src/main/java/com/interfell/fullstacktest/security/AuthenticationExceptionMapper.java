package com.interfell.fullstacktest.security;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {

    public Response toResponse(AuthenticationException e) {
        if (e.getRealm() != null) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .header("WWW-Authenticate", "Basic realm=\"" + e.getRealm() + "\"")
                    .type("text/plain")
                    .entity(e.getMessage())
                    .build();
        } else {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .type("text/plain")
                    .entity(e.getMessage())
                    .build();
        }
    }
}
