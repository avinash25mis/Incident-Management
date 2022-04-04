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




    public String getLoggedInUser() {
        String userName = null;
        if(SecurityContextHolder.getContext()!=null && SecurityContextHolder.getContext().getAuthentication()!=null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                userName = authentication.getName();

            }
        }
        if(userName==null){
            throw new AppExceptions("Not logged In","Logged in user cannot be null");
        }
        return userName;
    }

}
