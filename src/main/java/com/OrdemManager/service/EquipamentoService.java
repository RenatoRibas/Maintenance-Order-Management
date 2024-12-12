package com.OrdemManager.service;

import com.OrdemManager.dto.EquipamentoResponseDTO;
import com.OrdemManager.exception.EntityNotFoundException;
import com.OrdemManager.model.Equipamento;
import com.OrdemManager.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoService {

    private final EquipamentoRepository repository;

    public EquipamentoService(EquipamentoRepository repository) {
        this.repository = repository;
    }

    public Equipamento criarEquipamento(Equipamento equipamento) {
        return repository.save(equipamento);
    }

    public List<Equipamento> listarEquipamentos() {
        return repository.findAll();
    }

    public Equipamento buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipamento não encontrado com ID: " + id));
    }

    public Equipamento atualizarEquipamento(Long id, Equipamento equipamentoAtualizado) {
        Equipamento equipamentoExistente = buscarPorId(id);
        equipamentoExistente.setDescricao(equipamentoAtualizado.getDescricao());
        equipamentoExistente.setTag(equipamentoAtualizado.getTag());
        return repository.save(equipamentoExistente);
    }

    public void excluirEquipamento(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Equipamento não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    // Conversão para DTO
    public EquipamentoResponseDTO converterParaDTO(Equipamento equipamento) {
        EquipamentoResponseDTO dto = new EquipamentoResponseDTO();
        dto.setId(equipamento.getId());
        dto.setDescricao(equipamento.getDescricao());
        dto.setTag(equipamento.getTag());
        return dto;
    }
}
