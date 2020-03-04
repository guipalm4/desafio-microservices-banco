package com.guiPalma.desafio.core.responses;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContaCorrenteResponse {
	
	private Long id;
	private String numeroContaCorrente;
	private char tipo;	
	private HttpStatus status;
	private String msgErro;
	

}
