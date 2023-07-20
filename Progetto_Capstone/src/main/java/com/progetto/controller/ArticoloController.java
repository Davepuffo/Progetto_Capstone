package com.progetto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progetto.model.Articolo;
import com.progetto.service.ArticoloService;

@RestController
@RequestMapping("/catalogo/articolo")
@CrossOrigin(origins = "*", maxAge = 360000)
public class ArticoloController {

	@Autowired private ArticoloService service;

	@GetMapping("/{type}/{id}")
	public ResponseEntity<?> get(@PathVariable String type, @PathVariable String id) {
		return ResponseEntity.ok(service.get(type,id));
	}

	@PostMapping
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> save(@RequestBody Articolo a) {
		return ResponseEntity.ok(service.create(a));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Articolo a) {
		return ResponseEntity.ok(service.update(id, a));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> removeById(@PathVariable Long id) {
		return ResponseEntity.ok(service.delete(id));
	}
}
