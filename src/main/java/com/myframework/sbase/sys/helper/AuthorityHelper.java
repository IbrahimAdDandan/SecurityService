package com.myframework.sbase.sys.helper;

import com.myframework.sbase.sys.type.OperationType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class AuthorityHelper {

    public static boolean hasAuthority(UserDetails user, String privilege) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.addAll(user.getAuthorities());
        GrantedAuthority authority = new SimpleGrantedAuthority(privilege);
        return (authorities.contains(new SimpleGrantedAuthority("ADMIN")) || authorities.contains(authority));
    }

    /**
     *
     * @param user {@link UserDetails}
     * @param className {@link String}
     * @param operationType {@link OperationType}
     * @return boolean
     */
    public static boolean hasAuthority(UserDetails user, String className, OperationType operationType) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.addAll(user.getAuthorities());
        String privilege = operationType.name() + "_" + className;
        GrantedAuthority authority = new SimpleGrantedAuthority(privilege);
        return (authorities.contains(new SimpleGrantedAuthority("ADMIN")) || authorities.contains(authority));
    }

    /**
     *
     * @param authorities {@link GrantedAuthority}
     * @param className {@link String}
     * @param operationType {@link OperationType}
     * @return boolean
     */
    public static boolean hasAuthority(Collection<? extends GrantedAuthority> authorities, String className, OperationType operationType) {
        String privilege = operationType.name() + "_" + className;
        GrantedAuthority authority = new SimpleGrantedAuthority(privilege);
        return (authorities.contains(new SimpleGrantedAuthority("ADMIN")) || authorities.contains(authority));
    }

    public static boolean hasAuthority(List<GrantedAuthority> authorities, String privilege) {

        GrantedAuthority authority = new SimpleGrantedAuthority(privilege);
        return (authorities.contains(new SimpleGrantedAuthority("ADMIN")) || authorities.contains(authority));
    }

}
