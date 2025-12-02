package com.projetos.manutencao.ativos.DTO;

import com.projetos.manutencao.ativos.model.Medidor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EquipamentoDTO {

    @NotBlank(message = "O código é obrigatório")
    private String codigo;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @NotBlank(message = "O tipo é obrigatório")
    private String tipo;

    @NotBlank(message = "A localização é obrigatória")
    private String localizacao;

    @NotBlank(message = "O número de série é obrigatório")
    @Size(max = 50, message = "O número de série deve ter no máximo 100 caracteres")
    private String numeroSerie;

    @NotBlank(message = "O fabricante é obrigatório")
    private String fabricante;

    @NotBlank(message = "O modelo é obrigatório")
    private String modelo;

    @PastOrPresent(message = "A data de instalação não pode ser no futuro")
    private Instant dataInstalacao;

    @PastOrPresent(message = "A data da última manutenção não pode ser no futuro")
    private Instant dataUltimaManutencao;

    @NotBlank(message = "O status operacional é obrigatório")
    private String statusOperacional;

    private String criticidadeID;

    private Map<String, Object> parametrosOperacionais;

    private List<String> medidorIds = new ArrayList<>();

    private List<@NotBlank(message = "O nome do documento não pode estar vazio") String> documentosAnexados;

    @Pattern(
            regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$",
            message = "A URL da imagem deve ser válida"
    )
    private String imagemURL;

    private String parentID;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
