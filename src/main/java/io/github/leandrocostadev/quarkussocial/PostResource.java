package io.github.leandrocostadev.quarkussocial;

import java.util.List;
import java.util.Set;

import io.github.leandrocostadev.quarkussocial.domain.model.Post;
import io.github.leandrocostadev.quarkussocial.domain.repository.PostRepository;
import io.github.leandrocostadev.quarkussocial.domain.repository.UserRepository;
import io.github.leandrocostadev.quarkussocial.rest.dto.CreatePostRequest;
import io.github.leandrocostadev.quarkussocial.rest.dto.ResponseError;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private final Validator validator;

    @Inject
    public PostResource(PostRepository postRepository, UserRepository userRepository, Validator validator) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.validator = validator;
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
        post.setUser(userRepository.findById(userId));
        postRepository.persist(post);
        return Response.status(Response.Status.CREATED).entity(post).build();
    }
    
    @GET
    public Response getPostsByUserId(@PathParam("userId") Long userId){
//        List<Post> posts = postRepository.list("user", userRepository.findById(userId));
        Set<Post> posts = userRepository.findById(userId).getPosts();
        return Response.ok(posts).build();
    }
}
