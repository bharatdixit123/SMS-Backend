package com.example.student.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.student.model.Student;
import com.example.student.service.StudentService;

@Controller
@Service
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
//	StudentForm
	@GetMapping("/")
	public String studentForm(Model model) {
		System.out.println("MyController.MyController()");
		model.addAttribute("student", new Student());
		return "student-form";
	}
	
//	SaveData
	@PostMapping("/saveData")
    public String saveStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/studentList"; // model attribute not needed in redirect
    }
	
//	getStudentById
	@GetMapping("/getStd/{id}")
	public String getStudentById(@PathVariable("id") int id, Model model) {
		System.out.println("StudentController.getStudentById()");
		Student student = studentService.getStudentById(id);
		
		if(student==null) {
			model.addAttribute("msg", "Student with given id not found");
			System.out.println("Student not found");
			return "error-page";
		}
		
		 model.addAttribute("std", student);
		    return "studentProfile";
	}
	

//	ShowUpdateForm
	    //1.GET request-show existing student in update form
		@GetMapping("/updateStd/{id}")
		public String showUpdateForm(@PathVariable("id") int id, Model model) {
		    Student student = studentService.getStudentById(id); // only fetch
		    
		    if(student == null) {
		        model.addAttribute("msg", "Student with given id not found");
		        return "error-page";
		    }
		    		    
		    model.addAttribute("student", student);
		    return "studentUpdateForm";
		}

//		UpdateData
		//2.POST request-actually update the student
		@PostMapping("/updateData")
		public String updateForm(@ModelAttribute Student student, Model model) {
		    
		    Optional<Student> existingStudent =
		            studentService.getStudentByRollNo(student.getRollNo());		    
		 // agar rollNo mil gaya AND wo kisi aur student ka hai
		    if (existingStudent.isPresent() &&
		        existingStudent.get().getId() != student.getId()) {

		        model.addAttribute("msg", "Roll Number already exists");
		        model.addAttribute("student", student);

		        return "studentUpdateForm"; // wahi form dubara dikhao
		    }


		    return "redirect:/studentList";
		}
		
		
		@GetMapping("/studentList")
		public String showStudentList(Model model) {

		    List<Student> students = studentService.getAllStudents();

		    if (students.isEmpty()) {
		        model.addAttribute("msg", "No students found");
		    }

		    model.addAttribute("students", students);

		    return "studentList";
		}

		
//		StudentSearch
		@GetMapping("/studentSearch")
		public String studentSearch(
		        @RequestParam(required = false) String keyword,
		        Model model) {
			
			List<Student> students = studentService.search(keyword);

		    if (students.isEmpty()) {
		        model.addAttribute("msg", "No student found");
		    }

		    model.addAttribute("students", students);
		    model.addAttribute("keyword", keyword);
		    return "studentList";
		}


//		deleteStudent
		@GetMapping("/deleteStd/{id}")
		public String deleteStdById(@PathVariable("id") int id) {
		    studentService.deleteStdById(id);
		    return "redirect:/studentList";
	}


}
