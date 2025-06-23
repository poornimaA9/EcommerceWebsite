package com.crudcode.crudcart.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crudcode.crudcart.Entity.UserEntity;
import com.crudcode.crudcart.services.CrudService;

@RestController
public class CrudController {
	
	@Autowired
	private CrudService crudService;
	
	@GetMapping("/dashboard")
	public String dashBoard() {
		return "login Successfully";
	}
	
	@GetMapping("/api/users/get")
	public List<UserEntity> getAllUsers(){
		return crudService.getAllUsers();
	}
	
	@PostMapping("/set")
	public UserEntity createNewUser(@RequestBody UserEntity user) {
		return crudService.createNewUser(user);
	}
	
	@GetMapping("/{id}")
	public Optional<UserEntity> getUserById(@PathVariable Long id) {
		return crudService.getUserById(id);
	}
	
	@PutMapping("/{id}")
	public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
		return crudService.updateUser(id, user);
	}
	
	@DeleteMapping("{id}")
	public String deleteUser(@PathVariable Long id) {
		crudService.deleteUser(id);
	return "Deleted successfully";
	}
	
	
	
	

}
