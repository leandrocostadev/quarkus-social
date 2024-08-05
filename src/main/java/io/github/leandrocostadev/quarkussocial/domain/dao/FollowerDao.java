package io.github.leandrocostadev.quarkussocial.domain.dao;

import io.github.leandrocostadev.quarkussocial.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@ApplicationScoped
public class FollowerDao {

    @PersistenceContext
    private EntityManager em;

    public List<User> getFollowers(Long id){
        Query query = em.createNamedQuery("GET_FOLLOWERS");
        query.setParameter("id", id);
        List<User> users = query.getResultList();
        return users;
    }

    public List<User> getFollowing(Long id){
        Query query = em.createNamedQuery("GET_FOLLOWING");
        query.setParameter("id", id);
        List<User> users = query.getResultList();
        return users;
    }

    public boolean isFollowing(Long followerId, Long followedId){
        Query query = em.createNamedQuery("IS_FOLLOWING");
        query.setParameter("followerId", followerId);
        query.setParameter("followedId", followedId);
        Long result = (Long) query.getSingleResult();
        if (result == 0){
            return false;
        }
        return true;
    }

    public void follow(Long followerId, Long followedId){
        Query query = em.createNamedQuery("FOLLOW");
        query.setParameter("followerId", followerId);
        query.setParameter("followedId", followedId);
        query.executeUpdate();
    }

    public void unfollow(Long followerId, Long followedId){
        Query query = em.createNamedQuery("UNFOLLOW");
        query.setParameter("followerId", followerId);
        query.setParameter("followedId", followedId);
        query.executeUpdate();
    }
}
