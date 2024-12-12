package com.OrdemManager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdemManutencaoResponseDTO {
    private Long id;
    private String descricao;
    private String prioridade;
    private String status;
    private String equipamentoDescricao; // Nome do equipamento
    private String usuarioNome; // Nome do usuário responsável
}
