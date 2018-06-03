package com.interfell.fullstacktest.resources;

import com.interfell.fullstacktest.domain.User;
import com.interfell.fullstacktest.resources.dtos.UserResume;
import com.interfell.fullstacktest.resources.dtos.UserResumeListResponse;
import com.interfell.fullstacktest.services.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Path("/users")
@Produces("application/json")
@Api(value = "Users resource", produces = "application/json")
public class UsersResources {

    @Autowired
    private UsersService usersService;

    @GET
    @Path("")
    @ApiOperation(value = "Gets a list of users. Version 1 - (version in URL)", response = UserResumeListResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of users found")
    })
    public Response getAllUsers(){
        List<User> users = usersService.findAllUsers();
        return Response.ok(
                new UserResumeListResponse(
                        users.stream()
                                .map(user -> new UserResume(
                                        user.getId(),
                                        user.getUsername(),
                                        user.getFirstname(),
                                        user.getLastname()))
                                .collect(Collectors.toList()), new Date()))
                .build();
    }
}
