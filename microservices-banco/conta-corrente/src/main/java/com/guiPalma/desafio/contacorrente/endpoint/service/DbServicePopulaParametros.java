package com.guiPalma.desafio.contacorrente.endpoint.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guiPalma.desafio.contacorrente.model.ParametroLimite;
import com.guiPalma.desafio.contacorrente.repository.ParametroLimiteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DbServicePopulaParametros {

	private final ParametroLimiteRepository parametroLimiteRepository;

	public void populaParams() {
		
		if(parametroLimiteRepository.count() < 4) {
		
		ParametroLimite p1 = new ParametroLimite(null, 0, 1, 0.0, 0.0);
		ParametroLimite p2 = new ParametroLimite(null, 2, 5, 200.0, 1000.0);
		ParametroLimite p3 = new ParametroLimite(null, 8, 6, 2000.0, 2000.0);
		ParametroLimite p4 = new ParametroLimite(null, 9, 9, 15000.0, 5000.0);		
		parametroLimiteRepository.saveAll(Arrays.asList(p1,p2,p3,p4));
		}
		
	}

	public void instantiateTestDatabase() {
		populaParams();
	}

}
