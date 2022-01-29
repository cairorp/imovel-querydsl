package com.example.imovelquerydsl.imovel;

import com.example.imovelquerydsl.commons.api.clients.pessoa.PessoaClient;
import com.example.imovelquerydsl.imovel.web.ImovelCriterio;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ImovelService {

    private final ImovelRepository repository;
    private final PessoaClient pessoaClient;

    public ImovelService(ImovelRepository repository, PessoaClient pessoaClient) {
        this.repository = repository;
        this.pessoaClient = pessoaClient;
    }

    public Page<Imovel> buscarTodos(ImovelCriterio criterio) {
        BooleanBuilder builder = new BooleanBuilder();
        if (criterio.getId() != null) {
            builder.and(QImovel.imovel.id.eq(criterio.getId()));
        }
        if (criterio.getAlocado() != null) {
            builder.and(QImovel.imovel.alocado.eq(criterio.getAlocado()));
        }
        if (criterio.getQuantidadeQuartos() != null) {
            builder.and(QImovel.imovel.quantidadeQuartos.eq(criterio.getQuantidadeQuartos()));
        }
        if (criterio.getIdResponsavel() != null) {
            builder.and(QImovel.imovel.idPessoa.eq(criterio.getIdResponsavel()));
        }

        var predicate = builder.getValue();

        return Objects.nonNull(predicate) ?
                this.repository.findAll(predicate, PageRequest.of(criterio.getPageNumber(),
                        criterio.getPageSize(), Sort.by(criterio.getDirection(), criterio.getSortField()))) :
                this.repository.findAll(PageRequest.of(criterio.getPageNumber(), criterio.getPageSize(),
                        Sort.by(criterio.getDirection(), criterio.getSortField())));
    }

    public Optional<Imovel> buscarPorId(Long id) {
        return this.repository.findById(id);
    }

    public Imovel salvar(Imovel imovel) {
        var pessoa = this.pessoaClient.buscarPorId(imovel.getIdPessoa())
                .orElseThrow(() -> new IllegalStateException("Responsável não encontrado."));

        imovel.setIdPessoa(pessoa.getId());

        return this.repository.save(imovel);
    }
}
