package com.guiPalma.desafio.cadastropessoa.endpoint.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guiPalma.desafio.cadastropessoa.dto.PessoaDto;
import com.guiPalma.desafio.cadastropessoa.endpoint.service.CadastroPessoaService;
import com.guiPalma.desafio.cadastropessoa.model.Pessoa;
import com.guiPalma.desafio.core.responses.CadastroPessoaResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/admin/cadastro-pessoa")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints para cadastro de pessoas")
public class CadastroPessoaController {

	private final CadastroPessoaService cadastroPessoaService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Lista todas pessoas cadastradas", response = Pessoa[].class)
	public ResponseEntity<Iterable<Pessoa>> list(Pageable pageable) {
		return new ResponseEntity<>(cadastroPessoaService.list(pageable), HttpStatus.OK);
	}
	
	@PostMapping(path = "/cadastrar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Cadastra uma pessoa", response = CadastroPessoaResponse.class)
	public CadastroPessoaResponse cadastrarPessoa(@Valid @RequestBody PessoaDto pessoa){
		return cadastroPessoaService.cadastrarPessoa(pessoa);
	}
	
	
}

