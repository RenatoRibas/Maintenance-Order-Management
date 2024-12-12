package com.OrdemManager.controller;

import com.OrdemManager.model.OrdemManutencao;
import com.OrdemManager.service.OrdemManutencaoService;
import com.OrdemManager.dto.OrdemManutencaoDTO;
import com.OrdemManager.dto.OrdemManutencaoResponseDTO;
import org.springframework.web.bind.annotation.*;
import com.OrdemManager.model.Equipamento;
import com.OrdemManager.model.Usuario;



import java.util.List;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/ordens")
public class OrdemManutencaoController {

    private final OrdemManutencaoService service;

    public OrdemManutencaoController(OrdemManutencaoService service) {
        this.service = service;
    }

    @PostMapping
    public OrdemManutencao criarOrdem(@Valid @RequestBody OrdemManutencaoDTO ordemDTO) {
        // Converte DTO para entidade
        OrdemManutencao ordem = new OrdemManutencao();
        ordem.setDescricao(ordemDTO.getDescricao());
        ordem.setPrioridade(ordemDTO.getPrioridade());
        ordem.setStatus(ordemDTO.getStatus());
        ordem.setEquipamento(new Equipamento());
        ordem.getEquipamento().setId(ordemDTO.getEquipamentoId());
        ordem.setUsuario(new Usuario());
        ordem.getUsuario().setId(ordemDTO.getUsuarioId());
        return service.criarOrdem(ordem);
    }

    @GetMapping
    public List<OrdemManutencaoResponseDTO> listarOrdens() {
        return service.listarOrdens().stream()
                .map(service::converterParaDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public OrdemManutencaoResponseDTO buscarOrdemPorId(@PathVariable Long id) {
        OrdemManutencao ordem = service.buscarPorId(id);
        return service.converterParaDTO(ordem);
    }

    @PutMapping("/{id}")
    public OrdemManutencao atualizarOrdem(@PathVariable Long id, @Valid @RequestBody OrdemManutencaoDTO ordemDTO) {
        OrdemManutencao ordemAtualizada = new OrdemManutencao();
        ordemAtualizada.setDescricao(ordemDTO.getDescricao());
        ordemAtualizada.setPrioridade(ordemDTO.getPrioridade());
        ordemAtualizada.setStatus(ordemDTO.getStatus());
        ordemAtualizada.setEquipamento(new Equipamento());
        ordemAtualizada.getEquipamento().setId(ordemDTO.getEquipamentoId());
        ordemAtualizada.setUsuario(new Usuario());
        ordemAtualizada.getUsuario().setId(ordemDTO.getUsuarioId());
        return service.atualizarOrdem(id, ordemAtualizada);
    }


    @DeleteMapping("/{id}")
    public void excluirOrdem(@PathVariable Long id) {
        service.excluirOrdem(id);
    }
}
