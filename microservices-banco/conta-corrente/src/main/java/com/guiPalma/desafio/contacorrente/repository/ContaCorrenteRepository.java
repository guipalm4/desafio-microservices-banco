package com.guiPalma.desafio.contacorrente.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.guiPalma.desafio.contacorrente.model.ContaCorrente;

public interface ContaCorrenteRepository extends PagingAndSortingRepository<ContaCorrente, Long>{

}
