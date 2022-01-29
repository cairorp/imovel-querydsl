package com.example.imovelquerydsl.imovel.web;

import com.example.imovelquerydsl.commons.api.clients.pessoa.PessoaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImovelResponseDTO {
    private Long id;
    private boolean alocado;
    private Integer quantidadeQuartos;
    private PessoaDTO responsavel;
}
