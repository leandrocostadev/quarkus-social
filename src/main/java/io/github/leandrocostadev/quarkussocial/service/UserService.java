package io.github.leandrocostadev.quarkussocial.service;

import io.github.leandrocostadev.quarkussocial.domain.dao.UserDao;
import io.github.leandrocostadev.quarkussocial.domain.model.User;
import io.github.leandrocostadev.quarkussocial.rest.dto.CreateUserRequest;
import jakarta.inject.Inject;

import java.util.List;

public class UserService {
    @Inject
    private UserDao userDao;

    public void createUserService(CreateUserRequest createUserRequest){
        User user = new User();
        user.setAge(createUserRequest.getAge());
        user.setName(createUserRequest.getName());
        userDao.addUser(user);
    }

    public User getUserService(Long userId){
        User user = userDao.findUserById(userId);
        return user;
    }

    public List<User> listAllUsersService(){
        List<User> users = userDao.listAllUsers();
        return users;
    }

    public List<User> findUserByNameService(String userName){
        List<User> users = userDao.findUserByName(userName);
        return users;
    }

    public void updateUserByIdService(Long userId, CreateUserRequest createUserRequest){
        User user = userDao.findUserById(userId);
        user.setAge(createUserRequest.getAge());
        user.setName(createUserRequest.getName());
        userDao.updateUser(user);
    }

    public void deleteUserService(Long userId){
        userDao.deleteUserById(userId);
    }

}
