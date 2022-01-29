package com.example.imovelquerydsl.commons.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class PessoaApiInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("authorization", "");
    }
}
