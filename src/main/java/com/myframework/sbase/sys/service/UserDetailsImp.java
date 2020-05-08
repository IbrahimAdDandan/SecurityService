package com.myframework.sbase.sys.service;

import com.myframework.sbase.sys.domain.Privilege;
import com.myframework.sbase.sys.domain.Role;
import com.myframework.sbase.sys.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class UserDetailsImp implements UserDetails {

    private User user;

    public UserDetailsImp() {
    }

    public UserDetailsImp(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        Collection<String> privileges = this.getPrivileges(this.user.getRoles());
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
//        authorities.add(new SimpleGrantedAuthority(this.user.getRoles()));
        return authorities;
    }

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
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

    public Long getUserid() {
        return this.user.getId();
    }

    public String getUSerEmail() {
        return this.user.getEmail();
    }
}
