package com.crudcode.crudcart.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.crudcode.crudcart.Entity.UserEntity;
import com.crudcode.crudcart.repository.CrudRepository;

@Service
public class CrudService {
	
	@Autowired
	private CrudRepository crudRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// GET ALL USERS
	public List<UserEntity> getAllUsers(){
		return crudRepository.findAll();
	}
	
	// CREATE NEW USER
	public UserEntity createNewUser(@RequestBody UserEntity user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return crudRepository.save(user);
	}
	
	// GET SINGLE USER
	public Optional<UserEntity> getUserById(@PathVariable Long id) {
		return crudRepository.findById(id);		
		
	}
	
	// UPDATE USER
	public UserEntity updateUser(@PathVariable Long id,@RequestBody UserEntity user) {
		UserEntity userData = crudRepository.findById(id).orElseThrow(null);
		userData.setName(user.getName());
		userData.setEmail(user.getEmail());
		return crudRepository.save(userData);
	}
	
	//DELETE USER
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		UserEntity userData = crudRepository.findById(id).orElseThrow(null);
		crudRepository.delete(userData);
		return ResponseEntity.ok().build();
	}


}
