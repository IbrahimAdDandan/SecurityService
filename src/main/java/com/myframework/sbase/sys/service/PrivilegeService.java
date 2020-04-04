package com.myframework.sbase.sys.service;

import com.myframework.sbase.sys.repository.PrivilegeRepository;
import com.myframework.sbase.sys.domain.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PrivilegeService extends GenericService<Privilege>{

    @Autowired
    PrivilegeRepository privilegeRepository;

    public Privilege getBYName(String name) {
        return this.privilegeRepository.findByName(name);
    }

}
