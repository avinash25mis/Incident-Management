package com.configuration.security.dto;

import com.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author avinash.a.mishra
 */

@Getter
public class SecurityUserVO implements UserDetails {

   private String username;
   private String password;
   private boolean enabled;
   private List<GrantedAuthority> grantedAuthorityList;

    public SecurityUserVO(User user){
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.enabled=user.isEnabled();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        this.grantedAuthorityList=authorities;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
