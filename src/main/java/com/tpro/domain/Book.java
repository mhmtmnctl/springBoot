package com.tpro.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@NoArgsConstructor
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonProperty("bookName")//sadece json çıktısında name yerine bookName gelmesi için.
	private String name;
	
	@JsonIgnore//burda stackoverflow olmaması için yaptık. student-book arası gidip gelmemesi için
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;//student ile maplemek için obje oluşturduk
	

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Student getStudent() {
		return student;
	}
	
	
}
