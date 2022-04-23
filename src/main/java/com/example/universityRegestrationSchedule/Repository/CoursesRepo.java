package com.example.universityRegestrationSchedule.Repository;


import com.example.universityRegestrationSchedule.Models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CoursesRepo extends JpaRepository<Courses, Long> {
    List<Courses> findAllByInstructorName(String instructorName);

//    @Query(value = "DELETE FROM courses_std WHERE  courses_list_id =:id1 AND std_id=:id2", nativeQuery = true)
//    List<Courses> deleteCourse(@Param("id") Long id1 , @Param("id") Long id2);
}

