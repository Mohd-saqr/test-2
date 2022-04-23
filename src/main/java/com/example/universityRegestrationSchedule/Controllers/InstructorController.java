package com.example.universityRegestrationSchedule.Controllers;

import com.example.universityRegestrationSchedule.Models.Courses;
import com.example.universityRegestrationSchedule.Models.Instructor;
import com.example.universityRegestrationSchedule.Repository.CoursesRepo;
import com.example.universityRegestrationSchedule.Repository.InstructorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class InstructorController{

    @Autowired
    InstructorRepo instructorRepo1;

    @Autowired
    CoursesRepo coursesRepo1;

    //********************************************** (get Instructor)***************************************************

    @GetMapping("/instructors")
    String getInstructors(Model model) {
        model.addAttribute("instructorsList", instructorRepo1.findAll());
        return "getInstructors";
    }

    //********************************************** (add to Instructor schedule )**************************************

    @PostMapping("/addCourseToInstructor/{id}")
    public RedirectView addCourseForInstructor(Principal p, @PathVariable Long id) {
        Instructor newInstructor = instructorRepo1.findByUsername(p.getName());

        Courses newCourse = coursesRepo1.findById(id).get();
        newInstructor.getCourseInstructor().add(newCourse);
        newCourse.getInst().add(newInstructor);
        instructorRepo1.save(newInstructor);
        coursesRepo1.save(newCourse);
        return new RedirectView("/instructor");
    }

    @GetMapping("/instructor")
    public String getInstructorProfile(Model model){
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Instructor instructor = instructorRepo1.findByUsername(currentUser);
        model.addAttribute("name" , instructor.getInstructorName());
        model.addAttribute("username" , instructor.getUsername());
        model.addAttribute("email" , instructor.getEmail());
        model.addAttribute("bod" , instructor.getDayOfBirth());
        model.addAttribute("tele" , instructor.getTelephone());
        model.addAttribute("CoursesInst" , instructor.getCourseInstructor());
        return "instructorSchedule";
    }

    //**************************************(Delete to Instructor schedule )********************************************

    @GetMapping("/delete_course/{id}")
    public RedirectView deleteCourse(Principal p, @PathVariable Long id, @ModelAttribute Courses courses) {

        Instructor newInstructor = instructorRepo1.findByUsername(p.getName());
        Long instructorId = newInstructor.getId();

        Courses newCourse = coursesRepo1.findById(id).get();
        newInstructor.getCourseInstructor().remove(newCourse);

        newCourse.getInst().remove(newInstructor);
        instructorRepo1.save(newInstructor);
        coursesRepo1.save(newCourse);

        return new RedirectView("/instructor");
    }
}
