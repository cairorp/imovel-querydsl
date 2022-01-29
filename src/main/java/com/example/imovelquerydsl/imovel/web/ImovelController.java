package com.example.imovelquerydsl.imovel.web;

import com.example.imovelquerydsl.commons.api.clients.pessoa.PessoaClient;
import com.example.imovelquerydsl.commons.api.clients.pessoa.PessoaDTO;
import com.example.imovelquerydsl.commons.models.enums.BooleanChar;
import com.example.imovelquerydsl.imovel.Imovel;
import com.example.imovelquerydsl.imovel.ImovelService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/imoveis")
public class ImovelController extends ImovelAbstractSupport {

    private final ImovelService service;
    private final PessoaClient pessoaClient;

    public ImovelController(ImovelService service,
                            PessoaClient pessoaClient) {
        this.service = service;
        this.pessoaClient = pessoaClient;
    }

    @GetMapping
    public Page<ImovelResponseDTO> buscarTodos(ImovelCriterio criterio) {
        return this.buildPageResponse(this.service.buscarTodos(criterio));
    }

    @GetMapping("/{id}")
    public ImovelResponseDTO buscarPorId(@PathVariable Long id) {
        return this.buildImovelResponse(this.service.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado.")));
    }

    @PostMapping
    public ImovelResponseDTO criar(@RequestBody @Valid ImovelRequestDTO imovel) {
        return this.buildImovelResponse(this.service.salvar(this.buildImovel(imovel)));
    }

    @PutMapping("/{id}")
    public ImovelResponseDTO atualizar(@PathVariable Long id,
                                       @RequestBody ImovelRequestDTO request) {
        var imovel = this.service.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado"));
        imovel.setIdPessoa(request.getIdResponsavel());
        imovel.setQuantidadeQuartos(request.getQuantidadeQuartos());

        return this.buildImovelResponse(this.service.salvar(imovel));
    }

    @PatchMapping("/{id}/locacao/{status}")
    public ImovelResponseDTO atualizarStatusAlocacao(@PathVariable Long id, @PathVariable BooleanChar status) {
        var imovel = this.service.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado"));

        imovel.setAlocado(status.getLocado());

        return this.buildImovelResponse(this.service.salvar(imovel));
    }

    private Imovel buildImovel(ImovelRequestDTO imovel) {
        return Imovel.builder()
                .alocado(false)
                .quantidadeQuartos(imovel.getQuantidadeQuartos())
                .idPessoa(imovel.getIdResponsavel())
                .build();
    }

    private Page<ImovelResponseDTO> buildPageResponse(Page<Imovel> pagina) {
        return pagina.map(this::buildImovelResponse);
    }

    private ImovelResponseDTO buildImovelResponse(Imovel imovel) {
        return ImovelResponseDTO
                .builder()
                .id(imovel.getId())
                .alocado(imovel.getAlocado())
                .quantidadeQuartos(imovel.getQuantidadeQuartos())
                .responsavel(this.obterPessoaPorId(imovel.getIdPessoa()))
                .build();
    }

    private PessoaDTO obterPessoaPorId(Long idPessoa) {
        return this.pessoaClient.buscarPorId(idPessoa)
                .orElseThrow(() -> new IllegalArgumentException("Responsável não encontrado."));
    }
}
