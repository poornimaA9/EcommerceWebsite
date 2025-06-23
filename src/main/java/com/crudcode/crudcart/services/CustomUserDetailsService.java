package com.crudcode.crudcart.services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.crudcode.crudcart.Entity.UserEntity;
import com.crudcode.crudcart.repository.CrudRepository;
@Component
public class CustomUserDetailsService implements UserDetailsService {
    
	@Autowired
	private  CrudRepository crudRepository;
	  
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity userData = crudRepository.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException("User Not found"));
		
			
				return new User(userData.getUsername(),userData.getPassword(), Collections.singleton(new SimpleGrantedAuthority("USER_ROLE")));
	}
	

}
