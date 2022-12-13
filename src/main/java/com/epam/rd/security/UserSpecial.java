package com.epam.rd.security;

import com.epam.rd.model.entity.User;
import com.epam.rd.model.enumerations.URole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * User details implementation removed by User class and implement  for this class
 */
public class UserSpecial implements UserDetails {
    private String email;
    private String password;
    private boolean rememberMe;


    private Collection<SimpleGrantedAuthority> authorities;

    public UserSpecial() {
    }

    public UserSpecial(User u) {
        this.email = u.getEmail();
        this.password = u.getPassword();


    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Transactional
    public void setRoles(Set<URole> roles) {
        this.authorities = new HashSet<SimpleGrantedAuthority>();
        roles.forEach(l -> authorities.add(new SimpleGrantedAuthority(l.name())));
    }
}
