package com.projetos.manutencao.ordem_manutencao.DTO;

import com.projetos.manutencao.ordem_manutencao.enums.StatusExecucao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecucaoOrdemDTO {

    @NotBlank(message = "O ID da ordem de manutenção é obrigatório.")
    private String ordemManutencaoID;

    @NotBlank(message = "O ID do executor é obrigatório.")
    private String executorID;

    @Size(max = 2000, message = "A descrição do trabalho não pode exceder 2000 caracteres.")
    private String descricaoTrabalhoExecutado;

    @Size(max = 1000, message = "As observações do executor não podem exceder 1000 caracteres.")
    private String observacoesExecutor;

    private StatusExecucao statusExecucao;

    private String assinaturaDigital;

    @NotNull(message = "A lista de itens do checklist não pode ser nula.")
    @Size(min = 1, message = "O checklist deve ter ao menos 1 item.")
    private List<ChecklistItemDTO> checklistItens;

    private List<String> fotosAntes;
    private List<String> fotosDepois;

    private List<PeriodoTrabalhoDTO> periodosDeTrabalho;


}