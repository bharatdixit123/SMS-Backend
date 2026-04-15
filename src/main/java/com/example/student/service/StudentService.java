package com.example.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import com.example.student.Repository.StudentRepository;
import com.example.student.model.Student;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepo;
	
	public void saveStudent(Student student) {
		System.out.println("StudentDao.saveStudentService");
		if(student != null) {
			studentRepo.save(student);
		}
	}
	
	public Student getStudentById(int id) {
		 return studentRepo.findById(id).orElse(null);
	 }
	
	public Optional<Student> getStudentByRollNo(int rollNo) {
	    return studentRepo.findByRollNo(rollNo);
	}
	
	// Fetch student by id
    public Student showUpdateForm(int id) {
        Student student = studentRepo.findById(id).orElse(null);
        if(student == null) {
            return null;
        }
        return student;
    }
	
//    Update student by id
    public Student updateForm(Student student) {

        Student existingStudent = studentRepo.findById(student.getId()).orElse(null);

        if (existingStudent == null) {
            return null;
        }

        existingStudent.setName(student.getName());
        existingStudent.setRollNo(student.getRollNo());
        existingStudent.setGender(student.getGender());
        existingStudent.setAge(student.getAge());
        existingStudent.setCourse(student.getCourse());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPhone(student.getPhone());

        studentRepo.save(existingStudent);
        return existingStudent;
    }
    
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    
    public List<Student> search(String keyword) {

        // Null ya empty check
        if (keyword == null || keyword.isBlank()) {
            return studentRepo.findAll(); // Empty keyword → return all students
        }

        keyword = keyword.trim();

        // Agar keyword sirf number hai → roll no search
        if (keyword.matches("\\d+")) {
            int rollNo = Integer.parseInt(keyword);
            return studentRepo.findByRollNo(rollNo)
                    .map(List::of)   // Optional ko List mein convert
                    .orElse(List.of()); // agar rollNo match nahi hua → empty list
        }

        // Otherwise name search (partial, case-insensitive)
        return studentRepo.findByNameContainingIgnoreCase(keyword);
    }


	 
        public void deleteStdById(int id) {
	     if(id<=0) {
		 throw new IllegalArgumentException("Invalid student id");			 
    }
	 studentRepo.deleteById(id);
    }

    }
