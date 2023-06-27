package com.progetto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "articoli")
public class Articolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (nullable = false, unique = true)
	private String nome;
	private String descrizione;
	@Column (nullable = false)
	private Double prezzo;

//	{
//		"nome" : "scatolsdfghjka",
//		"descrizione" : "da 10kg",
//		"prezzo": 178.09
//	}
	
	
}
