package io.github.leandrocostadev.quarkussocial;

import java.util.List;
import java.util.Set;

import io.github.leandrocostadev.quarkussocial.domain.model.User;
import io.github.leandrocostadev.quarkussocial.domain.repository.UserRepository;
import io.github.leandrocostadev.quarkussocial.rest.dto.CreateUserRequest;
import io.github.leandrocostadev.quarkussocial.rest.dto.ResponseError;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserRepository repository;
    private final Validator validator;

    @Inject
    public UserResource(UserRepository repository, Validator validator){
        this.repository = repository;
        this.validator = validator;
    }

    @POST
    @Transactional
    public Response createUser( CreateUserRequest userRequest){
        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(userRequest);
        if (!violations.isEmpty()) {
            ResponseError responseError = ResponseError.createFromValidation(violations);
            return Response.status(Response.Status.BAD_REQUEST).entity(responseError).build();
        }
        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());
        repository.persist(user);

        return Response.status(Response.Status.CREATED).entity(user).build();

    }

    @GET
    public Response listAllUsers() {
        List<User> users = repository.listAll();
        return Response.ok(users).build();

    }

    @GET
    @Path("/find")
    public Response listUsersByName(@QueryParam("name") String name) {
        List<User> users = repository.find("name", name).list();
        return Response.ok(users).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateUserById(@PathParam("id") Long id, CreateUserRequest userRequest) {
        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(userRequest);
        if (!violations.isEmpty()) {
            ResponseError responseError = ResponseError.createFromValidation(violations);
            return Response.status(Response.Status.BAD_REQUEST).entity(responseError).build();
        }
        User user = repository.findById(id);
        if (user != null) {
            user.setAge(userRequest.getAge());
            user.setName(userRequest.getName());
            repository.persist(user);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response removeUserById(@PathParam("id") Long id) {
        try {
            boolean deleted = repository.deleteById(id);
            if (deleted) {
                return Response.ok().build(); // User successfully deleted
            } else {
                return Response.status(Response.Status.NOT_FOUND).build(); // User not found
            }
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).build(); // Invalid parameter format
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build(); // Server error
        }
    }
  
}
