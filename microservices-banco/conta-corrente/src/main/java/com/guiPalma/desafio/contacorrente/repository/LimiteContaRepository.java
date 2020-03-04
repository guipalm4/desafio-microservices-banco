package com.guiPalma.desafio.contacorrente.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.guiPalma.desafio.contacorrente.model.LimiteConta;

public interface LimiteContaRepository extends PagingAndSortingRepository<LimiteConta, Long>{

}
