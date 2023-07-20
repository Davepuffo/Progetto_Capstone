package com.progetto.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progetto.interfaces.StatoOrdine;
import com.progetto.model.Fattura;
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
	@Autowired FatturaService serviceFattura;
	
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
	
	public String update (Long id, Ordine s) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Ordine non esistente!!");
		}
		
		//solo se c'è il pagamento alla consegna si può modificare		
		
		Ordine o = new Ordine();
		o.setId(id);
		o.setData(s.getData());
		o.setStato(s.getStato());
		o.setPagamento(s.getPagamento());
		o.setUser(s.getUser());
		o.setArticoliOrdinati(s.getArticoliOrdinati());
		
		if(s.getStato() == StatoOrdine.IN_TRANSITO) {
			return "L'ordine è stato evaso e non può essere modificato";
		} 
		
		if(s.getStato() == StatoOrdine.CONSEGNATO) {
			Fattura f = serviceFattura.create(id);
			f.setOrdine(o);
			o.setFattura(f);
		}
		repo.save(o);
		
		return "Ordine modificato con successo!";
		
	}
	
	public String delete (Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Ordine non esistente!!");
		}
		Ordine o = repo.findById(id).get();
		if(o.getStato() == StatoOrdine.CONSEGNATO || o.getStato() == StatoOrdine.IN_TRANSITO || o.getStato() == StatoOrdine.IN_PREPARAZIONE) {
			return "L'ordine è stato evaso e non può essere cancellato";
		} else {
			List<Ordine> lo = o.getUser().getOrdiniEffettuati();
			for(Ordine o1 : lo) {
				if(o1 == o) {
					lo.remove(o);
				}
			}
			repo.deleteById(id);
			return "Ordine eliminato con successo!";
		}
		
	}

}
