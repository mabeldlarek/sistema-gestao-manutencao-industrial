package com.projetos.manutencao.ativos.DTO;

import com.projetos.manutencao.ativos.model.Medidor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;

import java.time.Instant;
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
    @Size(max = 50, message = "O número de série deve ter no máximo 50 caracteres")
    private String numeroSerie;

    @NotBlank(message = "O fabricante é obrigatório")
    private String fabricante;

    private String modelo;

    @PastOrPresent(message = "A data de instalação não pode ser no futuro")
    private Instant dataInstalacao;

    @PastOrPresent(message = "A data da última manutenção não pode ser no futuro")
    private Instant dataUltimaManutencao;

    @NotBlank(message = "O status operacional é obrigatório")
    private String statusOperacional;

    @NotBlank(message = "A criticidade é obrigatória")
    private String criticidadeID;

    private Map<String, Object> parametrosOperacionais;

    private List<@Valid Medidor> medidores;

    private List<@NotBlank(message = "O nome do documento não pode estar vazio") String> documentosAnexados;

    @Pattern(
            regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$",
            message = "A URL da imagem deve ser válida"
    )
    private String imagemURL;

    private String parentID;

}
