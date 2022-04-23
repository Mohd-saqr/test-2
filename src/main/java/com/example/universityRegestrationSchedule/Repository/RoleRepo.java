package com.example.universityRegestrationSchedule.Repository;




import com.example.universityRegestrationSchedule.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
     Role findRoleByName(String name) ;


}
