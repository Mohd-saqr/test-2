package com.example.universityRegestrationSchedule.Controllers;

import com.example.universityRegestrationSchedule.Models.Admin;
import com.example.universityRegestrationSchedule.Models.Role;
import com.example.universityRegestrationSchedule.Repository.AdminRepo;
import com.example.universityRegestrationSchedule.Repository.RoleRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AdminController {

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PasswordEncoder encoder;

    //********************************************** (SignUp Admin)*****************************************************

    @PostMapping("/addAdmin")
    public RedirectView addAdmin(@RequestParam String name,
                                 @RequestParam String username,
                                 @RequestParam String password,
                                 Model model) {
       Admin admin = new Admin(name, username, encoder.encode(password));
       Role role = (Role) roleRepo.findRoleByName("ADMIN");
        admin.setRoleAdmin(role);
        model.addAttribute("adminList",adminRepo.save(admin));

        return new RedirectView("/addAdmin");
    }

    @GetMapping("/addAdmin")
    public String getAdmins() {
        return "admin";
    }

    //********************************************** (Get All Students)*************************************************

    @GetMapping("/admins")
    String getStudents(Model model) {
        model.addAttribute("adminList", adminRepo.findAll());
        return "getStudents";
    }

}
