package com.example.universityRegestrationSchedule.Models;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "rolesStd")
    private Set<Student> student;

    @OneToMany(mappedBy = "roleInstuctor")
    private Set<Instructor> instructor;

    @OneToOne(mappedBy = "roleAdmin")
    private Admin admin;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Student> getStudent() {
        return student;
    }

    public void setStudent(Set<Student> student) {
        this.student = student;
    }

    public Set<Instructor> getInstructor() {
        return instructor;
    }

    public void setInstructor(Set<Instructor> instructor) {
        this.instructor = instructor;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setName(String name) {
        this.name = name;
    }
}
