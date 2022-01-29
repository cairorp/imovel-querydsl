package com.example.imovelquerydsl.imovel.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ImovelRequestDTO {
    @Max(value = 20, message = "A quantidade máxima de quartos permitida é {value}")
    private Integer quantidadeQuartos;
    @NotNull(message = "É necessário informar o responsável pelo imóvel")
    private Long idResponsavel;
}
