package com.guiPalma.desafio.contacorrente.endpoint.controller;

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

import com.guiPalma.desafio.contacorrente.endpoint.service.ContaCorrenteService;
import com.guiPalma.desafio.contacorrente.model.ContaCorrente;
import com.guiPalma.desafio.core.responses.CadastroPessoaResponse;
import com.guiPalma.desafio.core.responses.ContaCorrenteResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/admin/conta-corrente")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints para conta corrente")
public class ContaCorrenteController {
	
	private final ContaCorrenteService contaCorrenteService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Lista todas as contas-correntes cadastradas", response = ContaCorrente[].class)
    public ResponseEntity<Iterable<ContaCorrente>> list(Pageable pageable) {
        return new ResponseEntity<>(contaCorrenteService.list(pageable), HttpStatus.OK);
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Cadastra conta corrente a partir da API de cadastro de pessoas", response = ContaCorrenteResponse.class)
    public ContaCorrenteResponse cadastrarContaCorrente(@RequestBody CadastroPessoaResponse pessoa){		
    	return contaCorrenteService.criarContaCorrente(pessoa);		
	}
    
    

}
