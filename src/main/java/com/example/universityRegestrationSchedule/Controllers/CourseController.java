package com.example.universityRegestrationSchedule.Controllers;

import com.example.universityRegestrationSchedule.Models.Courses;
import com.example.universityRegestrationSchedule.Repository.CoursesRepo;
import com.example.universityRegestrationSchedule.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CourseController {

    @Autowired
    CoursesRepo coursesRepo;

    @Autowired
    CourseService courseService;

    //*********************************************** (Get Courses) **************************************

    @GetMapping("/courses")
    String getCourses(Model model) {
        model.addAttribute("Courses", coursesRepo.findAll());
        return "courses";
    }

    //*********************************************** (ADD Courses) ************************************/**********

    @GetMapping("/addcourse")
    public String addCourse(Model model) {
        return "addCourses";
    }

    @PostMapping("/addcourse")
    public RedirectView addCourse(@RequestParam String name,
                                  @RequestParam String classRoom,
                                  @RequestParam String instructorName,
                                  @RequestParam String time,
                                  @RequestParam String days,
                                  Model model) {
        Courses courses = new Courses(name,classRoom , instructorName, time, days);
        model.addAttribute("course",coursesRepo.save(courses));
        return new RedirectView("/courses");
    }

    //*********************************************** (Update Courses) *************************************************

    @GetMapping("/update/{id}")
    public String getUpdateCourse(@PathVariable Long id, Model model) {
        model.addAttribute("courses", courseService.getCoursesById(id));
        return "updateCourses";
    }

    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable Long id,
                               @ModelAttribute("courses") Courses courses,
                               Model model) {


        Courses existingCourse = courseService.getCoursesById(id);
        existingCourse.setId(id);
        existingCourse.setName(courses.getName());
        existingCourse.setClassRoom(courses.getClassRoom());
        existingCourse.setInstructorName(courses.getInstructorName());
        existingCourse.setTime(courses.getTime());
        existingCourse.setDays(courses.getDays());
        courseService.updateCourses(existingCourse);
        return "redirect:/courses";
    }

    //*********************************************** (Delete Courses) *************************************************

    @GetMapping("/delete/{id}")
    public RedirectView deleteCourse(@PathVariable Long id) {
        coursesRepo.deleteById(id);
        return new RedirectView("/courses");
    }
}
