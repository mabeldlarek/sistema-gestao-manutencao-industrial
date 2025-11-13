package com.projetos.manutencao.identidade_acesso.service.impl;

import com.projetos.manutencao.identidade_acesso.dto.auth.UsuarioDTO;
import com.projetos.manutencao.identidade_acesso.model.Funcionario;
import com.projetos.manutencao.identidade_acesso.model.Usuario;
import com.projetos.manutencao.identidade_acesso.repository.UsuarioRepository;
import com.projetos.manutencao.identidade_acesso.repository.mongo.FuncionarioRepository;
import com.projetos.manutencao.identidade_acesso.service.FuncionarioService;
import com.projetos.manutencao.identidade_acesso.service.UsuarioFuncionarioService;
import com.projetos.manutencao.identidade_acesso.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
        Funcionario funcionario = funcionarioService.findByMatricula(matricula)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Funcionário não encontrado com a matrícula: " + matricula
                ));

        usuarioService.save(usuarioDTO);
        Usuario usuarioCriado = usuarioService.findByEmail(usuarioDTO.getEmail());

        funcionario.setUsuarioId(usuarioCriado.getId().toString());

        funcionarioService.vincularUsuarioAFuncionario(funcionario);
    }

    @Transactional
    public void deleteByIdUsuarioVinculado(String idFuncionario) {
        funcionarioService.findById(idFuncionario).ifPresent(func -> {
            String idUsuario = func.getUsuarioId();

            if (idUsuario != null && !idUsuario.isEmpty()) {
                Usuario usuario = usuarioService.findById(UUID.fromString(idUsuario));

                usuario.getRoles().clear();
                usuarioService.deleteById(usuario.getId());
            }

            funcionarioService.deleteById(idFuncionario);
        });
    }
}
