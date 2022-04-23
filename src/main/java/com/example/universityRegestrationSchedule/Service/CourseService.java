package com.example.universityRegestrationSchedule.Service;


import com.example.universityRegestrationSchedule.Models.Courses;

import java.util.List;

public interface CourseService {

    List<Courses> getAllCourses();
    Courses saveCourses(Courses courses);
    void deleteStudent(Long studentId);
    Courses getCoursesById(Long courseId);
    Courses updateCourses(Courses courses);
}
