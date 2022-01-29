package com.example.imovelquerydsl.commons.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BooleanChar {
    SIM(true),
    NAO(false);

    @Getter
    final Boolean locado;
}
