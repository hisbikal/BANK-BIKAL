/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.webservices.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Hisbikal-Haqqi
 */
public class MyUserDetail implements UserDetails {

    private User user;

    public MyUserDetail(User user) {
        this.user = user;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        user.getUserRoleList().forEach((userRole) -> {
            authorities.add(new SimpleGrantedAuthority(userRole.getRoleId().getRoleName().toUpperCase()));
        });
        
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getIsVerified();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getIsVerified();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getIsVerified();
    }

    @Override
    public boolean isEnabled() {
        return user.getIsVerified();
    }
    
}