package com.myframework.sbase.sys.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Privilege extends BaseModel {

    @Column( unique = true, nullable = false)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "privileges", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Role> roles;

    public Privilege() {
        super();
    }

    public Privilege(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    public Privilege(String name, List<Role> roles, String description) {
        super();
        this.name = name;
        this.description = description;
        this.roles = roles;
    }

    public Privilege(Long id, Date createdDate, boolean isDeleted, String name, String description, List<Role> roles) {
        super(id, createdDate, isDeleted);
        this.name = name;
        this.description = description;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
