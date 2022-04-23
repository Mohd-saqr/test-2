package com.example.universityRegestrationSchedule.Repository;


import com.example.universityRegestrationSchedule.Models.Instructor;
import com.example.universityRegestrationSchedule.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor,Long> {
    Instructor findByUsername(String username);

}
