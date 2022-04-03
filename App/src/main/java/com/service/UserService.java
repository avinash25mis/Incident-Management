package com.service;


import com.dao.BaseRepository;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService{

    @Autowired
    protected BaseRepository repository;

    public User saveUpdateUser(User user) {
        User savedUser = repository.saveOrUpdate(user);
        return savedUser;
    }

    public User findUserByUserId(Long id) {
        validateId(id);
        User user = repository.findById(User.class, id);
        validateObject(user, "user",id);
        return user;
    }

    public void updateUser(User newUser) {
        validateId(newUser.getId());
        User oldUser = repository.findById(User.class, newUser.getId());
        validateObject(oldUser, "incidentReport",newUser.getId());
        compareAndUpdate(newUser,oldUser);
    }

    public void deleteUser(Long id) {
        validateId(id);
        User user = repository.findById(User.class, id);
        validateObject(user, User.class.getName(),id);
        repository.delete(user);

    }



    public List<User> findALlUser() {
       List<User> all = repository.findAll(User.class.getName());
       return all;
    }


    private void compareAndUpdate(User newUSer, User oldUser) {

        if(newUSer.getUsername()!=null){
            oldUser.setUsername(newUSer.getUsername());

        }
        if(newUSer.getFirstName()!=null){
            oldUser.setFirstName(newUSer.getFirstName());
        }
        if(newUSer.getLastName()!=null){
            oldUser.setLastName(newUSer.getLastName());
        }
        repository.saveOrUpdate(oldUser);
    }




}
