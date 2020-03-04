package com.guiPalma.desafio.cadastropessoa.dto;



import com.guiPalma.desafio.core.enums.TipoPessoaEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PessoaDto {	
	
	private String nome;
	private String numDocumento;
	private int tipo;

	
}
