package com.progetto.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progetto.security.entity.User;
import com.progetto.security.service.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 360000)
public class UserController {

	@Autowired private UserService service;

	@GetMapping("/{username}")
	public ResponseEntity<?> get(@PathVariable String username) {
		return ResponseEntity.ok(service.getByUsername(username));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User u) {
		return ResponseEntity.ok(service.update(id, u));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeById(@PathVariable Long id) {
		return ResponseEntity.ok(service.delete(id));
	}
	
}
