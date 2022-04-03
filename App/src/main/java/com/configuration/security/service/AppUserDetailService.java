package com.configuration.security.service;

import com.configuration.security.dto.SecurityUserVO;
import com.dao.BaseRepository;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author avinash.a.mishra
 */

@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    private BaseRepository baseRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = baseRepository.getUserByUserName(s);
        if(user==null){
            throw new UsernameNotFoundException("Not found :"+s);
        }
        SecurityUserVO u= new SecurityUserVO(user);
        return u;
    }
}
