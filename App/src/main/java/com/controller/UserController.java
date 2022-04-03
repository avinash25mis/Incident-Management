package com.controller;

import com.constants.StatusConstants;
import com.dto.request.UserVO;
import com.dto.response.GenericResponse;
import com.model.User;
import com.service.UserService;
import com.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * @author avinash.a.mishra
 */

@RestController
@RequestMapping("/User")
public class UserController {

   @Autowired
   private UserService userService;

   @Autowired
   private UserMapper mapper;

  @PostMapping
  public ResponseEntity<GenericResponse> registerUser(@Valid @RequestBody UserVO userVO, Principal principal) {
      User user = mapper.toEntity(userVO);
      User savedUser=userService.saveUpdateUser(user);
      return ResponseEntity.ok(new GenericResponse(StatusConstants.ok,"user saved",savedUser.getId()));
  }



    @PutMapping
    public ResponseEntity<GenericResponse> updateUser(@RequestBody UserVO userVO){
        User user = mapper.toEntity(userVO);
        userService.updateUser(user);
        return ResponseEntity.ok(new GenericResponse(StatusConstants.ok,"user updated",null));
  }

    @DeleteMapping("/{userId}")
    public ResponseEntity<GenericResponse> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
         return ResponseEntity.ok(new GenericResponse(StatusConstants.ok,"user deleted",null));
  }

  @GetMapping("/{userId}")
  public ResponseEntity<GenericResponse> getTheUser(@PathVariable Long userId){
      User userByUserId = userService.findUserByUserId(userId);
      UserVO userVO = mapper.toDto(userByUserId);
      return ResponseEntity.ok(new GenericResponse(StatusConstants.ok,"user",userVO));
  }


    @GetMapping
    public ResponseEntity<GenericResponse> getAllUser(){
        List<User> userList = userService.findALlUser();
        List<UserVO> userVOS = mapper.toDtoList(userList);
        return ResponseEntity.ok(new GenericResponse(StatusConstants.ok,"userList",userVOS));
    }


}
