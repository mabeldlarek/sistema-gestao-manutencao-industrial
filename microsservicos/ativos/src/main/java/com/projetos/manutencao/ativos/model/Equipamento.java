package com.projetos.manutencao.ativos.model;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "equipamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipamento {
    
    @Id
    private String id;

    private String codigo;
    private String nome;
    private String descricao;
    private String tipo;
    private String localizacao;
    private String numeroSerie;
    private String fabricante;
    private String modelo;
    private Instant dataInstalacao;
    private Instant dataUltimaManutencao;

    private String statusOperacional;

    private String criticidadeID;

    private Map<String, Object> parametrosOperacionais;

    private List<Medidor> medidores;

    private List<String> documentosAnexados;

    private String imagemURL;

    private String parentID;
}
