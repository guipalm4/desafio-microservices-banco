package com.guiPalma.desafio.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.guiPalma.desafio.core.service.DbConfigServervice;


@Configuration
public class DbCreateConfig {
	
		@Autowired
		private DbConfigServervice dbService;
		
		@Bean
		public boolean instantiateDataBase() {
			dbService.instantiateTestDatabase();
			return true;
		}

}
