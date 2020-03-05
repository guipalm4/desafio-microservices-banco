package com.guiPalma.desafio.cadastropessoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

import com.guiPalma.desafio.core.property.JwtConfiguration;

@SpringBootApplication
@ComponentScan("com.guiPalma.desafio")
@EnableConfigurationProperties(value = JwtConfiguration.class)
@EnableHystrix
@EnableCircuitBreaker
public class CadastroPessoaApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CadastroPessoaApplication.class, args);
	}

}
