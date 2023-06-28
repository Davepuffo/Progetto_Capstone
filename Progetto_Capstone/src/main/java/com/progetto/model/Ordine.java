package com.progetto.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.progetto.interfaces.StatoOrdine;
import com.progetto.security.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "ordini")
public class Ordine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column (nullable = false)
	private LocalDate data;
	@Enumerated(EnumType.STRING)
	@Column (nullable = false)
	private StatoOrdine stato;
	@Column
	private String pagamento;
	
	@ManyToOne
	private User user;
	
	@OneToMany
	List<Articolo> articoliOrdinati;
	
	@OneToOne (cascade = CascadeType.ALL)
	@JsonIgnore
	private Fattura fattura;

}
