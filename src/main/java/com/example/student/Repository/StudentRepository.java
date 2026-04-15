package com.example.student.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	Optional<Student> findByRollNo(int rollNo);
	
	List<Student> findByNameContainingIgnoreCase(String name);
}
