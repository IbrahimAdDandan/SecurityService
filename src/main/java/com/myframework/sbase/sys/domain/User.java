package com.myframework.sbase.sys.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class User extends BaseModel{

    @Column( unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column( unique = true, nullable = false)
    private String email;

    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    @JsonIgnore
    private List<Role> roles;

    public User() {
        super();
    }

    public User(String username, String password, String email, boolean isEnabled, List<Role> roles) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    public Long getId() { return super.getId(); }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
