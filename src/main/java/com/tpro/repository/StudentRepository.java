package com.tpro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tpro.domain.Student;
import com.tpro.dto.StudentDTO;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	boolean existsByEmail(String email);
	// SPring Data JPA içinde existById() var fakat Spring Data JPA bize sondaki eki istyediğimizdeğişken ismi ile 
	//değiştirmemize izin veriyor

	List<Student> findByLastName(String lastName);

	
	
	//jpql ile yazalım
	@Query("SELECT s FROM Student s WHERE s.grade=:pGrade")//p parametre anlamında
	List<Student> findAllEqualsGrade(@Param("pGrade") Integer grade);
	
	//native query ile (sql)
	@Query(value = "SELECT * FROM Student s WHERE s.grade=:pGrade",nativeQuery = true)
	List<Student> findAllEqualsGradeWithSQL(@Param("pGrade") Integer grade);

	//jpql
	@Query("SELECT new com.tpro.dto.StudentDTO(s) FROM Student s WHERE s.id=:id")//student tablosuna git id'si gelen id'ye eşit olan student pojo clasını getir.bunu da dto ile mapliyor
	
	Optional<StudentDTO> findStudentDTOById(@Param("id")Long id);//optional ya gelmezse ya olmazsa durumlarında kullanılıyor.
	

}
