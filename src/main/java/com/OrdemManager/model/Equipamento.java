package com.OrdemManager.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String tag; // Identificação única do equipamento

    @Column(unique = true, nullable = false, length = 50)
    private String descricao; // Descrição curta do equipamento
}
