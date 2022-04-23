package com.example.universityRegestrationSchedule.Service;


import com.example.universityRegestrationSchedule.Models.Courses;
import com.example.universityRegestrationSchedule.Repository.CoursesRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CoursesServiceImpl implements CourseService {

    private CoursesRepo coursesRepo;

    public CoursesServiceImpl(CoursesRepo coursesRepo) {
        super();
        this.coursesRepo = coursesRepo;
    }

    @Override
    public List<Courses> getAllCourses() {
        return coursesRepo.findAll();
    }

    @Override
    public Courses saveCourses(Courses courses) {
        return coursesRepo.save(courses);
    }

    @Override
    public Courses getCoursesById(Long id) {
        return coursesRepo.findById(id).get();
    }

    @Override
    public Courses updateCourses(Courses courses) {
        return coursesRepo.save(courses);
    }

    @Override
    public void deleteStudent(Long courseId) {
        coursesRepo.deleteById(courseId);
    }
}
