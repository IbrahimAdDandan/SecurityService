package com.myframework.sbase.controller;

import com.myframework.sbase.sys.controller.BaseController;
import com.myframework.sbase.sys.domain.BaseModel;
import com.myframework.sbase.sys.domain.Role;
import com.myframework.sbase.sys.service.GenericService;
import com.myframework.sbase.sys.service.PrivilegeService;
import com.myframework.sbase.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class Roles extends BaseController<Role, RoleService> {

    @Autowired
    public Roles(PrivilegeService privilegeService, RoleService roleService) {
        super(privilegeService, roleService);
    }

}
