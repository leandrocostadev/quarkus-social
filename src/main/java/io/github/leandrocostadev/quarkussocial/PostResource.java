package io.github.leandrocostadev.quarkussocial;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.leandrocostadev.quarkussocial.domain.dao.PostDao;
import io.github.leandrocostadev.quarkussocial.domain.dao.UserDao;
import io.github.leandrocostadev.quarkussocial.domain.model.Post;
import io.github.leandrocostadev.quarkussocial.rest.dto.CreatePostRequest;
import io.github.leandrocostadev.quarkussocial.rest.dto.ResponseError;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

    @Inject
    private PostDao postDao;
    @Inject
    private UserDao userDao;
    private final Validator validator;

    @Inject
    public PostResource(Validator validator) {
        this.validator = validator;
    }

    @GET
    public Response getPostsByUserId(@PathParam("userId") Long userId) throws JsonProcessingException {
        List<Post> posts = postDao.listPostsByUserId(userId);
        return Response.ok(posts).build();
    }

    @GET
    @Path("/{postId}")
    public Response getPost(@PathParam("postId") Long postId){
        Post post = postDao.getPost(postId);
        return Response.ok(post).build();
    }

    @POST
    @Transactional
    public Response createPost(@PathParam("userId") Long userId, CreatePostRequest postRequest) {
        Set<ConstraintViolation<CreatePostRequest>> violations = validator.validate(postRequest);
        if (!violations.isEmpty()) {
            ResponseError responseError = ResponseError.createFromValidation(violations);
            return Response.status(Response.Status.BAD_REQUEST).entity(responseError).build();
        }

        Post post = new Post();
        post.setText(postRequest.getText());
        post.setUser(userDao.findUserById(userId));
        postDao.addPost(post);
        return Response.status(Response.Status.CREATED).entity(post).build();
    }

    @PUT
    @Path("/{postId}")
    @Transactional
    public Response updatePost(@PathParam("postId") Long postId, CreatePostRequest postRequest) {
        Post post = postDao.getPost(postId);
        post.setText(postRequest.getText());
        return Response.ok(post).build();
    }

    @DELETE
    @Path("/{postId}")
    @Transactional
    public Response deletePost(@PathParam("postId") Long postId){
        try {
            postDao.deletePost(postId);
            return Response.ok().build();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
