package com.progetto.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progetto.interfaces.StatoOrdine;
import com.progetto.model.Fattura;
import com.progetto.model.Ordine;
import com.progetto.payload.FatturaDTO;
import com.progetto.repository.FatturaRepository;
import com.progetto.repository.OrdineRepository;

import jakarta.persistence.EntityExistsException;

@Service
public class FatturaService {
	
	@Autowired FatturaRepository repo;
	@Autowired OrdineRepository repoOrdine;
	
	public List<Fattura> getAll(){
		return repo.findAll();
	}
	
	public Fattura getById (Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Fattura non esistente!!");
		}
		return repo.findById(id).get();
	}
	
	public Fattura create (Long id) {
		Ordine o = repoOrdine.findById(id).get();
		Fattura f = new Fattura();
		f.setData(LocalDate.now());
		f.setOrdine(o);
		return repo.save(f);
	}
	
	public String delete (Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Fattura non esistente!!");
		}
		repo.deleteById(id);
		return "Fattura eliminata!!";
	}
}
