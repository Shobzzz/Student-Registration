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
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView editCourse(@PathVariable("id") Long courseId) {
        ModelAndView modelAndView = new ModelAndView("add-course"); // Specify the view name

        Course course = courseService.getCourseById(courseId);
        modelAndView.addObject("course", course); // Add the course object to the model

        return modelAndView;
    }
    // Controller method to handle course deletion
    @GetMapping("/delete-course/{id}")
    public String deleteCourse(@PathVariable("id") Long courseId) {
        courseService.deleteCourse(courseId);
        return "redirect:/course-details";
    }
    
    @PostMapping("/save-course")
    public String saveCourse(@ModelAttribute("course") Course course) {
        if (course.getCourseId() != null) {
            // Existing course, update it
            courseService.updateCourse(course);
        } else {
            // New course, save it
            courseService.saveCourse(course);
        }
        return "redirect:/course-details";
    }

//
//    @PostMapping("/save-course")
//    public String saveCourse(@ModelAttribute("course") Course course) {
//        courseService.saveCourse(course);
//        return "redirect:/index";
//    }
}