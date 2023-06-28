package com.progetto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progetto.model.Indirizzo;
import com.progetto.payload.IndirizzoDTO;
import com.progetto.repository.IndirizzoRepository;
import com.progetto.security.entity.User;
import com.progetto.security.repository.UserRepository;
import com.progetto.security.service.UserService;

import jakarta.persistence.EntityExistsException;

@Service
public class IndirizzoService {
	
@Autowired IndirizzoRepository repo;

@Autowired UserRepository repoUser;
@Autowired UserService serviceUser;
	
	public List<Indirizzo> getAll(){
		return repo.findAll();
	}
	
	public Indirizzo getById (Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Indirizzo non esistente!!");
		}
		return repo.findById(id).get();
	}
	
	public Indirizzo create (IndirizzoDTO DTO) {
		User u = repoUser.findByUsername(DTO.getUser()).get();
		List<Indirizzo> indirizzoUtente = u.getIndirizziUtente();
		if (indirizzoUtente.size() > 4) {
			throw new EntityExistsException("Hai già 4 indirizzi registrati, modificane uno!");
		}
//		if(indirizzoUtente.size() == 2) {
//			throw new EntityExistsException("Hai già");
//		}
//		for (Indirizzo indirizzo : indirizzoUtente) {
//			Boolean flag = indirizzo.getTipo() == TipoIndirizzo.;
//			if (flag && i.size() == 2) {
//				throw new MyAPIException(HttpStatus.NOT_ACCEPTABLE,
//						"Hai inserito sede unica non puoi avere piu di 2 indirizzi");
//			}
//		}
		Indirizzo i = new Indirizzo();
		i.setCitta(DTO.getCitta());
		i.setCivico(DTO.getCivico());
		i.setProvincia(DTO.getProvincia());
		i.setTipo(DTO.getTipo());
		i.setUser(repoUser.findByUsername(DTO.getUser()).get());
		i.setVia(DTO.getVia());
		repo.save(i);
		indirizzoUtente.add(i);
		serviceUser.update(u.getId(), u);
		return i;
	}
	
	public Indirizzo update (Long id, IndirizzoDTO DTO) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Indirizzo non esistente!!");
		}
		Indirizzo i = repo.findById(id).get();
		i.setCitta(DTO.getCitta());
		i.setCivico(DTO.getCivico());
		i.setProvincia(DTO.getProvincia());
		i.setTipo(DTO.getTipo());
		i.setUser(repoUser.findByUsername(DTO.getUser()).get());
		i.setVia(DTO.getVia());
		return repo.save(i);
	}
	
	public String delete (Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Indirizzo non esistente!!");
		}
		repo.deleteById(id);
		return "Indirizzo eliminato!!";
	}

}
