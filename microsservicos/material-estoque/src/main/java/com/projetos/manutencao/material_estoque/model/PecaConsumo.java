package com.projetos.manutencao.material_estoque.model;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PecaConsumo {
    @Id
    private UUID id;
    private UUID ordemManutencaoID;
    private UUID pecaID;
    private Double quantidade;
    private Date dataConsumo;
    private Double custoTotalConsumo;
}
