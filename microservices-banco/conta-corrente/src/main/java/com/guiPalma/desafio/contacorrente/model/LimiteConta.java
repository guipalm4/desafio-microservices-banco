package com.guiPalma.desafio.contacorrente.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.guiPalma.desafio.core.enums.TipoLimiteEnum;
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

public class LimiteConta implements AbstractEntity{
	private static final long serialVersionUID = 4350572906737182585L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
	private Long id;	
		
	@Column(nullable = false)
	private Double valorChequeEspecial;
	
	@Column(nullable = false)
	private Double valorCartaoCredito;
	
	@OneToOne
	@JoinColumn(name = "id_conta_corrente")
	private ContaCorrente conta;
	
	@Override
	public Long getId() {		
		return id;
	} 

}
