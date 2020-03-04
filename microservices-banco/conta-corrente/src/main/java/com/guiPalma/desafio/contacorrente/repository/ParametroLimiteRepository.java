package com.guiPalma.desafio.contacorrente.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.guiPalma.desafio.contacorrente.model.ParametroLimite;

public interface ParametroLimiteRepository extends PagingAndSortingRepository<ParametroLimite, Long>{

	@Query("select pm from ParametroLimite pm where pm.scoreMaximo >= ?1 and pm.scoreMinimo <= ?1")
	public ParametroLimite findByRangeScore(Integer score);
}
