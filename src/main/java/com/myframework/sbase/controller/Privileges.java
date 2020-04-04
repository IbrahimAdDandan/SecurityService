package com.myframework.sbase.controller;

import com.myframework.sbase.sys.controller.BaseController;
import com.myframework.sbase.sys.domain.BaseModel;
import com.myframework.sbase.sys.domain.Privilege;
import com.myframework.sbase.sys.domain.Role;
import com.myframework.sbase.sys.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/privileges")
public class Privileges extends BaseController<Privilege, PrivilegeService>{

    @Autowired
    public Privileges(PrivilegeService privilegeService) {
        super(privilegeService, privilegeService);
    }

}
