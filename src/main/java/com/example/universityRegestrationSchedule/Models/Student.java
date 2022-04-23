package com.example.universityRegestrationSchedule.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class Student implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    private String fullName;
    private String username;
    private String password;
    private String email;
    private String dayOfBirth;
    private String telephone;


    @ManyToOne
    private Role rolesStd;

    @ManyToMany(mappedBy = "std")
    public Set<Courses> coursesList;

    public Student(){
    }

    public Student(String fullName, String username, String password, String email, String dayOfBirth, String telephone) {
        this.fullName = fullName;
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

    public Role getRolesStd() {
        return rolesStd;
    }

    public void setRolesStd(Role rolesStd) {
        this.rolesStd = rolesStd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Courses> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(Set<Courses> coursesList) {
        this.coursesList = coursesList;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List <SimpleGrantedAuthority> authorities= new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(rolesStd.getName()));
        return authorities ;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rolesStd=" + rolesStd +
                ", coursesList=" + coursesList +
                '}';
    }
}
