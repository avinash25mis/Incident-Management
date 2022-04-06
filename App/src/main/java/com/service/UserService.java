package com.service;


import com.ExceptionHandling.AppExceptions;
import com.model.IncidentReport;
import com.repository.BaseRepository;
import com.model.User;
import com.repository.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService{

    @Autowired
    private BaseRepository repository;
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        User savedUser = repository.saveOrUpdate(user);
        return savedUser;
    }



    public void updateUser(User newUser) {
        User oldUser = validateAndGetUser(newUser.getUsername());
        compareAndUpdate(newUser,oldUser);
        repository.saveOrUpdate(oldUser);
    }

    public void deleteUser(Long id) {
        User user = validateAndFindUserById(id);
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

    }


    public User validateAndGetUser(String username) {
        User user = userRepository.getUserByUsername(username);
        if(user==null){
           throw new AppExceptions("User Not found","with username :-"+username);
        }
        return user;
    }

    public boolean compareWithLoggedInUser(String username) {
        if(StringUtils.isNotEmpty(username) && getLoggedInUser().equals(username)) {
           return true;
        }else{
            return false;
        }
    }


    public User validateAndFindUserById(Long id) {
        User user=null;
        if(id!=null) {
            user = repository.findById(User.class, id);
        }
        if (user==null) {
            throw new AppExceptions("User Not found "+id);
        }
        return user;
    }




}
