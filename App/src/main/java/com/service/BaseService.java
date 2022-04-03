package com.service;


import com.ExceptionHandling.AppExceptions;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BaseService {

     void validateId(Long id) {
        if(id==null){
            throw new AppExceptions("id null", "Id cannot be null");
        }

    }


     void validateObject(Object o, String objName, Long id) {
        if (o==null) {
            throw new AppExceptions(objName+" not found", id.toString());
        }
    }


    public String getLoggedInUser() {
        String userName = null;
        if(SecurityContextHolder.getContext()!=null && SecurityContextHolder.getContext().getAuthentication()!=null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                userName = authentication.getName();

            }
        }
        return userName;
    }

}
