package com.OrdemManager.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipamentoDTO {
    @NotNull(message = "A descrição do equipamento é obrigatória")
    private String descricao;

    @NotNull(message = "A tag do equipamento é obrigatória")
    private String tag;
}
