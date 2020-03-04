package com.guiPalma.desafio.cadastropessoa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="myserver")
public class ConfigServersEnvironment {
	
	private String gatewayPath;
	private String contaCorrentePath;
	private String mainUrl;
	

}
