package com.example.universityRegestrationSchedule.Scurity;


import com.example.universityRegestrationSchedule.Models.Admin;
import com.example.universityRegestrationSchedule.Models.Instructor;
import com.example.universityRegestrationSchedule.Models.Student;
import com.example.universityRegestrationSchedule.Repository.AdminRepo;
import com.example.universityRegestrationSchedule.Repository.InstructorRepo;
import com.example.universityRegestrationSchedule.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    StudentRepo stdRepo;
    @Autowired
    InstructorRepo instructorRepo;
    @Autowired
    AdminRepo adminRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<Student> optionalUser1 = Optional.ofNullable(stdRepo.findByUsername(username));
        final Optional<Instructor> optionalUser2 =  Optional.ofNullable(instructorRepo.findByUsername(username));
        final Optional<Admin> optionalUser3 =  Optional.ofNullable(adminRepo.findByUsername(username));
        if (optionalUser1.isPresent()) {
            return optionalUser1.get();
        }
        else if (optionalUser2.isPresent()){

            return optionalUser2.get();

        }
        else if (optionalUser3.isPresent()){
            return optionalUser3.get();

        }

        else {
            throw new UsernameNotFoundException(MessageFormat.format("User with name {0} cannot be found.", username));
        }


    }


}