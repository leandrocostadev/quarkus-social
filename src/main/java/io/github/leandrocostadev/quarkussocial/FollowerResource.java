package io.github.leandrocostadev.quarkussocial;

import io.github.leandrocostadev.quarkussocial.domain.model.Follower;
import io.github.leandrocostadev.quarkussocial.domain.model.User;
import io.github.leandrocostadev.quarkussocial.domain.repository.FollowerRepository;
import io.github.leandrocostadev.quarkussocial.domain.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users/{userId}/followers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FollowerResource {

    private FollowerRepository followerRepository;
    private UserRepository userRepository;
    private final Validator validator;

    @Inject
    public FollowerResource(FollowerRepository followerRepository, UserRepository userRepository, Validator validator) {
        this.followerRepository = followerRepository;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @GET
    public Response getFollowers(@PathParam("userId") Long userId){
//        List<Follower> followers = followerRepository.list("followed", userRepository.findById(userId));
//        List<User> userFollowers = null;
//        for (int i = 0; i < followers.size(); i++) {
//            userFollowers.add(followers.get(i).getFollower());
//        }
//        return Response.ok(userFollowers).build();

        return Response.ok().build();
    }
}
