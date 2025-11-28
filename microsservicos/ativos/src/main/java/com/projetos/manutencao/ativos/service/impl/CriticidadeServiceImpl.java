package com.projetos.manutencao.ativos.service.impl;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import com.projetos.manutencao.ativos.DTO.CriticidadeDTO;
import com.projetos.manutencao.ativos.enums.Frequencia;
import com.projetos.manutencao.ativos.enums.Impacto;
import com.projetos.manutencao.ativos.enums.Nivel;
import com.projetos.manutencao.ativos.enums.Peso;
import com.projetos.manutencao.ativos.model.Equipamento;
import com.projetos.manutencao.ativos.repository.EquipamentoRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.manutencao.ativos.model.Criticidade;
import com.projetos.manutencao.ativos.repository.CriticidadeRepository;
import com.projetos.manutencao.ativos.service.CriticidadeService;

@Slf4j
@Service
public class CriticidadeServiceImpl implements CriticidadeService {

    private final CriticidadeRepository repository;
    private final EquipamentoRepository equipamentoRepository;
    private final HashMap<String, Double> produtoCalculadoPorCategoria = new HashMap<String, Double>();

    @Autowired
    private ModelMapper modelMapper;

    public CriticidadeServiceImpl(CriticidadeRepository repository, EquipamentoRepository equipamentoRepository) {
        this.repository = repository;
        this.equipamentoRepository = equipamentoRepository;
    }

    @Override
    public Criticidade create(String idEquipamento, CriticidadeDTO criticidadeDTO) {

        if (idEquipamento == null || idEquipamento.isBlank()) {
            throw new IllegalArgumentException("ID do equipamento não pode ser vazio.");
        }

        if (criticidadeDTO == null) {
            throw new IllegalArgumentException("Objeto CriticidadeDTO não pode ser nulo.");
        }

        Criticidade criticidade = modelMapper.map(criticidadeDTO, Criticidade.class);

        if (criticidade.getId() == null || criticidade.getId().isBlank()) {
            criticidade.setId(UUID.randomUUID().toString());
            criticidade.setNivel(obterNivelCriticidade(criticidadeDTO));
        }

        Equipamento equipamento = equipamentoRepository.findById(idEquipamento)
                .orElseThrow(() -> new NoSuchElementException("Equipamento não encontrado: " + idEquipamento));

        equipamento.setCriticidadeID(criticidade.getId());
        equipamentoRepository.save(equipamento);

        return repository.save(criticidade);
    }

    @Override
    public List<Criticidade> findAll() {
        return repository.findAll();
    }

    @Override
    public Criticidade findById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Criticidade não encontrada: " + id));
    }

    @Override
    public Criticidade update(String id, CriticidadeDTO criticidadeDTO) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (criticidadeDTO == null) {
            throw new IllegalArgumentException("Objeto CriticidadeDTO não pode ser nulo.");
        }

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Criticidade não encontrada para atualização: " + id);
        }

        Criticidade criticidade = modelMapper.map(criticidadeDTO, Criticidade.class);
        criticidade.setId(id);

        return repository.save(criticidade);
    }

    @Override
    public void delete(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Criticidade não encontrada para exclusão: " + id);
        }

        repository.deleteById(id);
    }

    public String obterNivelCriticidade(CriticidadeDTO criticidadeDTO) {

        if (criticidadeDTO == null) {
            throw new IllegalArgumentException("CriticidadeDTO é obrigatório para cálculo.");
        }

        produtoCalculadoPorCategoria.clear();

        iniciarCalculoDeNivel(criticidadeDTO);

        double pontuacaoFinal = finalizarCalculoDeNivel();

        return definirNivelFinal(pontuacaoFinal);
    }

    public void iniciarCalculoDeNivel(CriticidadeDTO criticidadeDTO) {
        validarCamposCriticidade(criticidadeDTO);

        calcularNivelProdutoCriticidadePorCategoria(
                Peso.PRODUCAO.getCategoria(),
                criticidadeDTO.getFrequenciaImpactoProducao(),
                criticidadeDTO.getImpactoProducao()
        );

        calcularNivelProdutoCriticidadePorCategoria(
                Peso.SEGURANCA.getCategoria(),
                criticidadeDTO.getFrequenciaImpactoSeguranca(),
                criticidadeDTO.getImpactoSeguranca()
        );

        calcularNivelProdutoCriticidadePorCategoria(
                Peso.AMBIENTAL.getCategoria(),
                criticidadeDTO.getFrequenciaImpactoAmbiental(),
                criticidadeDTO.getImpactoAmbiental()
        );

        calcularNivelProdutoCriticidadePorCategoria(
                Peso.CUSTO_REPARO.getCategoria(),
                criticidadeDTO.getFrequenciaCustoReparo(),
                criticidadeDTO.getCustoReparo()
        );

        calcularNivelProdutoCriticidadePorCategoria(
                Peso.FALHA.getCategoria(),
                criticidadeDTO.getFrequenciaFalha(),
                criticidadeDTO.getImpactoFalha()
        );
    }

    private void validarCamposCriticidade(CriticidadeDTO dto) {
        if (dto.getImpactoProducao() == null || dto.getFrequenciaImpactoProducao() == null ||
                dto.getImpactoSeguranca() == null || dto.getFrequenciaImpactoSeguranca() == null ||
                dto.getImpactoAmbiental() == null || dto.getFrequenciaImpactoAmbiental() == null ||
                dto.getCustoReparo() == null || dto.getFrequenciaCustoReparo() == null ||
                dto.getImpactoFalha() == null || dto.getFrequenciaFalha() == null) {

            throw new IllegalArgumentException("Todos os campos de impacto e frequência devem ser preenchidos.");
        }
    }

    public double finalizarCalculoDeNivel() {

        if (produtoCalculadoPorCategoria.isEmpty()) {
            throw new IllegalStateException("Nenhum cálculo de criticidade foi realizado.");
        }

        double somaDosPesos = Peso.PRODUCAO.getValor()
                + Peso.SEGURANCA.getValor()
                + Peso.AMBIENTAL.getValor()
                + Peso.CUSTO_REPARO.getValor()
                + Peso.FALHA.getValor();

        double somaProdutos = produtoCalculadoPorCategoria.values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        return somaProdutos / somaDosPesos;
    }

    public String definirNivelFinal(double pontuacaoCalculada) {
        if (pontuacaoCalculada < 0) {
            throw new IllegalArgumentException("Pontuação calculada inválida: " + pontuacaoCalculada);
        }

        if (pontuacaoCalculada <= Nivel.D.getFaixaDeAceite()) return "D";
        if (pontuacaoCalculada <= Nivel.C.getFaixaDeAceite()) return "C";
        if (pontuacaoCalculada <= Nivel.B.getFaixaDeAceite()) return "B";
        return "A";
    }

    private void calcularNivelProdutoCriticidadePorCategoria(String categoria, String frequencia, String impacto) {
        if (categoria == null || frequencia == null || impacto == null) {
            throw new IllegalArgumentException("Categoria, frequência e impacto não podem ser nulos.");
        }

        String nivel = getNivelCriticidadeDeFrequenciaImpactoD(categoria, frequencia, impacto);

        if (nivel.isEmpty()) nivel = getNivelCriticidadeDeFrequenciaImpactoC(categoria, frequencia, impacto);
        if (nivel.isEmpty()) nivel = getNivelCriticidadeDeFrequenciaImpactoB(categoria, frequencia, impacto);
        if (nivel.isEmpty()) nivel = getNivelCriticidadeDeFrequenciaImpactoA(categoria, frequencia, impacto);

        if (nivel.isEmpty()) {
            throw new IllegalArgumentException(
                    "Combinação inválida de impacto/frequência: " + impacto + " - " + frequencia
            );
        }
    }


    private String getNivelCriticidadeDeFrequenciaImpactoA(String categoria, String frequencia, String impacto) {
        Set<String> combinacoesNivelA = Set.of(Impacto.MEDIO.getDescricao() + "-" + Frequencia.ALTA.getDescricao(), Impacto.ALTO.getDescricao() + "-" + Frequencia.ALTA.getDescricao(), Impacto.ALTO.getDescricao() + "-" + Frequencia.MEDIA.getDescricao(), Impacto.CATASTROFICO.getDescricao() + "-" + Frequencia.ALTA.getDescricao(), Impacto.CATASTROFICO.getDescricao() + "-" + Frequencia.MEDIA.getDescricao());

        String chave = impacto + "-" + frequencia;

        if (combinacoesNivelA.contains(chave)) {
            calcularProdutoCategoriaNivelA(categoria);
            return "A";
        }

        return "";
    }

    private String getNivelCriticidadeDeFrequenciaImpactoB(String categoria, String frequencia, String impacto) {
        Set<String> combinacoesNivelB = Set.of(Impacto.BAIXO.getDescricao() + "-" + Frequencia.ALTA.getDescricao(), Impacto.BAIXO.getDescricao() + "-" + Frequencia.MEDIA.getDescricao(), Impacto.MEDIO.getDescricao() + "-" + Frequencia.MEDIA.getDescricao(), Impacto.ALTO.getDescricao() + "-" + Frequencia.BAIXA.getDescricao(), Impacto.CATASTROFICO.getDescricao() + "-" + Frequencia.BAIXA.getDescricao(), Impacto.CATASTROFICO.getDescricao() + "-" + Frequencia.REMOTA.getDescricao());

        String chave = impacto + "-" + frequencia;

        if (combinacoesNivelB.contains(chave)) {
            calcularProdutoCategoriaNivelB(categoria);

            return "B";
        }

        return "";
    }

    private String getNivelCriticidadeDeFrequenciaImpactoC(String categoria, String frequencia, String impacto) {
        Set<String> combinacoesNivelC = Set.of(Impacto.CATASTROFICO.getDescricao() + "-" + Frequencia.IMPROVAVEL.getDescricao(), Impacto.ALTO.getDescricao() + "-" + Frequencia.REMOTA.getDescricao(), Impacto.MEDIO.getDescricao() + "-" + Frequencia.BAIXA.getDescricao(), Impacto.MEDIO.getDescricao() + "-" + Frequencia.REMOTA.getDescricao(), Impacto.BAIXO.getDescricao() + "-" + Frequencia.BAIXA.getDescricao(), Impacto.INSIGNIFICANTE.getDescricao() + "-" + Frequencia.ALTA.getDescricao(), Impacto.INSIGNIFICANTE.getDescricao() + "-" + Frequencia.MEDIA.getDescricao(), Impacto.INSIGNIFICANTE.getDescricao() + "-" + Frequencia.BAIXA.getDescricao());

        String chave = impacto + "-" + frequencia;

        if (combinacoesNivelC.contains(chave)) {
            calcularProdutoCategoriaNivelC(categoria);

            return "C";
        }

        return "";
    }

    private String getNivelCriticidadeDeFrequenciaImpactoD(String categoria, String frequencia, String impacto) {
        Set<String> combinacoesNivelD = Set.of(Impacto.ALTO.getDescricao() + "-" + Frequencia.IMPROVAVEL.getDescricao(), Impacto.MEDIO.getDescricao() + "-" + Frequencia.IMPROVAVEL.getDescricao(), Impacto.BAIXO.getDescricao() + "-" + Frequencia.REMOTA.getDescricao(), Impacto.BAIXO.getDescricao() + "-" + Frequencia.IMPROVAVEL.getDescricao(), Impacto.INSIGNIFICANTE.getDescricao() + "-" + Frequencia.REMOTA.getDescricao(), Impacto.INSIGNIFICANTE.getDescricao() + "-" + Frequencia.IMPROVAVEL.getDescricao());

        String chave = impacto + "-" + frequencia;

        if (combinacoesNivelD.contains(chave)) {
            calcularProdutoCategoriaNivelD(categoria);
            return "D";
        }

        return "";
    }

    private double getValorPorCategoria(String categoria) {
        double valorCategoria = 0.0;

        if (categoria.equals(Peso.PRODUCAO.getCategoria())) {
            valorCategoria = Peso.PRODUCAO.getValor();
        }
        if (categoria.equals(Peso.SEGURANCA.getCategoria())) {
            valorCategoria = Peso.SEGURANCA.getValor();
        }
        if (categoria.equals(Peso.AMBIENTAL.getCategoria())) {
            valorCategoria = Peso.AMBIENTAL.getValor();
        }
        if (categoria.equals(Peso.CUSTO_REPARO.getCategoria())) {
            valorCategoria = Peso.CUSTO_REPARO.getValor();
        }
        if (categoria.equals(Peso.FALHA.getCategoria())) {
            valorCategoria = Peso.FALHA.getValor();
        }

        return valorCategoria;
    }

    private void calcularProdutoCategoriaNivelA(String categoria) {
        double valorCategoria = getValorPorCategoria(categoria);
        double valorProdutoA = arredondar(valorCategoria * Nivel.A.getValor());
        produtoCalculadoPorCategoria.put(categoria, valorProdutoA);
    }

    private void calcularProdutoCategoriaNivelB(String categoria) {
        double valorCategoria = getValorPorCategoria(categoria);
        double valorProdutoB = arredondar(valorCategoria * Nivel.B.getValor());
        produtoCalculadoPorCategoria.put(categoria, valorProdutoB);
    }

    private void calcularProdutoCategoriaNivelC(String categoria) {
        double valorCategoria = getValorPorCategoria(categoria);
        double valorProdutoC = arredondar(valorCategoria * Nivel.C.getValor());
        produtoCalculadoPorCategoria.put(categoria, valorProdutoC);
    }

    private void calcularProdutoCategoriaNivelD(String categoria) {
        double valorCategoria = getValorPorCategoria(categoria);
        double valorProdutoD = arredondar(valorCategoria * Nivel.D.getValor());
        produtoCalculadoPorCategoria.put(categoria, valorProdutoD);
    }

    private double arredondar(double valor) {
        return BigDecimal.valueOf(valor)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

}
