package com.guiPalma.desafio.auth.docs;

import org.springframework.context.annotation.Configuration;

import com.guiPalma.desafio.core.docs.BaseSwaggerConfig;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    public SwaggerConfig() {
        super("com.guiPalma.desafio.auth.endpoint.controller");
    }
}