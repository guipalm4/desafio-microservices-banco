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

import com.guiPalma.desafio.contacorrente.dto.ParametroLimiteDto;
import com.guiPalma.desafio.contacorrente.endpoint.service.ParametroLimiteService;
import com.guiPalma.desafio.contacorrente.model.ParametroLimite;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/admin/conta-corrente/parametrizacao")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints para parametrização de limite da conta corrente")
public class ParametroLimiteController {
	
	private final ParametroLimiteService parametrizacaoLimiteService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Lista todas os parametros de limite", response = ParametroLimite[].class)
    public ResponseEntity<Iterable<ParametroLimite>> list(Pageable pageable) {
        return new ResponseEntity<>(parametrizacaoLimiteService.list(pageable), HttpStatus.OK);
    }
    @PostMapping(path = "/limite/atualizar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Atualiza faixas de limite", response = ParametroLimite[].class)
    public ResponseEntity<ParametroLimite> atualizarFaixaLimite(@RequestBody ParametroLimiteDto paramDto){		
		return new ResponseEntity<>(parametrizacaoLimiteService.atualizarFaixaLimite(paramDto),HttpStatus.OK);
	}
    
   
}
