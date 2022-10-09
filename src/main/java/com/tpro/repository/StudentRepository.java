package com.tpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpro.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	boolean existsByEmail(String email);
	// SPring Data JPA içinde existById() var fakat Spring Data JPA bize sondaki eki istyediğimizdeğişken ismi ile 
	//değiştirmemize izin veriyor

}
