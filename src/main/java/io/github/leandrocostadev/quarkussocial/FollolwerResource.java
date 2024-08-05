package io.github.leandrocostadev.quarkussocial;

import io.github.leandrocostadev.quarkussocial.domain.dao.FollowerDao;
import io.github.leandrocostadev.quarkussocial.domain.dao.UserDao;
import io.github.leandrocostadev.quarkussocial.domain.model.User;
import io.github.leandrocostadev.quarkussocial.rest.dto.IsFollowingResponse;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users/{userId}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FollolwerResource {

    @Inject
    private FollowerDao followerDao;
    @Inject
    private UserDao userDao;

    @GET
    @Path("/followers")
    public Response getFollowers(@PathParam("userId") Long userId){
        List<User> followers = followerDao.getFollowers(userId);
        return Response.ok(followers).build();
    }

    @GET
    @Path("/following")
    public Response getFollowing(@PathParam("userId") Long userId){
        List<User> followingList = followerDao.getFollowing(userId);
        return Response.ok(followingList).build();
    }

    @POST
    @Path("/follow/{followedId}")
    @Transactional
    public Response follow(@PathParam("userId") Long userId, @PathParam("followedId") Long followedId){
        try {
            followerDao.follow(userId, followedId);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/unfollow/{followedId}")
    @Transactional
    public Response unfollow(@PathParam("userId") Long userId, @PathParam("followedId") Long followedId){
        try {
            followerDao.unfollow(userId, followedId);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("/isFollowing/{followedId}")
    public Response isFollowing(@PathParam("userId") Long userId, @PathParam("followedId") Long followedId){
        boolean isFollowing = followerDao.isFollowing(userId, followedId);
        IsFollowingResponse isFollowingResponse = new IsFollowingResponse(isFollowing);
        return Response.ok(isFollowingResponse).build();
    }

}
