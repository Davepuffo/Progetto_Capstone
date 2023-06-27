package com.progetto.payload;

import java.util.List;

import com.progetto.interfaces.StatoOrdine;
import com.progetto.model.Articolo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdineDTO {

	private StatoOrdine stato = StatoOrdine.CREATO;
	private Long id_user;
	private List<Articolo> articoliOrdinati;
	
}
