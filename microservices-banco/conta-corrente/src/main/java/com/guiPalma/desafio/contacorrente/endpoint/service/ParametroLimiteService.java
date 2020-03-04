package com.guiPalma.desafio.contacorrente.endpoint.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.guiPalma.desafio.contacorrente.dto.ParametroLimiteDto;
import com.guiPalma.desafio.contacorrente.model.ParametroLimite;
import com.guiPalma.desafio.contacorrente.repository.ParametroLimiteRepository;
import com.guiPalma.desafio.core.exceptions.ObjectNotFoundException;
import com.guiPalma.desafio.core.exceptions.ServiceErrorException;
import com.guiPalma.desafio.core.util.Validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ParametroLimiteService {
	
	private final ParametroLimiteRepository parametroLimiteRepository;
	
	 public Iterable<ParametroLimite> list(Pageable pageable) {
	        log.info("Listing all courses");
	        return parametroLimiteRepository.findAll(pageable);
	    }

	public ParametroLimite atualizarFaixaLimite(ParametroLimiteDto paramDto) {
		
		if(validarInput(paramDto) ) {			
			Optional<ParametroLimite> param = parametroLimiteRepository.findById(paramDto.getId().longValue());			
			if(param.isPresent()) {
				ParametroLimite paramLimite = param.get();
				paramLimite.setValorCartaoCredito(paramDto.getValorCartaoCredito());
				paramLimite.setValorChequeEspecial(paramDto.getValorChequeEspecial());
				return parametroLimiteRepository.save(paramLimite);
			}throw new ObjectNotFoundException("Parametro não encontrado.");	
		}throw new ServiceErrorException("Informaçao invalida.");
	}

	private boolean validarInput(ParametroLimiteDto paramDto) {
		return Validator.has(paramDto.getId()) && Validator.has(paramDto.getValorCartaoCredito()) && Validator.has(paramDto.getValorChequeEspecial());
	}

	
}
