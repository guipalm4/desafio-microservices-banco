package com.guiPalma.desafio.core.responses;

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
public class CadastroPessoaResponse {
	
	private Long id;
	private String nome;
	private String numDocumento;
	private TipoPessoaEnum tipoPessoa;	
	private int scorePessoa;
	private ContaCorrenteResponse contaCorrenteResponse;
	private String msgErro;

}
