package com.example.universityRegestrationSchedule.Models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class Admin implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String username;
    private String password;

    @OneToOne
    @JoinColumn(name = "role_id" , referencedColumnName = "id")
    private Role roleAdmin;

    public Role getRoleAdmin() {
        return roleAdmin;
    }

    public void setRoleAdmin(Role roleAdmin) {
        this.roleAdmin = roleAdmin;
    }

    public Admin() {
    }
    public Admin(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
