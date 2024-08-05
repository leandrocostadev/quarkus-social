package io.github.leandrocostadev.quarkussocial;

import java.util.List;

import io.github.leandrocostadev.quarkussocial.domain.model.User;
import io.github.leandrocostadev.quarkussocial.rest.dto.CreateUserRequest;
import io.github.leandrocostadev.quarkussocial.service.UserService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    public Response createUser( CreateUserRequest userRequest){
        userService.createUserService(userRequest);
        return Response.status(Response.Status.CREATED).build();

    }

    @GET
    @Path("/{userId}")
    public Response getUser(@PathParam("userId") Long userId){
        User user = userService.getUserService(userId);
        return Response.ok(user).build();
    }

    @GET
    public Response listAllUsers() {
        List<User> users = userService.listAllUsersService();
        return Response.ok(users).build();
    }

    @GET
    @Path("/find")
    public Response findUserByName(@QueryParam("name") String name) {
        List<User> users = userService.findUserByNameService(name);
        return Response.ok(users).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateUserById(@PathParam("id") Long id, CreateUserRequest userRequest) {
        userService.updateUserByIdService(id, userRequest);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response removeUserById(@PathParam("id") Long id) {
        userService.deleteUserService(id);
        return Response.ok().build();
    }

}
