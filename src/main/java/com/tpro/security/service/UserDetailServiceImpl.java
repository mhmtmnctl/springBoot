package com.tpro.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tpro.domain.Role;
import com.tpro.domain.User;
import com.tpro.exception.ResourceNotFoundException;
import com.tpro.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//username unique yapmıştık. projede email üzerinden kontrol etcez
		
	User user=	userRepository.findByUserName(username).orElseThrow(()-> new ResourceNotFoundException("User Not Found username: "+username));//db den user aldık 
	
	if(user!=null) {//nullpointer exceptin gelme olasılığı düşük ama yine de kontrol ettik
		//kullanıcı adı, şifresi ve rolü lazım
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),buildGrantedAuthorities(user.getRoles()));
	}
	else {
		throw new UsernameNotFoundException("user name not found username:"+username);
	}
		
	
	}
	
	//rol özelliği security katmanında simple garanted authority yapısında olması gerekiyor.
	private static List<SimpleGrantedAuthority> buildGrantedAuthorities (final Set<Role> roles) {//statik yaptık başka yerde kullanmak için.
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for(Role role:roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName().name()));//getName.name yapmamızın sebebi role enum yapısında olduğu içindi.
		}
		return authorities;
		
	}

}
