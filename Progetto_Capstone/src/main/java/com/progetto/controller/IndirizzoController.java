package com.progetto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progetto.model.Indirizzo;
import com.progetto.payload.IndirizzoDTO;
import com.progetto.service.IndirizzoService;

@RestController
@RequestMapping("/indirizzo")
public class IndirizzoController {

	@Autowired private IndirizzoService service;

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		return ResponseEntity.ok(service.getById(id));
	}
	@PostMapping
	public ResponseEntity<?> save(@RequestBody IndirizzoDTO a) {
		return ResponseEntity.ok(service.create(a));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody IndirizzoDTO a) {
		return ResponseEntity.ok(service.update(id, a));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeById(@PathVariable Long id) {
		return ResponseEntity.ok(service.delete(id));
	}
}
