package com.projetos.manutencao.ordem_manutencao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.projetos.manutencao.ordem_manutencao.DTO.FuncionarioDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.OrdemManutencaoDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.PlanoManutencaoDTO;
import com.projetos.manutencao.ordem_manutencao.enums.StatusOrdem;
import com.projetos.manutencao.ordem_manutencao.feign.FuncionarioClient;
import com.projetos.manutencao.ordem_manutencao.feign.ProcedimentoClient;
import com.projetos.manutencao.ordem_manutencao.model.ExecucaoOrdem;
import com.projetos.manutencao.ordem_manutencao.model.PlanoManutencao;
import com.projetos.manutencao.ordem_manutencao.repository.PlanoManutencaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.manutencao.ordem_manutencao.model.OrdemManutencao;
import com.projetos.manutencao.ordem_manutencao.repository.OrdemManutencaoRepository;
import com.projetos.manutencao.ordem_manutencao.service.OrdemManutencaoService;


@Service
public class OrdemManutencaoServiceImpl implements OrdemManutencaoService {

    private final OrdemManutencaoRepository repository;
    private final PlanoManutencaoRepository planoManutencaoRepository;
    private final FuncionarioClient funcionarioClient;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrdemManutencaoRepository repo;

    public OrdemManutencaoServiceImpl(OrdemManutencaoRepository repository, PlanoManutencaoRepository planoManutencaoRepository, FuncionarioClient funcionarioClient) {
        this.repository = repository;
        this.planoManutencaoRepository = planoManutencaoRepository;
        this.funcionarioClient = funcionarioClient;
    }

    @Override
    public OrdemManutencao create(OrdemManutencaoDTO ordemManutencaoDTO) {
        OrdemManutencao ordemManutencao = modelMapper.map(ordemManutencaoDTO, OrdemManutencao.class);
        ordemManutencao.setId(UUID.randomUUID().toString());
        return repository.save(ordemManutencao);
    }

    @Override
    public Optional<OrdemManutencao> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<OrdemManutencao> findAll() {
        return repository.findAll();
    }

    @Override
    public OrdemManutencao update(String id, OrdemManutencaoDTO ordemManutencaoDTO) {
        OrdemManutencao ordemManutencao = modelMapper.map(ordemManutencaoDTO, OrdemManutencao.class);

        if (repository.existsById(id)) {
            repository.save(ordemManutencao);
        }

        return ordemManutencao;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    public OrdemManutencao gerarOM(String idPM) {

        PlanoManutencao pm = planoManutencaoRepository.findById(idPM).get();

        OrdemManutencao om = OrdemManutencao.builder()
                .numeroOS(UUID.randomUUID().toString())
                .planoManutencaoID(pm.getId())
                .equipamentoID(pm.getEquipamentoID())
                .procedimentoID(pm.getProcedimentoID())
                .status(StatusOrdem.ABERTA)
                .dataAbertura(new Date())
                .responsavelID(selecionaResponsavelOM(idPM))
                .build();

        return repo.save(om);
    }

    private String selecionaResponsavelOM(String idPlano){
        PlanoManutencao pm = planoManutencaoRepository.findById(idPlano).get();
        List<String> idsResponsaveis = pm.getResponsaveisPadraoID();

        for(String funcionarioId : idsResponsaveis){
            long numeroOrdensPorFuncionario = repository.countByResponsavelID(funcionarioId);

            if(numeroOrdensPorFuncionario < 3) {
                return funcionarioId;
            }
        }

        return "";
    }

}