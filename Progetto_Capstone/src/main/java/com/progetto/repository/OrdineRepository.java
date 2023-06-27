package com.progetto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progetto.model.Ordine;

public interface OrdineRepository extends JpaRepository<Ordine, Long> {

}
