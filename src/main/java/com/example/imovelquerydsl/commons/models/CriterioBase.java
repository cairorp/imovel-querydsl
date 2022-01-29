package com.example.imovelquerydsl.commons.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class CriterioBase {
    protected Integer pageNumber = 0;
    protected Integer pageSize = 10;
    protected String sortType = "ASC";
    protected String sortField = "id";

    public Sort.Direction getDirection(){
        if ("DESC".equals(sortType)) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }
}
