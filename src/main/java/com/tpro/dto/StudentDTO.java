package com.tpro.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tpro.domain.Student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter//obj1.get dediğimizde getircek bize
@Setter
@AllArgsConstructor//tüm paramaetreli constructurları oluşturdu
@NoArgsConstructor//boş constructor
public class StudentDTO {

	
	private Long id; 
	
	@NotNull(message = "First name can not be null")
	@NotBlank(message = "last name can not be white space")
	@Size(min = 2,max = 25,message = "First name '${validatedValue}' must be between {min} and {max} long")
	
	private String firstName ;
	
	private String lastName;
	
	private Integer grade;
	
	@Email(message = "Provide valid email")
	private String email;
	
	private String phoneNumber;
	
	private LocalDateTime createDate = LocalDateTime.now();
	
	public StudentDTO(Student student) {
		this.id=student.getId();
		this.firstName=student.getName();
		this.lastName=student.getLastName();
		this.email=student.getEmail();
		this.grade=student.getGrade();
		this.phoneNumber=student.getPhoneNumber();
		this.createDate=student.getCreateDate();
	}

}
