package com.tpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpro.domain.Student;
import com.tpro.exception.ConflictException;
import com.tpro.exception.ResourceNotFoundException;
import com.tpro.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	// Get All Students
	public List<Student> getAll() {
		
		return studentRepository.findAll();
		
	}

	
	// Create Student
	public void createStudent(Student student) {
		if(studentRepository.existsByEmail(student.getEmail())) {
			throw new ConflictException("Email is already exist!");
		}
		studentRepository.save(student);
		
		
	}

    // find Student By ID
	public Student findStudent(Long id) {
		return studentRepository.findById(id).
		orElseThrow(()-> new ResourceNotFoundException("Student not found with id : " + id));
	}


	public void deleteStudent(Long id) {
		 Student student = findStudent(id);
		 studentRepository.delete(student);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
