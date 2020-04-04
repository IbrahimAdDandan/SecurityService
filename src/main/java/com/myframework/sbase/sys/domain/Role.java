package com.myframework.sbase.sys.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Role extends BaseModel {

    @Column( unique = true, nullable = false)
    private String roleName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    @JsonIgnore
    List<Privilege> privileges;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> users;
    
    public Role() {
        super();
    }

    public Role(String roleName) {
        super();
        this.roleName = roleName;
    }

    public Role(String roleName, List<Privilege> privileges, List<User> users) {
        super();
        this.roleName = roleName;
        this.privileges = privileges;
        this.users = users;
    }

    public Role(Long id, Date createdDate, boolean isDeleted, String roleName, List<Privilege> privileges, List<User> users) {
        super(id, createdDate, isDeleted);
        this.roleName = roleName;
        this.privileges = privileges;
        this.users = users;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
