package com.progetto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progetto.model.Articolo;
import com.progetto.repository.ArticoloRepository;

import jakarta.persistence.EntityExistsException;

@Service
public class ArticoloService {
	
@Autowired ArticoloRepository repo;
	
	public List<Articolo> getAll(){
		return repo.findAll();
	}
	
	public Articolo getById (Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Articolo non esistente!!");
		}
		return repo.findById(id).get();
	}
	
	public Articolo create (Articolo s) {
		if(repo.existsByNome(s.getNome())) {
			throw new EntityExistsException("Articolo già esistente!!");
		}
		return repo.save(s);
	}
	
	public Articolo update (Long id, Articolo s) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Articolo non esistente!!");
		}
		return repo.save(s);
	}
	
	public String delete (Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Articolo non esistente!!");
		}
		repo.deleteById(id);
		return "Articolo eliminato!!";
	}
}
