package com.guiPalma.desafio.contacorrente.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.guiPalma.desafio.contacorrente.endpoint.service.DbServicePopulaParametros;

@Configuration
public class ParametroLimiteConfig {
	
	@Autowired
	private DbServicePopulaParametros dbServicePopulaParametros;
	
	@Bean
	public boolean instantiateDataBaseParams() {
		dbServicePopulaParametros.instantiateTestDatabase();
		return true;
	}

}
