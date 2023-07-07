package com.progetto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progetto.interfaces.TipoAnimale;
import com.progetto.model.Articolo;
import com.progetto.repository.ArticoloRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ArticoloService {
	
@Autowired ArticoloRepository repo;
	
	
	public List<Articolo> get(String type, Long id) {
		switch (type) {
		case "cane":
			TipoAnimale tipo = TipoAnimale.valueOf(type.toUpperCase());
			if (repo.findByAnimale(tipo).isEmpty()) {
				throw new EntityNotFoundException("Nessun risultato trovato!");
			} else {
				return repo.findByAnimale(tipo);
			}
		case "gatto":
			TipoAnimale tipo2 = TipoAnimale.valueOf(type.toUpperCase());
			if (repo.findByAnimale(tipo2).isEmpty()) {
				throw new EntityNotFoundException("Nessun risultato trovato!");
			} else {
				return repo.findByAnimale(tipo2);
			}
		case "pesci":
			TipoAnimale tipo3 = TipoAnimale.valueOf(type.toUpperCase());
			if (repo.findByAnimale(tipo3).isEmpty()) {
				throw new EntityNotFoundException("Nessun risultato trovato!");
			} else {
				return repo.findByAnimale(tipo3);
			}
		case "cerca":
			if (repo.findByNome(id.toString()).isEmpty()) {
				throw new EntityNotFoundException("Nessun risultato trovato!");
			} else {
				return repo.findByNome(id.toString());
			}
		case "id":
			List<Articolo> l = new ArrayList<>();
			l.add(repo.findById(id).get());
			return l;
		default:
			return repo.findAll();
		}
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
