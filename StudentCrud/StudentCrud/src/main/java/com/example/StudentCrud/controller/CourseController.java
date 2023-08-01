// CourseController.java
package com.example.StudentCrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.StudentCrud.domain.Course;
import com.example.StudentCrud.service.CourseService;

@Controller
public class CourseController {

  
    @Autowired
    private CourseService courseService;

    // Other controller methods go here

    // Controller method to display the Course-details page
    
    @GetMapping("/new-course")
    public String showCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "add-course";
    }
    
    
    @GetMapping("/course-details")
    public String showCourseDetails(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "course-details";
    }


    // Controller method to redirect to the add-course page with previous values for editing
    @GetMapping("/edit-course/{id}")
    public String editCourse(@PathVariable("id") Long courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("course", course);
        return "add-course";
    }

    // Controller method to handle course deletion
    @GetMapping("/delete-course/{id}")
    public String deleteCourse(@PathVariable("id") Long courseId) {
        courseService.deleteCourse(courseId);
        return "redirect:/course-details";
    }
    
    @PostMapping("/save-course")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.saveCourse(course);
        return "redirect:/course-details"; // Redirect to the Course-details page after saving the course
    }
//
//    @PostMapping("/save-course")
//    public String saveCourse(@ModelAttribute("course") Course course) {
//        courseService.saveCourse(course);
//        return "redirect:/index";
//    }
}