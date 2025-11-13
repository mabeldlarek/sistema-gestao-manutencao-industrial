package com.projetos.manutencao.material_estoque.model;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PecaConsumo {
    @Id
    private UUID id;
    private UUID ordemManutencaoID;
    private UUID pecaID;
    private Double quantidade;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataConsumo;
    private Double custoTotalConsumo;

    @PrePersist
    protected void onCreate() {
        this.dataConsumo = new Date();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOrdemManutencaoID() {
        return ordemManutencaoID;
    }

    public void setOrdemManutencaoID(UUID ordemManutencaoID) {
        this.ordemManutencaoID = ordemManutencaoID;
    }

    public UUID getPecaID() {
        return pecaID;
    }

    public void setPecaID(UUID pecaID) {
        this.pecaID = pecaID;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataConsumo() {
        return dataConsumo;
    }

    public void setDataConsumo(Date dataConsumo) {
        this.dataConsumo = dataConsumo;
    }

    public Double getCustoTotalConsumo() {
        return custoTotalConsumo;
    }

    public void setCustoTotalConsumo(Double custoTotalConsumo) {
        this.custoTotalConsumo = custoTotalConsumo;
    }
}
