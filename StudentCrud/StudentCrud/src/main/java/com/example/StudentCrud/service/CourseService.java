// CourseService.java
package com.example.StudentCrud.service;

import java.util.List;
import com.example.StudentCrud.domain.Course;

public interface CourseService {
    List<Course> getAllCourses();
    Course saveCourse(Course course);
    Course getCourseById(Long id);
    void deleteCourse(Long id);
    Course updateCourse(Course course);

   

}
