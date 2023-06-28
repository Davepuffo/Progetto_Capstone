package com.progetto.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.progetto.model.Indirizzo;

@Repository
public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long> {
}
