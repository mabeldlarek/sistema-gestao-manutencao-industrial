package com.projetos.manutencao.ordem_manutencao.service.impl;
    
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.projetos.manutencao.ordem_manutencao.DTO.PlanoManutencaoDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.UpdateStatusOrdemDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.UpdateStatusPlanoDTO;
import com.projetos.manutencao.ordem_manutencao.enums.StatusOrdem;
import com.projetos.manutencao.ordem_manutencao.enums.StatusPlano;
import com.projetos.manutencao.ordem_manutencao.enums.UnidadeFrequencia;
import com.projetos.manutencao.ordem_manutencao.model.OrdemManutencao;
import com.projetos.manutencao.ordem_manutencao.model.PlanoManutencao;
import com.projetos.manutencao.ordem_manutencao.util.DateTimeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.manutencao.ordem_manutencao.repository.PlanoManutencaoRepository;
import com.projetos.manutencao.ordem_manutencao.service.PlanoManutencaoService;

@Service
public class PlanoManutencaoServiceImpl implements PlanoManutencaoService {

    private final PlanoManutencaoRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public PlanoManutencaoServiceImpl(PlanoManutencaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public PlanoManutencao criarPlano(PlanoManutencaoDTO planoManutencaoDTO) {
        PlanoManutencao planoManutencao = modelMapper.map(planoManutencaoDTO, PlanoManutencao.class);
        planoManutencao.setId(UUID.randomUUID().toString());

        if (planoManutencao.getGerarOMAutomatica()) {
            planoManutencao.setDataGeracaoAutomaticaOM(calcularProximaDataGeraçãoTempo(planoManutencao.getFrequenciaUnidade(), planoManutencao));
        }

        planoManutencao.setDataCriacao(new Date());

     
        return repository.save(planoManutencao);
    }

    @Override
    public Optional<PlanoManutencao> buscarPorId(String id) {
        return repository.findById(id);
    }

    @Override
    public List<PlanoManutencao> listarTodos() {
        return repository.findAll();
    }

    @Override
    public PlanoManutencao atualizarPlano(String id, PlanoManutencaoDTO planoManutencaoDTO) {
        PlanoManutencao planoManutencao = modelMapper.map(planoManutencaoDTO, PlanoManutencao.class);
        planoManutencao.setId(id);
        if (repository.existsById(id)) {
            repository.save(planoManutencao);
        }
        return planoManutencao;
    }

    @Override
    public void deletarPlano(String id) {
        repository.deleteById(id);
    }

    public List<PlanoManutencao> buscarPlanosAgendados(Date agora) {
        return repository.findByGerarOMAutomaticaTrueAndDataGeracaoAutomaticaOMLessThanEqual(agora);
    }

    private Date calcularProximaDataGeraçãoTempo(UnidadeFrequencia uf, PlanoManutencao pm) {
        return DateTimeUtils.calcularProximaData(
                pm.getDataGeracaoAutomaticaOM(),
                uf,
                pm.getFrequenciaValor().intValue()
        );
    }

    @Override
    public void updateStatus(String idOm, UpdateStatusPlanoDTO status) {
        PlanoManutencao om = repository.findById(idOm).get();
        StatusPlano statusPlano = modelMapper.map(status, StatusPlano.class);

        if (om != null) {
            om.setStatus(statusPlano);
            repository.save(om);
        }
    }

}