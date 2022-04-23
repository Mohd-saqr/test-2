package com.example.universityRegestrationSchedule.Controllers;


import com.example.universityRegestrationSchedule.Models.Admin;
import com.example.universityRegestrationSchedule.Models.Instructor;
import com.example.universityRegestrationSchedule.Models.Role;
import com.example.universityRegestrationSchedule.Models.Student;
import com.example.universityRegestrationSchedule.Repository.AdminRepo;
import com.example.universityRegestrationSchedule.Repository.InstructorRepo;
import com.example.universityRegestrationSchedule.Repository.RoleRepo;
import com.example.universityRegestrationSchedule.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class HomeController{

    @Autowired
    StudentRepo stdRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    InstructorRepo instructorRepo;
    private int anInt;

    @GetMapping("/")
    public String getHomePage(Principal principal, Model model) {
        String username;
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (obj instanceof UserDetails) {
            username = ((UserDetails) obj).getUsername();
            model.addAttribute("username", username);
        } else username = obj.toString();
        return principal != null ? "home" : "login";
    }

    //************************************************ (login page) ***************************************************

    @GetMapping("/login")
    public String getLoginPage() {
        if (adminRepo.findAll().size()==0){
            Admin admin = new Admin("admin", "admin", encoder.encode("0000"));
            Role role = roleRepo.findRoleByName("ADMIN");
            admin.setRoleAdmin(role);
            adminRepo.save(admin);
        }
        return "login";
    }

    @PostMapping("/login")
    public RedirectView getHome() {
        return new RedirectView("/");
    }

    //************************************************ (logout page) ***************************************************

    @GetMapping("/logout")
    public RedirectView getLogoutPage() {
        return new RedirectView("/login");
    }

    //************************************************ (SingUp Admin page) *********************************************

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }


    @PostMapping("/signup")
    public String signupUser(@RequestParam String studentName,
                             @RequestParam String username,
                             @RequestParam String password) {
        Admin admin = new Admin(studentName, username, encoder.encode(password));
        Role role = roleRepo.findRoleByName("ADMIN");
        admin.setRoleAdmin(role);
        adminRepo.save(admin);
        return "login";
    }

    //********************************************** (add students)*****************************************************

    @GetMapping("/signup/student")
    public String getSignupStudentPage() {
        return "createStudent";
    }

    @PostMapping("/signup/student")
    public RedirectView StudentSignup(@RequestParam String studentName,
                                      @RequestParam String username,
                                      @RequestParam String password,
                                      @RequestParam String email,
                                      @RequestParam String dayOfBirth,
                                      @RequestParam String telephone) {
        Student std = new Student(studentName , username , encoder.encode(password) , email , dayOfBirth , telephone);
        Role role = roleRepo.findRoleByName("STUDENT");
        std.setRolesStd(role);
        stdRepo.save(std);
        return new RedirectView("/students");
    }

    //********************************************** (add instructor)***************************************************

    @GetMapping("/signup/instructor")
    public String getSignupInstructorPage() {
        return "createInstructor";
    }

    @PostMapping("/signup/instructor")
    public RedirectView instructorSignup(@RequestParam(name = "instructorName") String instructorName,
                                         @RequestParam String username,
                                         @RequestParam String password,
                                         @RequestParam String email,
                                         @RequestParam String dayOfBirth,
                                         @RequestParam String telephone) {
        Instructor instructor = new Instructor(instructorName, username, encoder.encode(password),email , dayOfBirth , telephone);
        System.out.println(instructor);
        Role role = roleRepo.findRoleByName("INSTRUCTOR");
        instructor.setRole(role);
        instructorRepo.save(instructor);
        return new RedirectView("/instructors");
    }

    //********************************************** (delete students)**************************************************

    @GetMapping("/deleteStudent/{id}")
    public RedirectView deleteStudent(@PathVariable Long id) {
        stdRepo.deleteById(id);
        return new RedirectView("/students");
    }

    //********************************************** (delete instructor)************************************************

    @GetMapping("/deleteInstructor/{id}")
    public RedirectView deleteInstructor(@PathVariable Long id) {
        instructorRepo.deleteById(id);
        return new RedirectView("/instructors");
    }

    //************************************************ (Error page) ***************************************************

    @GetMapping("/error")
    public String getError() {
        return "error";
    }

}