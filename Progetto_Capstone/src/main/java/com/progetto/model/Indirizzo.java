package com.progetto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.progetto.interfaces.TipoIndirizzo;
import com.progetto.security.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "indirizzi")
public class Indirizzo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (nullable = false)
	private String via;
	private String civico;
	@Column (nullable = false)
	private String citta;
	@Column (nullable = false)
	private String provincia;
	@Enumerated(EnumType.STRING)
	@Column (nullable = false)
	private TipoIndirizzo tipo;
	
	@ManyToOne
	@JsonIgnore
	private User user;
}
