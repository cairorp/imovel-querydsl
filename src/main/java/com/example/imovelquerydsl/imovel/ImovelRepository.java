package com.example.imovelquerydsl.imovel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ImovelRepository extends JpaRepository<Imovel, Long>, QuerydslPredicateExecutor<Imovel> {
}
