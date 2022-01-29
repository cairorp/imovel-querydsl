package com.example.imovelquerydsl.imovel.web;

import com.example.imovelquerydsl.commons.models.CriterioBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImovelCriterio extends CriterioBase {
    private Long id;
    private Long idResponsavel;
    private Boolean alocado;
    private Integer quantidadeQuartos;
}
