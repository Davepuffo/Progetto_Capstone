package com.progetto.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progetto.model.Fattura;
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
	
	public Fattura create (FatturaDTO s) {
		Fattura f = new Fattura();
		f.setData(LocalDate.now());
		f.setOrdine(repoOrdine.findById(s.getId_ordine()).get());
		return repo.save(f);
	}
	
	public Fattura update (Long id, Fattura s) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Fattura non esistente!!");
		}
		return repo.save(s);
	}
	
	public String delete (Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Fattura non esistente!!");
		}
		repo.deleteById(id);
		return "Fattura eliminata!!";
	}
}
