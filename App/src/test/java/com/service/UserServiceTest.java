package com.service;

import com.ExceptionHandling.AppExceptions;
import com.model.User;
import com.repository.BaseRepository;
import com.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    public static final String name = "john";
    @InjectMocks
    protected UserService userService;
    @Mock
    protected BaseRepository repository;
    @Mock
    protected UserRepository userRepository;

    @Test
    @DisplayName("Save user")
    void saveUser() {
        when(repository.saveOrUpdate(getDummyUser(name, name))).thenReturn(getDummyUser(name, name));
        User user = userService.saveUser(getDummyUser(name, name));
        assertEquals(1l,user.getId());
    }


    @Test
    @DisplayName("Save user without username")
    void saveUserWithNoUserName() {
        User john = getDummyUser(name, null);
        when(repository.saveOrUpdate(john)).thenThrow(TransactionSystemException.class);
        assertThrows(TransactionSystemException.class,()->userService.saveUser(getDummyUser(name,null)));
    }

    @Test
    @DisplayName("update user")
    void updateUser() {
        User oldUser = getDummyUser(name, name);
        User newUser = getDummyUser(name, name);
        when(userRepository.getUserByUsername(name)).thenReturn(oldUser);
         oldUser = userService.validateAndGetUser(name);
         when(repository.saveOrUpdate(oldUser)).thenReturn(oldUser);
         userService.updateUser(newUser);

    }

    @Test
    @DisplayName("delete user")
    void deleteUser() {
        User user = getDummyUser(name, name);
        when( repository.findById(User.class,1l)).thenReturn(user);
        userService.deleteUser(1l);
    }

    @Test
    @DisplayName("delete user which is not found")
    void deleteUserNotFound() {
        User user = getDummyUser(name, name);
        when( repository.findById(User.class,2l)).thenReturn(user);
        AppExceptions appExceptions = assertThrows(AppExceptions.class, () -> userService.deleteUser(1l));
        String expectedMessage="User Not found";
        String actualMessage=appExceptions.getErrorMsg();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("find all user")
    void findALlUser() {
        List<User> expectedList=new ArrayList<>();
        expectedList.add(getDummyUser(name, name));
        when(repository.findAll(User.class.getName())).thenReturn(expectedList);
        List<User> actualList=userService.findALlUser();
        assertEquals(expectedList.size(),actualList.size());
    }

    @Test
    void validateAndGetUser() {
        User dummyUser = getDummyUser(name, name);
        when(userRepository.getUserByUsername(name)).thenReturn(dummyUser);
        User user = userService.validateAndGetUser(name);
        System.out.println();
    }




    private User getDummyUser(String name,String username){
        User user= new User();
        user.setId(1l);
        user.setFirstName(name);
        user.setUsername(username);
        user.setLastName(name);
        return user;
    }


}