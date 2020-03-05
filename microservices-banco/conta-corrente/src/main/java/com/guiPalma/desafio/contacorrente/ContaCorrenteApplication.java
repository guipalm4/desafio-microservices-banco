package com.guiPalma.desafio.contacorrente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.guiPalma.desafio.core.property.JwtConfiguration;

@SpringBootApplication
@EntityScan({"com.guiPalma.desafio.core.model", "com.guiPalma.desafio.contacorrente.model"})
@EnableJpaRepositories({"com.guiPalma.desafio.core.repository", "com.guiPalma.desafio.contacorrente.repository"})
@EnableConfigurationProperties(value = JwtConfiguration.class)
@ComponentScan("com.guiPalma.desafio")
public class ContaCorrenteApplication {

	public static void main(String[] args) {
        SpringApplication.run(ContaCorrenteApplication.class, args);
    }

}
