package com.tpro.domain;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter//obj1.get dediğimizde getircek bize
@Setter
@AllArgsConstructor//tüm paramaetreli constructurları oluşturdu
@NoArgsConstructor//boş constructor
@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; // wrapper class kullanmamızın sebebi , eğer değer atanmazsa null olarak tanımlansın, 
	//int olarak tanımlasaydık default olarak 0 değeri verilecekti
	@NotNull(message = "First name can not be null")//ilk kontrol burada yapılıyor..controllere gelmeden, apide kontrol ediyo
	@NotBlank(message = "last name can not be white space")
	@Size(min = 2,max = 25,message = "First name '${validatedValue}' must be between {min} and {max} long")//kullanıcının girmiş old. değeri getirir
	@Column(nullable = false, length = 25)//burası hibernate tarafında kontrol.
	private String name ;
	@Column(nullable = false, length = 25)
	private String lastName;
	@Column
	private Integer grade;
	@Column(nullable = false,length = 50,unique = true)
	@Email(message = "Provide valid email")//xxx@yy.com
	private String email;
	@Column
	private String phoneNumber;
	
	private LocalDateTime createDate = LocalDateTime.now();	
	
	
}
