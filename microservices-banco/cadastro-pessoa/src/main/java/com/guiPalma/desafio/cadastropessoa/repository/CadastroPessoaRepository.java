package com.guiPalma.desafio.cadastropessoa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.guiPalma.desafio.cadastropessoa.model.Pessoa;

public interface CadastroPessoaRepository extends PagingAndSortingRepository<Pessoa, Long>{

public Pessoa findBynumDocumento(String numeroDocumento);


}
