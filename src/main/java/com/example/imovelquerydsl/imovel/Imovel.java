package com.example.imovelquerydsl.imovel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "IMOVEL")
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDT_IMOVEL")
    private Long id;

    @Column(name = "ST_LOCACAO")
    private Boolean alocado;

    @Column(name = "QTD_QUARTO")
    private Integer quantidadeQuartos;

    @Column(name = "IDT_PESSOA")
    private Long idPessoa;

}
