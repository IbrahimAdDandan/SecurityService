package com.myframework.sbase.sys.service;

import com.myframework.sbase.sys.repository.RoleRepository;
import com.myframework.sbase.sys.repository.UserRepository;
import com.myframework.sbase.sys.domain.Privilege;
import com.myframework.sbase.sys.domain.Role;
import com.myframework.sbase.sys.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service("userDetailsService")
@Transactional
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true,this.getAuthorities(user));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(User user) {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        Collection<String> privileges = this.getPrivileges(user.getRoles());
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

    public List<User> getUsers() { return (List<User>) userRepository.findAll();}

    public User get() {
        return new User();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User getAuthByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    public User add(User user) {
        return userRepository.save(user);
    }

}
