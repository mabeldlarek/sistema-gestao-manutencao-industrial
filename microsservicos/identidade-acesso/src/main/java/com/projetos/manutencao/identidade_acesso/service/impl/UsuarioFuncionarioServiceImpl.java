package com.projetos.manutencao.identidade_acesso.service.impl;

import com.projetos.manutencao.identidade_acesso.dto.UsuarioDTO;
import com.projetos.manutencao.identidade_acesso.model.Funcionario;
import com.projetos.manutencao.identidade_acesso.model.Usuario;
import com.projetos.manutencao.identidade_acesso.service.FuncionarioService;
import com.projetos.manutencao.identidade_acesso.service.UsuarioFuncionarioService;
import com.projetos.manutencao.identidade_acesso.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UsuarioFuncionarioServiceImpl implements UsuarioFuncionarioService {

    private final FuncionarioService funcionarioService;
    private final UsuarioService usuarioService;

    public UsuarioFuncionarioServiceImpl(FuncionarioService funcionarioService, UsuarioService usuarioService) {
        this.funcionarioService = funcionarioService;
        this.usuarioService = usuarioService;
    }

    @Override
    public void criarUsuarioParaFuncionário(String matricula, UsuarioDTO usuarioDTO) {

        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("Matrícula não pode ser vazia.");
        }

        if (usuarioDTO == null) {
            throw new IllegalArgumentException("Objeto UsuarioDTO não pode ser nulo.");
        }

        usuarioService.save(usuarioDTO);

        Usuario usuarioCriado = usuarioService.findByEmail(usuarioDTO.getEmail());

        Funcionario funcionario = funcionarioService.findByMatricula(matricula)
                .orElseThrow(() -> new NoSuchElementException(
                        "Funcionário não encontrado com a matrícula: " + matricula
                ));

        funcionario.setUsuarioId(usuarioCriado.getId().toString());
        funcionarioService.vincularUsuarioAFuncionario(funcionario);
    }

    @Transactional
    @Override
    public void deleteByIdUsuarioVinculado(String idFuncionario) {

        if (idFuncionario == null || idFuncionario.isBlank()) {
            throw new IllegalArgumentException("ID do funcionário não pode ser vazio.");
        }

        Funcionario funcionario = funcionarioService.findById(idFuncionario)
                .orElseThrow(() -> new NoSuchElementException(
                        "Funcionário não encontrado: " + idFuncionario
                ));

        if (funcionario.getUsuarioId() != null && !funcionario.getUsuarioId().isBlank()) {

            UUID idUsuario = UUID.fromString(funcionario.getUsuarioId());

            Usuario usuario = usuarioService.findById(idUsuario);

            if (usuario == null) {
                throw new NoSuchElementException("Usuário vinculado não encontrado.");
            }

            usuario.getRoles().clear();
            usuarioService.deleteById(idUsuario);
        }

        funcionarioService.deleteById(idFuncionario);
    }
}

