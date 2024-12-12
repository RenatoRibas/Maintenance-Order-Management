package com.OrdemManager.service;

import com.OrdemManager.exception.EntityNotFoundException;
import com.OrdemManager.model.OrdemManutencao;
import com.OrdemManager.dto.OrdemManutencaoResponseDTO;
import com.OrdemManager.repository.OrdemManutencaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemManutencaoService {

    private final OrdemManutencaoRepository repository;

    public OrdemManutencaoService(OrdemManutencaoRepository repository) {
        this.repository = repository;
    }

    // Método para criar uma nova ordem de manutenção
    public OrdemManutencao criarOrdem(OrdemManutencao ordem) {
        // Validações básicas para equipamento e usuário
        if (ordem.getEquipamento() == null || ordem.getEquipamento().getId() == null) {
            throw new IllegalArgumentException("Equipamento associado não pode ser nulo.");
        }
        if (ordem.getUsuario() == null || ordem.getUsuario().getId() == null) {
            throw new IllegalArgumentException("Usuário responsável não pode ser nulo.");
        }
        return repository.save(ordem);
    }

    // Método para listar todas as ordens de manutenção
    public List<OrdemManutencao> listarOrdens() {
        return repository.findAll();
    }

    // Método para buscar uma ordem de manutenção por ID
    public OrdemManutencao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de manutenção não encontrada com o ID: " + id));
    }

    // Método para atualizar uma ordem de manutenção
    public OrdemManutencao atualizarOrdem(Long id, OrdemManutencao ordemAtualizada) {
        OrdemManutencao ordemExistente = buscarPorId(id);

        // Atualizando somente os campos fornecidos
        if (ordemAtualizada.getDescricao() != null) {
            ordemExistente.setDescricao(ordemAtualizada.getDescricao());
        }
        if (ordemAtualizada.getStatus() != null &&
                (ordemAtualizada.getStatus().equals("Aberta") || ordemAtualizada.getStatus().equals("Finalizada"))) {
            ordemExistente.setStatus(ordemAtualizada.getStatus());
        } else if (ordemAtualizada.getStatus() != null) {
            throw new IllegalArgumentException("Status inválido. Use 'Aberta' ou 'Finalizada'.");
        }
        if (ordemAtualizada.getPrioridade() != null) {
            ordemExistente.setPrioridade(ordemAtualizada.getPrioridade());
        }
        if (ordemAtualizada.getDataConclusao() != null) {
            ordemExistente.setDataConclusao(ordemAtualizada.getDataConclusao());
        }
        if (ordemAtualizada.getEquipamento() != null && ordemAtualizada.getEquipamento().getId() != null) {
            ordemExistente.setEquipamento(ordemAtualizada.getEquipamento());
        }
        if (ordemAtualizada.getUsuario() != null && ordemAtualizada.getUsuario().getId() != null) {
            ordemExistente.setUsuario(ordemAtualizada.getUsuario());
        }

        return repository.save(ordemExistente);
    }

    // Método para excluir uma ordem de manutenção por ID
    public void excluirOrdem(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Ordem de manutenção não encontrada com o ID: " + id);
        }
        repository.deleteById(id);
    }

    // Método para converter uma entidade para um DTO
    public OrdemManutencaoResponseDTO converterParaDTO(OrdemManutencao ordem) {
        OrdemManutencaoResponseDTO dto = new OrdemManutencaoResponseDTO();
        dto.setId(ordem.getId());
        dto.setDescricao(ordem.getDescricao());
        dto.setPrioridade(ordem.getPrioridade());
        dto.setStatus(ordem.getStatus());
        dto.setEquipamentoDescricao(
                ordem.getEquipamento() != null ? ordem.getEquipamento().getDescricao() : "Equipamento não definido"
        );
        dto.setUsuarioNome(
                ordem.getUsuario() != null ? ordem.getUsuario().getNome() : "Usuário não definido"
        );
        return dto;
    }
}
