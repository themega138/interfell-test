package com.interfell.fullstacktest.security;

import com.interfell.fullstacktest.domain.User;
import com.interfell.fullstacktest.exceptions.EntityNotFoundException;
import com.interfell.fullstacktest.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.Principal;

@Component
@Provider
@PreMatching
public class AuthenticationFilter implements ContainerRequestFilter{

    @Inject
    javax.inject.Provider<UriInfo> uriInfo;
    private static final String REALM = "HTTPS Example authentication";

    @Autowired
    private UsersService usersService;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        User user = authenticate(containerRequestContext);
        containerRequestContext.setSecurityContext(new Authorizer(user));
    }

    private User authenticate(ContainerRequestContext filterContext) {
        // Extract authentication credentials
        String authentication = filterContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authentication == null) {
            throw new AuthenticationException("Authentication credentials are required", REALM);
        }
        if (!authentication.startsWith("Basic ")) {
            return null;
            // additional checks should be done here
            // "Only HTTP Basic authentication is supported"
        }
        authentication = authentication.substring("Basic ".length());
        String[] values = new String(DatatypeConverter.parseBase64Binary(authentication), Charset.forName("ASCII")).split(":");
        if (values.length < 2) {
            throw new WebApplicationException(400);
            // "Invalid syntax for username and password"
        }
        String username = values[0];
        String password = values[1];
        if ((username == null) || (password == null)) {
            throw new WebApplicationException(400);
            // "Missing username or password"
        }

        // Validate the extracted credentials

        try {
            User user = usersService.loadByUsername(username);
            if(!user.getPassword().equals(password)) throw new AuthenticationException("Invalid username or password\r\n", REALM);

            return user;

        } catch (EntityNotFoundException e) {
            throw new AuthenticationException("Invalid username or password\r\n", REALM);
        }
    }

    public class Authorizer implements SecurityContext {

        private User user;
        private Principal principal;

        public Authorizer(final User user) {
            this.user = user;
            this.principal = () -> user.getUsername();
        }

        public Principal getUserPrincipal() {
            return this.principal;
        }

        public boolean isUserInRole(String role) {
            return (role.equals(user.getRights().contains(role)));
        }

        public boolean isSecure() {
            return "https".equals(uriInfo.get().getRequestUri().getScheme());
        }

        public String getAuthenticationScheme() {
            return SecurityContext.BASIC_AUTH;
        }
    }
}
