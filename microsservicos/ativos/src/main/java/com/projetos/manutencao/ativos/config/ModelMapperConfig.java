package com.projetos.manutencao.ativos.config;

import com.projetos.manutencao.ativos.DTO.EquipamentoDTO;
import com.projetos.manutencao.ativos.model.Equipamento;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setSkipNullEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        mapper.getTypeMaps().forEach(typeMap -> {
            if (typeMap.getSourceType().equals(EquipamentoDTO.class) &&
                    typeMap.getDestinationType().equals(Equipamento.class)) {
                mapper.getTypeMaps().remove(typeMap);
            }
        });

        return mapper;
    }
}