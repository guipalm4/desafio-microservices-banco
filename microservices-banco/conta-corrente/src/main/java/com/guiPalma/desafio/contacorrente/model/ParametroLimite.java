package com.guiPalma.desafio.contacorrente.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.guiPalma.desafio.core.model.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ParametroLimite implements AbstractEntity{
	
	private static final long serialVersionUID = 2746480190751960170L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
	private Long id;	
	private Integer scoreMinimo;
	private Integer scoreMaximo;
	private Double valorChequeEspecial;
	private Double valorCartaoCredito;
	
	

}
