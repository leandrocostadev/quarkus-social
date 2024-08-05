package io.github.leandrocostadev.quarkussocial.domain.dao;

import io.github.leandrocostadev.quarkussocial.domain.model.Post;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@ApplicationScoped
public class PostDao {

    @PersistenceContext
    private EntityManager em;

    public List<Post> listPostsByUserId(Long userId){
        Query query = em.createNamedQuery("LIST_POSTS_BY_USER_ID");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public Post getPost(Long postId){
        Query query = em.createNamedQuery("GET_POST");
        query.setParameter("postId", postId);
        return (Post) query.getSingleResult();
    }

    public void addPost(Post post){
        Query query = em.createNamedQuery("ADD_POST");
        query.setParameter("postText", post.getText());
        query.setParameter("userId", post.getUser().getId());
        query.executeUpdate();
    }

    public void updatePost(Post post){
        Query query = em.createNamedQuery("UPDATE_POST");
        query.setParameter("postText", post.getText());
        query.setParameter("postId", post.getId());
        query.executeUpdate();
    }

    public void deletePost(Long postId){
        Query query = em.createNamedQuery("DELETE_POST");
        query.setParameter("postId", postId);
        query.executeUpdate();
    }
}
