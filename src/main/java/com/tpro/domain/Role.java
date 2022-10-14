package com.tpro.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tpro.domain.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_role")
@Entity

public class Role {
	//roller sabitse değişmeyecekse ENUM yapabiliriz
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)//enumları int olarak değil de ismi ile çağırmak için string ile yani
	@Column(length = 30,nullable = false)
	private UserRole name;
	
	@Override
	public String toString() {
		
		return "Role[name="+name+"]";
	}
	
						
}
