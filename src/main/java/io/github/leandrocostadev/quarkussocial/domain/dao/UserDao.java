package io.github.leandrocostadev.quarkussocial.domain.dao;

import io.github.leandrocostadev.quarkussocial.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@ApplicationScoped
public class UserDao {
    @PersistenceContext
    private EntityManager em;

    public List<User> listAllUsers(){
        Query query = em.createNamedQuery("LIST_ALL_USERS");
        return query.getResultList();
    }

    public List<User> findUserByName(String name){
        Query query = em.createNamedQuery("FIND_BY_NAME");
        query.setParameter("name", name);
        return query.getResultList();
    }

    public User findUserById(Long userId){
        Query query = em.createNamedQuery("FIND_USER_BY_ID");
        query.setParameter("id", userId);
        System.out.println(query);
        return (User) query.getSingleResult();
    }

    public void addUser(User user){
        Query query = em.createNamedQuery("ADD_USER");
        query.setParameter("name", user.getName());
        query.setParameter("age", user.getAge());
        query.executeUpdate();
    }

    public void updateUser(User user){
        Query query = em.createNamedQuery("UPDATE_USER");
        query.setParameter("name", user.getName());
        query.setParameter("age", user.getAge());
        query.executeUpdate();
    }

    public void deleteUserById(Long id){
        Query query = em.createNamedQuery("DELETE_USER_BY_ID");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
