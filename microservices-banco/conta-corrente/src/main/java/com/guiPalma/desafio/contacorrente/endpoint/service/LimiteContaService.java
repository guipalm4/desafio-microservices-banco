package com.guiPalma.desafio.contacorrente.endpoint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guiPalma.desafio.contacorrente.model.ContaCorrente;
import com.guiPalma.desafio.contacorrente.model.LimiteConta;
import com.guiPalma.desafio.contacorrente.model.ParametroLimite;
import com.guiPalma.desafio.contacorrente.repository.LimiteContaRepository;
import com.guiPalma.desafio.contacorrente.repository.ParametroLimiteRepository;
import com.guiPalma.desafio.core.exceptions.ServiceErrorException;
import com.guiPalma.desafio.core.responses.CadastroPessoaResponse;
import com.guiPalma.desafio.core.util.Validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LimiteContaService {

	private final LimiteContaRepository limiteContaRepository;
	private final ParametroLimiteRepository parametroLimiteRepository;

	public LimiteConta cadastrarLimite(CadastroPessoaResponse cadastroPessoaResponse, ContaCorrente conta) {

		if (Validator.has(conta)) {
			if (Validator.has(cadastroPessoaResponse.getScorePessoa())) {
				var score = cadastroPessoaResponse.getScorePessoa();
				LimiteConta limite = classificarValor(score);
				limite.setConta(conta);
				return limiteContaRepository.save(limite);
			}
			throw new ServiceErrorException("Score: null");
		}
		throw new ServiceErrorException("ContaCorrente: null");
	}

	private LimiteConta classificarValor(Integer score) {

		ParametroLimite parametroLimite = parametroLimiteRepository.findByRangeScore(score);

		return LimiteConta.builder().id(null).valorCartaoCredito(parametroLimite.getValorCartaoCredito())
				.valorChequeEspecial(parametroLimite.getValorChequeEspecial()).build();
	}

}
