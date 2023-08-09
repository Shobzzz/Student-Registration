// CourseServiceImpl.java
package com.example.StudentCrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.StudentCrud.domain.Course;
import com.example.StudentCrud.repository.CourseRepository;

import jakarta.transaction.Transactional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }
    
    @Override
    public Course getCourseById(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        return courseOptional.orElse(null);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
    
    @Override
    public Course updateCourse(Course course) {
        Optional<Course> existingCourseOptional = courseRepository.findById(course.getCourseId());
        if (existingCourseOptional.isPresent()) {
            Course existingCourse = existingCourseOptional.get();
            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setCourseFee(course.getCourseFee());
            existingCourse.setCourseDescription(course.getCourseDescription());
            return courseRepository.save(existingCourse);
        } else {
            // Handle the case where the course doesn't exist
            throw new CourseNotFoundException("Course not found with ID: " + course.getCourseId());
        }
    }

    
  
}
