package com.OrdemManager.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdemManutencaoDTO {
    @NotNull(message = "A descrição é obrigatória")
    private String descricao;

    @NotNull(message = "A prioridade é obrigatória")
    private String prioridade;

    @NotNull(message = "O status é obrigatório")
    private String status;

    @NotNull(message = "O ID do equipamento é obrigatório")
    private Long equipamentoId;

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long usuarioId;
}
