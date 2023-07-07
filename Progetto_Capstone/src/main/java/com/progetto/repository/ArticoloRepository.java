package com.progetto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.progetto.interfaces.TipoAnimale;
import com.progetto.model.Articolo;

@Repository
public interface ArticoloRepository extends JpaRepository<Articolo, Long> {

	@Query("SELECT a FROM Articolo a WHERE a.nome LIKE '%value1%' ")
	List<Articolo> findByNome (@Param("value1")String nome);
	
	List<Articolo> findByTipo (String tipo);
	
	List<Articolo> findByAnimale (TipoAnimale tipoAnimale);
	
	Boolean existsByNome(String nome);
}
