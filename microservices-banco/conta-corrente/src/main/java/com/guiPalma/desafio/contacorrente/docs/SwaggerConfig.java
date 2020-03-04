package com.guiPalma.desafio.contacorrente.docs;

import org.springframework.context.annotation.Configuration;

import com.guiPalma.desafio.core.docs.BaseSwaggerConfig;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    public SwaggerConfig() {
        super("com.guiPalma.desafio.contacorrente.endpoint.controller");
    }
}
