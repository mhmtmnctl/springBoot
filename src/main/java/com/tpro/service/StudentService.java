package com.tpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tpro.domain.Student;
import com.tpro.dto.StudentDTO;
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

	
	public void updateStudent(Long id, StudentDTO studentDTO) {
		
	boolean emailExist=	studentRepository.existsByEmail(studentDTO.getEmail());//boolean değer dönecek//db de yeni girilen email var mıyok mu bakıyoruz.
	//false dönerse yani bu email önceden db de yokmuş demek.
	Student student= findStudent(id);//burada da öğrencinin diğer bilgilerini aldık. şuan login olan kullanıcının
	
	//email exist mi? ve anlık olarak gelen kullanıcıya mı ait bunun kontrolünü yapalım
	if (emailExist && !studentDTO.getEmail().equals(student.getEmail())) {//yeni email db de var mı?
		throw new ConflictException("email is already exist");//yeni diye girdiği email eskiden var mıydı?
		
	}
	student.setName(studentDTO.getFirstName());
	student.setLastName(studentDTO.getLastName());
	student.setEmail(studentDTO.getEmail());
	student.setGrade(studentDTO.getGrade());
	student.setPhoneNumber(studentDTO.getPhoneNumber());
	studentRepository.save(student);
	
	}

	//pageable
	public Page<Student> getAllWithPage(Pageable pageable) {
		
		return studentRepository.findAll(pageable);
	}


	public List<Student> findStudent(String lastName) {
		
		return studentRepository.findByLastName(lastName);
	}


	public List<Student> findAllEqualsGrade(Integer grade) {
		
		return studentRepository.findAllEqualsGrade(grade);
	}


	public StudentDTO findStudentDTObyId(Long id) {
		
		
		return studentRepository.findStudentDTOById(id).orElseThrow(()->new ResourceNotFoundException("Student not found wit id: "+id));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
