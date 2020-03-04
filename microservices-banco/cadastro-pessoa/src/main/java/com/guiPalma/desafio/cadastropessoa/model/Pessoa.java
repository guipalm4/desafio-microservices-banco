package com.guiPalma.desafio.cadastropessoa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.guiPalma.desafio.cadastropessoa.dto.PessoaDto;
import com.guiPalma.desafio.core.enums.TipoPessoaEnum;
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
public class Pessoa implements AbstractEntity{
	
private static final long serialVersionUID = -8273144767024952668L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@EqualsAndHashCode.Include
private Long id;

@NotNull(message = "O campo 'nome' é obrigatório")
@Column(nullable = false)
private String nome;

@NotNull(message = "O campo 'numero do documento' é obrigatório")
@Column(nullable = false)
private String numDocumento;

@NotNull(message = "O campo 'tipo' é obrigatório")
@Column(nullable = false)
private int tipo;

@Column(nullable = true)
private int score;

@Override
public Long getId() {
	return this.id;
}
public TipoPessoaEnum getTipoPessoa() {
	return TipoPessoaEnum.fromCodigo(tipo);
}	

}
