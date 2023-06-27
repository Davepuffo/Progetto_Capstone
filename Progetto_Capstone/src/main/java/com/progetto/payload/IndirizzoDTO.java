package com.progetto.payload;


import com.progetto.interfaces.TipoIndirizzo;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class IndirizzoDTO {

	private String via;
	private String civico;
	private String citta;
	private String provincia;
	private TipoIndirizzo tipo;
	private String user;
}
