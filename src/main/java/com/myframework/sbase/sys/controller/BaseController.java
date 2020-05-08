package com.myframework.sbase.sys.controller;


import com.myframework.sbase.sys.domain.BaseModel;
import com.myframework.sbase.sys.domain.Privilege;
import com.myframework.sbase.sys.helper.AuthorityHelper;
import com.myframework.sbase.sys.service.GenericService;
import com.myframework.sbase.sys.service.PrivilegeService;
import com.myframework.sbase.sys.service.UserDetailsImp;
import com.myframework.sbase.sys.type.OperationType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class BaseController< T extends BaseModel, S extends GenericService> {

    S genericService;

    public BaseController(PrivilegeService privilegeService, S genericService) {
        String className = this.getClass().getSimpleName();
        if (!className.equals("BaseController")) {
            String getPrivilege = OperationType.GET.name() + "_" + className;
            String postPrivilege = OperationType.ADD.name() + "_" + className;
            String updatePrivilege = OperationType.UPDATE.name() + "_" + className;
            String deletePrivilege = OperationType.DELETE.name() + "_" + className;

            List<Privilege> privileges = new ArrayList<>();

            if (privilegeService.getBYName(getPrivilege) == null)
                privileges.add(new Privilege(getPrivilege,"Get one or all"));

            if (privilegeService.getBYName(getPrivilege) == null)
                privileges.add(new Privilege(postPrivilege,"Insert Privilege"));

            if (privilegeService.getBYName(getPrivilege) == null)
                privileges.add(new Privilege(updatePrivilege,"Update Privilege"));

            if (privilegeService.getBYName(getPrivilege) == null)
                privileges.add(new Privilege(deletePrivilege,"Delete Privilege"));

            privilegeService.saveAll(privileges);
        }

        this.genericService = genericService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(Authentication authentication) {
//        return new ResponseEntity<>(authentication, HttpStatus.OK);
//        UserDetails authentication = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!AuthorityHelper.hasAuthority(authentication.getAuthorities(), this.getClass().getSimpleName(), OperationType.GET))
//            return new ResponseEntity<>(authentication, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        try {
            return new ResponseEntity<>(genericService.getAll(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id, Authentication authentication) {
        if (!AuthorityHelper.hasAuthority(authentication.getAuthorities(), this.getClass().getSimpleName(), OperationType.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        try {
            return new ResponseEntity<>(genericService.getOne(id), HttpStatus.OK);
        }  catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody T input, Authentication authentication) {
        if (!AuthorityHelper.hasAuthority(authentication.getAuthorities(), this.getClass().getSimpleName(), OperationType.ADD))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        try {
            return new ResponseEntity<>(genericService.save(input), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody T input, Authentication authentication) {
        if (!AuthorityHelper.hasAuthority(authentication.getAuthorities(), this.getClass().getSimpleName(), OperationType.UPDATE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        try {
            return new ResponseEntity<>(genericService.save(input), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id, Authentication authentication) {
        if (!AuthorityHelper.hasAuthority(authentication.getAuthorities(), this.getClass().getSimpleName(), OperationType.DELETE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        try {
            genericService.delete(genericService.getOne(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
