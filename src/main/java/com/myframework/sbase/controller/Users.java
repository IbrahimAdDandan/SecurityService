package com.myframework.sbase.controller;

import com.myframework.sbase.sys.domain.User;
import com.myframework.sbase.sys.helper.AuthorityHelper;
import com.myframework.sbase.sys.service.AuthService;
import com.myframework.sbase.sys.service.PrivilegeService;
import com.myframework.sbase.sys.type.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class Users {

    AuthService authService;

    @Autowired
    public Users(PrivilegeService privilegeService, AuthService authService) {
        this.authService = authService;
    }

//    @GetMapping("")
//    public ResponseEntity<?> getAll(Authentication authentication) {
//        if (!AuthorityHelper.hasAuthority(authentication.getAuthorities(), this.getClass().getSimpleName(), OperationType.GET))
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        return new ResponseEntity<>(authService.getUsers(), HttpStatus.OK);
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getUserById(@PathVariable("id") Long userId, Authentication authentication) {
//        if (!AuthorityHelper.hasAuthority(authentication.getAuthorities(), this.getClass().getSimpleName(), OperationType.GET))
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        return new ResponseEntity<>(authService.getUserById(userId), HttpStatus.OK);
//    }

//    @PutMapping("")
//    public ResponseEntity<?> updateUser(@RequestBody User user, Authentication authentication) {
//        if (!AuthorityHelper.hasAuthority(authentication.getAuthorities(), this.getClass().getSimpleName(), OperationType.UPDATE))
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        return new ResponseEntity<>(authService.add(user), HttpStatus.OK);
//    }

//    @PutMapping("/change-password")
//    public ResponseEntity<?> updatePassword(@RequestBody String password, Authentication authentication) {
//        User user = (User)authentication.getPrincipal();
//        Long userId = user.getId();
//        User user1 = authService.getUserById(userId).get();
//        user1.setPassword("{noop}"+password);
//        authService.add(user1);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable("id") Long userId, Authentication authentication) {
//        if (!AuthorityHelper.hasAuthority(authentication.getAuthorities(), this.getClass().getSimpleName(), OperationType.DELETE))
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        authService.remove(userId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PostMapping("")
//    public ResponseEntity<?> addUser(@RequestBody User user, Authentication authentication) {
//        if (!AuthorityHelper.hasAuthority(authentication.getAuthorities(), this.getClass().getSimpleName(), OperationType.ADD))
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        return new ResponseEntity<>(authService.add(user), HttpStatus.OK);
//    }

}
