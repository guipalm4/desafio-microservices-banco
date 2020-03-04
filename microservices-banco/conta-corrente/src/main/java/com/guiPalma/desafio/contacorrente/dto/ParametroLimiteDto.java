package com.guiPalma.desafio.contacorrente.dto;

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
public class ParametroLimiteDto {
	
	private Integer id;	
	private Double valorChequeEspecial;
	private Double valorCartaoCredito;

}
