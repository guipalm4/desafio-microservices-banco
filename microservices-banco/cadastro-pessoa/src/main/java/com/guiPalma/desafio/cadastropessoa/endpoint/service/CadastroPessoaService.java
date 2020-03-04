package com.guiPalma.desafio.cadastropessoa.endpoint.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guiPalma.desafio.cadastropessoa.dto.PessoaDto;
import com.guiPalma.desafio.cadastropessoa.endpoint.consumer.ContaCorrenteConsumer;
import com.guiPalma.desafio.cadastropessoa.model.Pessoa;
import com.guiPalma.desafio.cadastropessoa.repository.CadastroPessoaRepository;
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
public class CadastroPessoaService {

	private final CadastroPessoaRepository cadastroPessoaRepository;
	private final ContaCorrenteConsumer contaCorrenteConsumer;

	public Page<Pessoa> list(Pageable pageable) {
		log.info("Listandos todas as pessoa");
		return cadastroPessoaRepository.findAll(pageable);
	}

	public CadastroPessoaResponse cadastrarPessoa(PessoaDto pessoa) {
		try {
			Pessoa cadastroPessoa = cadastroPessoaRepository.findBynumDocumento(pessoa.getNumDocumento());

			if (Validator.has(cadastroPessoa)) {
				throw new ServiceErrorException("Numero documento ja cadastrado");
			}

			if (validarDocumento(pessoa)) {
				Pessoa pessoaCadastrada = insertPessoa(pessoa);
				ContaCorrenteResponse response = contaCorrenteConsumer.cadastrarContaCorrente(pessoaCadastrada);
				return setResponse(pessoaCadastrada, response);
			}
			throw new ServiceErrorException("Numero do documento invalido");
		} catch (Exception e) {
			throw new ServiceErrorException(e.getMessage());
		}
	}

	public CadastroPessoaResponse setResponse(Pessoa pessoaCadastrada, ContaCorrenteResponse contaCorrenteResponse) {

		return CadastroPessoaResponse.builder().id(pessoaCadastrada.getId()).nome(pessoaCadastrada.getNome())
				.tipoPessoa(pessoaCadastrada.getTipoPessoa()).numDocumento(pessoaCadastrada.getNumDocumento())
				.scorePessoa(pessoaCadastrada.getScore()).contaCorrenteResponse(contaCorrenteResponse).build();
	}

	private Pessoa insertPessoa(PessoaDto pessoaDto) {
		return cadastroPessoaRepository.save(setParamsPessoa(pessoaDto));
	}

	private Pessoa setParamsPessoa(PessoaDto pessoaDto) {

		Pessoa pessoa = new Pessoa();
		pessoa.setId(null);
		pessoa.setNome(pessoaDto.getNome());
		pessoa.setNumDocumento(pessoaDto.getNumDocumento());
		pessoa.setScore(definirScore());
		pessoa.setTipo(pessoaDto.getTipo());
		return pessoa;
	}

	public boolean validarDocumento(PessoaDto pessoa) {

		if (pessoa.getTipo() == TipoPessoaEnum.PESSOAFISICA.getCod()) {
			return pessoa.getNumDocumento().length() == 11;
		}
		return pessoa.getNumDocumento().length() == 14;
	}

	private int definirScore() {
		Random r = new Random();
		return r.nextInt((9 - 0) + 1);
	}

}
