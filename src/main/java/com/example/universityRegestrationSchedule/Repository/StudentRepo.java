package com.example.universityRegestrationSchedule.Repository;

import com.example.universityRegestrationSchedule.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface StudentRepo extends JpaRepository <Student,Long>{
    Student findByUsername(String username);


}
