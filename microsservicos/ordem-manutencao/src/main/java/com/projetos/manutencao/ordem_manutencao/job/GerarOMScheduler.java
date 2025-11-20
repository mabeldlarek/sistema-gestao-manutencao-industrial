package com.projetos.manutencao.ordem_manutencao.job;

import com.projetos.manutencao.ordem_manutencao.enums.UnidadeFrequencia;
import com.projetos.manutencao.ordem_manutencao.model.PlanoManutencao;
import com.projetos.manutencao.ordem_manutencao.service.OrdemManutencaoService;
import com.projetos.manutencao.ordem_manutencao.service.PlanoManutencaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Component
public class GerarOMScheduler {

    @Autowired
    private PlanoManutencaoService planoService;

    @Autowired
    private OrdemManutencaoService omService;

    @Scheduled(fixedRate = 60000)
    public void verificarPlanos() {

        Date agora = new Date();

        List<PlanoManutencao> planos = planoService.buscarPlanosAgendados(agora);

        for (PlanoManutencao pm : planos) {
            omService.gerarOM(pm.getId());
        }
    }

    private void calcularProximaDataGeração(UnidadeFrequencia uf, PlanoManutencao pm) {

        Date dataAtual = pm.getDataGeracaoAutomaticaOM();
        int valor = pm.getFrequenciaValor().intValue();

        Date novaDataOm = null;

        switch (uf) {
            case DIA:
                novaDataOm = Date.from(dataAtual.toInstant().plus(valor, ChronoUnit.DAYS));
                break;

            case SEMANA:
                novaDataOm = Date.from(dataAtual.toInstant().plus(valor, ChronoUnit.WEEKS));
                break;

            case MES:
                novaDataOm = Date.from(dataAtual.toInstant().plus(valor, ChronoUnit.MONTHS));
                break;

            case ANO:
                novaDataOm = Date.from(dataAtual.toInstant().plus(valor, ChronoUnit.YEARS));
                break;
        }

        pm.setDataGeracaoAutomaticaOM(novaDataOm);
    }
}