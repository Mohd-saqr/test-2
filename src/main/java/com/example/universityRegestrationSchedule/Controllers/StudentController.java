package com.example.universityRegestrationSchedule.Controllers;

import com.example.universityRegestrationSchedule.Repository.*;

import com.example.universityRegestrationSchedule.Models.Courses;
import com.example.universityRegestrationSchedule.Models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class StudentController{

    @Autowired
    CoursesRepo coursesRepo;

    @Autowired
    StudentRepo stdRepo;

    //********************************************** (Get All Students)*************************************************

    @GetMapping("/students")
    String getStudents(Model model) {
        model.addAttribute("studentList", stdRepo.findAll());
        return "getStudents";
    }

    //********************************************** (add course To studentSchedule)************************************

    @PostMapping("/addCourse/{id}")
    public RedirectView addCourseToStudentSchedule(Principal p, @PathVariable Long id) {
        Student newStudent = stdRepo.findByUsername(p.getName());
        Long stdId = newStudent.getId();
        Courses newCourse = coursesRepo.findById(id).get();
        newStudent.getCoursesList().add(newCourse);
        newCourse.getStd().add(newStudent);
        stdRepo.save(newStudent);
        coursesRepo.save(newCourse);
        return new RedirectView("/student");
    }

    @GetMapping("/student")
    public String getStudentProfile(Model model) {
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student = stdRepo.findByUsername(currentUser);

        model.addAttribute("name", student.getFullName());
        model.addAttribute("username", student.getUsername());
        model.addAttribute("email", student.getEmail());
        model.addAttribute("dayOfBirth",student.getDayOfBirth());
        model.addAttribute("telephone", student.getTelephone());
        model.addAttribute("CoursesStd", student.getCoursesList());
        return "studentSchedule";
    }

    //********************************************** (Delete course )***************************************************

    @GetMapping("/delete_schedule/{id}")
    public RedirectView deleteCourse(Principal p, @PathVariable Long id, @ModelAttribute Courses courses) {
        Student newStudent = stdRepo.findByUsername(p.getName());
        Courses newCourse = coursesRepo.findById(id).get();
        newStudent.getCoursesList().remove(newCourse);
        newCourse.getStd().remove(newStudent);
        stdRepo.save(newStudent);
        coursesRepo.save(newCourse);
        return new RedirectView("/student");
    }
}
