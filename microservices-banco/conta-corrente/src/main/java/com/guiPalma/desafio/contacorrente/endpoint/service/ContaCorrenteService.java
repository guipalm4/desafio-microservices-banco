package com.guiPalma.desafio.contacorrente.endpoint.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.guiPalma.desafio.contacorrente.config.ConfigSystemEnvironment;
import com.guiPalma.desafio.contacorrente.model.ContaCorrente;
import com.guiPalma.desafio.contacorrente.model.LimiteConta;
import com.guiPalma.desafio.contacorrente.repository.ContaCorrenteRepository;
import com.guiPalma.desafio.core.enums.TipoPessoaEnum;
import com.guiPalma.desafio.core.exceptions.ServiceErrorException;
import com.guiPalma.desafio.core.responses.CadastroPessoaResponse;
import com.guiPalma.desafio.core.responses.ContaCorrenteResponse;
import com.guiPalma.desafio.core.util.Validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContaCorrenteService {

	private final ContaCorrenteRepository contaCorrenteRepository;

	private final LimiteContaService limiteContaService;

	private final ConfigSystemEnvironment config;

	public Iterable<ContaCorrente> list(Pageable pageable) {
		log.info("Listing all courses");
		return contaCorrenteRepository.findAll(pageable);
	}

	public ContaCorrenteResponse criarContaCorrente(CadastroPessoaResponse pessoa) {
		if (Validator.has(pessoa)) {
			if (Validator.has(pessoa.getId())) {
				ContaCorrente contaCadastrada = contaCorrenteRepository.save(setParamsCadastroContaCorrente(pessoa));
				LimiteConta limite = cadastrarLimiteConta(pessoa, contaCadastrada);
				contaCadastrada.setLimite(limite);
				return setReponse(contaCorrenteRepository.save(contaCadastrada));
			}
			throw new ServiceErrorException("Falha no recebimento : API externa[Pessoa.id]");
		}
		throw new ServiceErrorException("Falha no recebimento : API externa[Pessoa]");
	}

	private LimiteConta cadastrarLimiteConta(CadastroPessoaResponse pessoa, ContaCorrente contaCadastrada) {
		return limiteContaService.cadastrarLimite(pessoa, contaCadastrada);
	}

	private String getConfigAgencia() {
		return config.getAgencia();
	}

	private ContaCorrente setParamsCadastroContaCorrente(CadastroPessoaResponse pessoa) {
		
		return ContaCorrente.builder()
							.id(null)
							.numeroConta(gerarNumeroConta())
							.agencia(getConfigAgencia())
							.tipo(classificarConta(pessoa))
							.idPessoa(pessoa.getId())
							.build();
	}
	
	private ContaCorrenteResponse setReponse(ContaCorrente contaCorrenteCadastrada) {
		return ContaCorrenteResponse.builder()
				.id(contaCorrenteCadastrada.getId())
				.numeroContaCorrente(contaCorrenteCadastrada.getNumeroConta())
				.tipo(contaCorrenteCadastrada.getTipo())
				.status(HttpStatus.CREATED)
				.build();    	
	}

	private char classificarConta(CadastroPessoaResponse pessoa) {

		if (pessoa.getTipoPessoa() == TipoPessoaEnum.PESSOAFISICA) {
			return 'C';
		}

		return 'E';
	}

	private String gerarNumeroConta() {

		var aux = new ArrayList<String>();
		var sb = new StringBuilder("");

		var range = IntStream.range(0, 9).boxed().collect(Collectors.toCollection(ArrayList::new));
		Collections.shuffle(range);
		range.subList(0, 6).forEach(n -> aux.add(n.toString()));

		aux.stream().forEach(sb::append);
		return sb.toString();
	}

}
