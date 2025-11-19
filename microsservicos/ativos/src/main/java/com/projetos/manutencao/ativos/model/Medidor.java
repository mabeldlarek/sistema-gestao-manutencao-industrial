package com.projetos.manutencao.ativos.model;
import com.projetos.manutencao.ativos.enums.TipoMedidor;
import com.projetos.manutencao.ativos.enums.UnidadeMedida;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("medidores")
public class Medidor {

    @Id
    private String id;

    private String equipamentoId;
    private String codigo;
    private String nome;
    private TipoMedidor tipo;
    private UnidadeMedida unidade;

    private Double valorAtual;
    private Double valorMinimo;
    private Double valorMaximo;

}