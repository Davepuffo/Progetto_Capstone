package com.progetto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progetto.model.Fattura;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {

}
