package com.projetos.manutencao.ordem_manutencao.config;

import com.projetos.manutencao.ordem_manutencao.DTO.ExecucaoOrdemDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.OrdemManutencaoDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.PlanoManutencaoDTO;
import com.projetos.manutencao.ordem_manutencao.model.ExecucaoOrdem;
import com.projetos.manutencao.ordem_manutencao.model.OrdemManutencao;
import com.projetos.manutencao.ordem_manutencao.model.PlanoManutencao;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setAmbiguityIgnored(true);

        mapper.typeMap(PlanoManutencaoDTO.class, PlanoManutencao.class)
                .addMappings(m -> {
                    m.map(PlanoManutencaoDTO::getEquipamentoID, PlanoManutencao::setEquipamentoID);
                    m.map(PlanoManutencaoDTO::getProcedimentoID, PlanoManutencao::setProcedimentoID);
                });

        mapper.typeMap(OrdemManutencaoDTO.class, OrdemManutencao.class)
                .addMappings(m -> {
                    m.map(OrdemManutencaoDTO::getEquipamentoID, OrdemManutencao::setEquipamentoID);
                    m.map(OrdemManutencaoDTO::getSolicitanteID, OrdemManutencao::setSolicitanteID);
                    m.map(OrdemManutencaoDTO::getResponsavelID, OrdemManutencao::setResponsavelID);
                    m.map(OrdemManutencaoDTO::getProcedimentoID, OrdemManutencao::setProcedimentoID);
                    m.map(OrdemManutencaoDTO::getModoFalhaID, OrdemManutencao::setModoFalhaID);
                    m.map(OrdemManutencaoDTO::getCausaRaizID, OrdemManutencao::setCausaRaizID);
                });

        mapper.typeMap(ExecucaoOrdemDTO.class, ExecucaoOrdem.class)
                .addMappings(m -> {
                    m.map(ExecucaoOrdemDTO::getOrdemManutencaoID, ExecucaoOrdem::setOrdemManutencaoID);
                    m.map(ExecucaoOrdemDTO::getExecutorID, ExecucaoOrdem::setExecutorID);
                    m.map(ExecucaoOrdemDTO::getChecklistItens, ExecucaoOrdem::setChecklistItens);
                });

        return mapper;
    }
}

