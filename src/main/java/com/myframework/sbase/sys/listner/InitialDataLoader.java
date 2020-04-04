package com.myframework.sbase.sys.listner;

import com.myframework.sbase.sys.domain.Privilege;
import com.myframework.sbase.sys.domain.Role;
import com.myframework.sbase.sys.domain.User;
import com.myframework.sbase.sys.repository.PrivilegeRepository;
import com.myframework.sbase.sys.repository.RoleRepository;
import com.myframework.sbase.sys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Component
public class InitialDataLoader
        implements ApplicationListener<ContextRefreshedEvent>
{

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    public InitialDataLoader(UserRepository userRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilege adminPrivilege = createPrivilegeIfNotFound();
//        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE", "Can write anything");

        List<Privilege> adminPrivileges = Collections.singletonList(adminPrivilege);
        createRoleIfNotFound(adminPrivileges);
//        createRoleIfNotFound("ROLE_USER", Arrays.asList(adminPrivilege));
        createAdminIfNotFound();
        alreadySetup = true;
    }

    @Transactional
    void createAdminIfNotFound() {
        User u = userRepository.findByEmail("test@test.com");
        if (u == null) {
            Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN");
            User user = new User();
            user.setUsername("admin");
            user.setPassword("{noop}admin");
            user.setEmail("test@test.com");
            user.setRoles(Collections.singletonList(adminRole));
            user.setEnabled(true);
            userRepository.save(user);
        }
    }

    @Transactional
    Privilege createPrivilegeIfNotFound() {

        Privilege privilege = privilegeRepository.findByName("ADMIN");
        if (privilege == null) {
            privilege = new Privilege("ADMIN", "Can Do anything");
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    void createRoleIfNotFound(List<Privilege> privileges) {

        Role role = roleRepository.findByRoleName("ROLE_ADMIN");
        if (role == null) {
            role = new Role("ROLE_ADMIN");
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
    }
}