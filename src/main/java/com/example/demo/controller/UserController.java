package com.example.demo.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.object.Person;
import com.example.demo.service.FirebaseServices;

@RestController
public class UserController {
	
	@Autowired
	private FirebaseServices firebaseServices;
	
	@GetMapping("/getUserDetails")
	public Person getUserDetails(@RequestHeader String name) throws InterruptedException, ExecutionException {
		return firebaseServices.getUserDetails(name);
	}
	
	@PostMapping("/createUser")
	public String createNewUser(@RequestBody Person person) throws InterruptedException, ExecutionException {
		return firebaseServices.saveUserDetails(person);
	}
	
	@PutMapping("/updateUser")
	public String updateUser(@RequestBody Person person) {
		return "Updated user "+person.getName();
	}
	
	@DeleteMapping("/deleteUser")
	public String deleteUser(@RequestHeader String name) throws InterruptedException, ExecutionException {
		return firebaseServices.deleteUser(name);
	}

}
