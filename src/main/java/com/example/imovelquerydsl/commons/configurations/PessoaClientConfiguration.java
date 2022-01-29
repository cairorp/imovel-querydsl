package com.example.imovelquerydsl.commons.configurations;

import com.example.imovelquerydsl.commons.interceptors.PessoaApiInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class PessoaClientConfiguration {

    @Bean
    public RequestInterceptor pessoaApiRequestInterceptor() {
        return new PessoaApiInterceptor();
    }
}
