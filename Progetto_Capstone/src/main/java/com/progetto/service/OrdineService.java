package com.progetto.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progetto.model.Ordine;
import com.progetto.payload.OrdineDTO;
import com.progetto.repository.OrdineRepository;
import com.progetto.security.entity.User;
import com.progetto.security.repository.UserRepository;
import com.progetto.security.service.UserService;

import jakarta.persistence.EntityExistsException;

@Service
public class OrdineService {

	@Autowired OrdineRepository repo;
	@Autowired UserRepository repoUser;
	@Autowired UserService serviceUser;
	
	public List<Ordine> getAll(){
		return repo.findAll();
	}
	
	public Ordine getById (Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Ordine non esistente!!");
		}
		return repo.findById(id).get();
	}
	
	public Ordine create (OrdineDTO DTO) {
		Ordine o = new Ordine();
		o.setData(LocalDate.now());
		o.setStato(DTO.getStato());
		o.setArticoliOrdinati(DTO.getArticoliOrdinati());
		o.setUser(repoUser.findById(DTO.getId_user()).get());
		repo.save(o);
		User u = repoUser.findById(DTO.getId_user()).get();
		List<Ordine> ordiniUser = u.getOrdiniEffettuati();
		ordiniUser.add(o);
		serviceUser.update(u.getId(), u);
		return o;
	}
	
	public Ordine update (Long id, Ordine s) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Ordine non esistente!!");
		}
		return repo.save(s);
	}
	
	public String delete (Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Ordine non esistente!!");
		}
		repo.deleteById(id);
		return "Ordine eliminato!!";
	}

}
