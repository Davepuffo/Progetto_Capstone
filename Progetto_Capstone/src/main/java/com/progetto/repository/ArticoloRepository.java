package com.progetto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.progetto.model.Articolo;

@Repository
public interface ArticoloRepository extends JpaRepository<Articolo, Long> {

	Optional<Articolo> findByNome (String nome);
	
	Boolean existsByNome(String nome);
}
