package com.example.StudentCrud.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import com.example.StudentCrud.domain.Course;
import com.example.StudentCrud.domain.Student;
import com.example.StudentCrud.service.CourseService;
import com.example.StudentCrud.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	public StudentService service;
	
	@Autowired
	private CourseService courseService;
	
	
	
	

	
	  @GetMapping("/index")
	    public String viewHomePage(@RequestParam(value = "filter", required = false, defaultValue = "all") String filter, Model model) {
	        List<Student> liststudent;
	        if (filter.equals("active")) {
	            liststudent = service.listActiveStudents();
	        } else if (filter.equals("inactive")) {
	            liststudent = service.listInactiveStudents();
	        } else {
	            liststudent = service.listAll();
	        }
	        model.addAttribute("liststudent", liststudent);
	        List<Course> courses = courseService.getAllCourses();
	        model.addAttribute("courses", courses);
	    return "index";
	    }
	  
	  @GetMapping("/table")
	    public String getFilteredTable(@RequestParam(name = "filter", required = false) String filter, Model model) {
	        List<Student> filteredStudents;
	        if ("active".equals(filter)) {
	            filteredStudents = service.listActiveStudents();
	        } else if ("inactive".equals(filter)) {
	            filteredStudents = service.listInactiveStudents();
	        } else {
	            filteredStudents = service.listAll();
	        }
	        model.addAttribute("liststudent", filteredStudents);
	        return "fragments/table_body :: table_Body";
	    }
	  
	  
	  
	  
	


	

	
	@GetMapping("/home")
	public String showHomePage(Model model) {
		model.addAttribute("message", "Student Registration Portal");
		return "Home";
	}
	 
	@GetMapping("/new")
	public String add(Model model) {
		 List<Course> courses = courseService.getAllCourses();
		    model.addAttribute("courses", courses);
		model.addAttribute("student", new Student());
		return "new";
		
	}
	
	@RequestMapping(value = "/save",method= RequestMethod.POST)
	public String saveStudent(@ModelAttribute("student")Student std) {
		
		service.save(std);
		return "redirect:/index";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("new");
	    Student std = service.get(id);
	    mav.addObject("student", std);
	    
	    List<Course> courses = courseService.getAllCourses(); // Get the list of available courses
	    mav.addObject("courses", courses); // Add it to the model
	    
	    return mav;
	}

	
	 @RequestMapping("/delete/{id}")
	    public String deletestudent(@PathVariable(name = "id") int id) {
	        Student std = service.get(id);
	        std.setDeleted(true); // Soft delete by updating the 'deleted' status
	        service.save(std); // Save the updated student
	        return "redirect:/index";
	    }
    
}