package com.example.universityRegestrationSchedule.Repository;


import com.example.universityRegestrationSchedule.Models.Admin;
import com.example.universityRegestrationSchedule.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    Role findRoleByName(String admin);

    Admin findByUsername(String username);
}
