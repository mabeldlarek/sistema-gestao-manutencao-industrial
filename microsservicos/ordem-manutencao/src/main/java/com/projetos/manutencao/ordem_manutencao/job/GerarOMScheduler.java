package com.projetos.manutencao.ordem_manutencao.job;

import com.projetos.manutencao.ordem_manutencao.DTO.MedidorDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.PlanoManutencaoDTO;
import com.projetos.manutencao.ordem_manutencao.enums.UnidadeFrequencia;
import com.projetos.manutencao.ordem_manutencao.feign.MedidorClient;
import com.projetos.manutencao.ordem_manutencao.model.PlanoManutencao;
import com.projetos.manutencao.ordem_manutencao.service.OrdemManutencaoService;
import com.projetos.manutencao.ordem_manutencao.service.PlanoManutencaoService;
import com.projetos.manutencao.ordem_manutencao.util.DateTimeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class GerarOMScheduler {

    @Autowired
    private PlanoManutencaoService planoService;

    @Autowired
    private OrdemManutencaoService omService;

    @Autowired
    private MedidorClient medidorClient;

    @Autowired
    private ModelMapper modelMapper;

    @Scheduled(fixedRate = 60000)
    public void verificarPlanos() {

        Date agora = new Date();

        List<PlanoManutencao> planos = planoService.buscarPlanosAgendados(agora);

        for (PlanoManutencao pm : planos) {
            if (pm.getGerarOMAutomatica()) {
                if (!pm.getDataGeracaoAutomaticaOM().after(agora))
                {
                    Date novaDataOm = calcularProximaDataGeracaoOmPreventiva(pm.getFrequenciaUnidade(), pm);
                    pm.setDataGeracaoAutomaticaOM(novaDataOm);
                    PlanoManutencaoDTO planoManutencaoDTO = modelMapper.map(pm, PlanoManutencaoDTO.class);

                    planoService.atualizarPlano(pm.getId(), planoManutencaoDTO);

                    omService.gerarOM(pm.getId());

                    return;
                }
            }

            if (pm.getGerarOMAutomaticaMedidor()) {
                String medidorId = pm.getMedidorId();

                if(isGerarOmCorretivaMedidor(pm.getFrequenciaValor(), medidorId)){
                    omService.gerarOM(pm.getId());
                    return;
                }

                if(isGerarOmPreditivaMedidor(pm.getFrequenciaValor(), medidorId)){
                    omService.gerarOM(pm.getId());
                    return;
                }

            }
        }
    }

    private Date calcularProximaDataGeracaoOmPreventiva(UnidadeFrequencia uf, PlanoManutencao pm) {
        return DateTimeUtils.calcularProximaData(
                pm.getDataGeracaoAutomaticaOM(),
                uf,
                pm.getFrequenciaValor().intValue()
        );
    }

    private boolean isGerarOmPreditivaMedidor(Double pmValorFrequencia, String horimetroId){
        MedidorDTO medidorDTO = medidorClient.buscar(horimetroId);

        Double horimetroValorAtual = medidorDTO.getValorAtual();

        if(horimetroValorAtual >= pmValorFrequencia){
            return true;
        }
        return false;
    }

    private boolean isGerarOmCorretivaMedidor(Double pmValorFrequencia, String horimetroId){
        MedidorDTO medidorDTO = medidorClient.buscar(horimetroId);

        Double horimetroValorAtual = medidorDTO.getValorAtual();

        if(horimetroValorAtual > medidorDTO.getValorMaximo() || horimetroValorAtual < medidorDTO.getValorMinimo()){
            return true;
        }

        return false;
    }


}