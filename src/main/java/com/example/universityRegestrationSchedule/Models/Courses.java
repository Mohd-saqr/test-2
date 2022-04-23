package com.example.universityRegestrationSchedule.Models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String classRoom;
    private String instructorName;
    private String time;
    private String days;

    @ManyToMany
    public Set<Student> std;

    @ManyToMany
    public List<Instructor> inst;


    public Courses() {
    }

    public Courses(String name, String classRoom, String instructorName , String time, String days) {
        this.name = name;
        this.classRoom = classRoom;
        this.instructorName = instructorName;
        this.time = time;
        this.days = days;
    }

    public List<Instructor> getInst() {
        return inst;
    }

    public void setInst(List<Instructor> inst) {
        this.inst = inst;
    }

    public Set<Student> getStd() {
        return std;
    }

    public void setStd(Set<Student> std) {
        this.std = std;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classRoom='" + classRoom + '\'' +
                '}';
    }
}