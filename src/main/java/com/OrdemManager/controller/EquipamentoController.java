package com.OrdemManager.controller;

import com.OrdemManager.dto.EquipamentoDTO;
import com.OrdemManager.dto.EquipamentoResponseDTO;
import com.OrdemManager.exception.EntityNotFoundException;
import com.OrdemManager.model.Equipamento;
import com.OrdemManager.service.EquipamentoService;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    private final EquipamentoService service;

    public EquipamentoController(EquipamentoService service) {
        this.service = service;
    }

    @PostMapping
    public EquipamentoResponseDTO criarEquipamento(@Valid @RequestBody EquipamentoDTO equipamentoDTO) {
        Equipamento equipamento = new Equipamento();
        equipamento.setDescricao(equipamentoDTO.getDescricao());
        equipamento.setTag(equipamentoDTO.getTag());

        Equipamento equipamentoCriado = service.criarEquipamento(equipamento);
        return service.converterParaDTO(equipamentoCriado);
    }

    @GetMapping
    public List<EquipamentoResponseDTO> listarEquipamentos() {
        return service.listarEquipamentos().stream()
                .map(service::converterParaDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public EquipamentoResponseDTO buscarEquipamentoPorId(@PathVariable Long id) {
        Equipamento equipamento = service.buscarPorId(id); // Lança EntityNotFoundException se não encontrar
        return service.converterParaDTO(equipamento);
    }


    @PutMapping("/{id}")
    public EquipamentoResponseDTO atualizarEquipamento(@PathVariable Long id, @Valid @RequestBody EquipamentoDTO equipamentoDTO) {
        Equipamento equipamentoAtualizado = new Equipamento();
        equipamentoAtualizado.setDescricao(equipamentoDTO.getDescricao());
        equipamentoAtualizado.setTag(equipamentoDTO.getTag());

        Equipamento equipamentoSalvo = service.atualizarEquipamento(id, equipamentoAtualizado);
        return service.converterParaDTO(equipamentoSalvo);
    }

    @DeleteMapping("/{id}")
    public void excluirEquipamento(@PathVariable Long id) {
        service.excluirEquipamento(id);
    }
}
