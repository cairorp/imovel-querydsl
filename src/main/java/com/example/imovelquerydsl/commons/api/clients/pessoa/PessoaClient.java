package com.example.imovelquerydsl.commons.api.clients.pessoa;

import com.example.imovelquerydsl.commons.configurations.PessoaClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(value = "pessoas", url = "${api.urls.pessoa}", decode404 = true,
        configuration = PessoaClientConfiguration.class)
public interface PessoaClient {

    @GetMapping("/pessoas/{id}")
    Optional<PessoaDTO> buscarPorId(@PathVariable Long id);
}
