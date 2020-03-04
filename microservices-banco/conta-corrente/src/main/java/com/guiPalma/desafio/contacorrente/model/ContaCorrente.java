package com.guiPalma.desafio.contacorrente.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

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
public class ContaCorrente implements AbstractEntity{
		
	private static final long serialVersionUID = -8273144767024952668L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
	private Long id;
	
	@NotNull(message = "O campo 'agencia' é obrigatório")
	@Column(nullable = false)
	private String agencia;
	
	@NotNull(message = "O campo 'numero' é obrigatório")
	@Column(nullable = false)
	private String numeroConta;
	
	@NotNull(message = "O campo 'tipo' é obrigatório")
	@Column(nullable = false)
	private char tipo;	
	
	@OneToOne
	@JoinColumn(name="id_limite_conta")
	private LimiteConta limite;
	
	@Column(nullable = false)
	private Long idPessoa;	
	
	@Override
	public Long getId() {
		return id;
	}
	
	
	//private Pessoa pessoa;
	
	

	
	
	

}
