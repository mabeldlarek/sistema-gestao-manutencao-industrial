package com.projetos.manutencao.ativos.model;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
    @Indexed(unique = true)
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

    private List<String> medidorIds = new ArrayList<>();

    private List<String> documentosAnexados;

    private String imagemURL;

    private String path;

    private String parentID;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Instant getDataInstalacao() {
        return dataInstalacao;
    }

    public void setDataInstalacao(Instant dataInstalacao) {
        this.dataInstalacao = dataInstalacao;
    }

    public Instant getDataUltimaManutencao() {
        return dataUltimaManutencao;
    }

    public void setDataUltimaManutencao(Instant dataUltimaManutencao) {
        this.dataUltimaManutencao = dataUltimaManutencao;
    }

    public String getStatusOperacional() {
        return statusOperacional;
    }

    public void setStatusOperacional(String statusOperacional) {
        this.statusOperacional = statusOperacional;
    }

    public String getCriticidadeID() {
        return criticidadeID;
    }

    public void setCriticidadeID(String criticidadeID) {
        this.criticidadeID = criticidadeID;
    }

    public Map<String, Object> getParametrosOperacionais() {
        return parametrosOperacionais;
    }

    public void setParametrosOperacionais(Map<String, Object> parametrosOperacionais) {
        this.parametrosOperacionais = parametrosOperacionais;
    }

    public List<String> getMedidorIds() {
        return medidorIds;
    }

    public void setMedidorIds(List<String> medidorIds) {
        this.medidorIds = medidorIds;
    }

    public List<String> getDocumentosAnexados() {
        return documentosAnexados;
    }

    public void setDocumentosAnexados(List<String> documentosAnexados) {
        this.documentosAnexados = documentosAnexados;
    }

    public String getImagemURL() {
        return imagemURL;
    }

    public void setImagemURL(String imagemURL) {
        this.imagemURL = imagemURL;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }
}
