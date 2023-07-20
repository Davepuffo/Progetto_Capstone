package com.progetto.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.progetto.interfaces.TipoAnimale;
import com.progetto.interfaces.TipoArticolo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
	@Column (nullable = false)
	private String foto;
	private String descrizione;
	@Column (nullable = false)
	private Double prezzo;
	@Column (nullable = false)
	private TipoArticolo tipo;
	@Column (nullable = false)
	private TipoAnimale animale;
	
	@ManyToMany (mappedBy = "articoliOrdinati")
	@JsonIgnore
	List<Ordine> ordini;
	

//	{
//		"nome" : "Monge All Breeds Adult Salmone e Riso",
//		"foto" : "https://arcaplanet.vtexassets.com/arquivos/ids/270797-1800-1800/Monge-All-Breeds-Adult-Salmone-e-Riso-12Kg.jpg?v=1774881573&quality=1&width=1800&height=1800",
//        "descrizione" : "da 10kg",
//		"prezzo": 48.9,
//        "tipo" : "ALIMENTAZIONE",
//        "animale" : "CANE"
//	}
	
	
}
