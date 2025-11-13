package com.projetos.manutencao.material_estoque.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class PecaConsumoDTO {

    @NotNull(message = "O ID da ordem de manutenção é obrigatório.")
    private UUID ordemManutencaoID;

    @NotNull(message = "O ID da peça é obrigatório.")
    private UUID pecaID;

    @NotNull(message = "A quantidade consumida é obrigatória.")
    @DecimalMin(value = "0.01", message = "A quantidade consumida deve ser maior que zero.")
    private Double quantidade;

    @NotNull(message = "O custo total do consumo é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O custo total deve ser maior que zero.")
    private Double custoTotalConsumo;

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

    public Double getCustoTotalConsumo() {
        return custoTotalConsumo;
    }

    public void setCustoTotalConsumo(Double custoTotalConsumo) {
        this.custoTotalConsumo = custoTotalConsumo;
    }
}
