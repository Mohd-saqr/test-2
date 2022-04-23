package com.example.universityRegestrationSchedule.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
public class Instructor implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String instructorName;
    private String username;
    private String password;
    private String email;
    private String dayOfBirth;
    private String telephone;

    @ManyToMany(mappedBy = "inst")
    public Set<Courses> courseInstructor;

    @ManyToOne
    private Role roleInstuctor;

    public Instructor(String instructorName, String username, String password, String email, String dayOfBirth, String telephone) {
        this.instructorName = instructorName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dayOfBirth = dayOfBirth;
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Role getroleInstuctor() {
        return roleInstuctor;
    }

    public Instructor() {
    }

    public Role getRoleInstuctor() {
        return roleInstuctor;
    }

    public void setRoleInstuctor(Role roleInstuctor) {
        this.roleInstuctor = roleInstuctor;
    }


    public Set<Courses> getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(Set<Courses> courseInstructor) {
        this.courseInstructor = courseInstructor;
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

    public void setRole(Role role) {
        this.roleInstuctor = role;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(roleInstuctor.getName()));
        return authorities;
    }
}
