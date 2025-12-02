package com.projetos.manutencao.identidade_acesso.service;


import java.util.List;
import java.util.UUID;

import com.projetos.manutencao.identidade_acesso.dto.auth.FuncionarioDTO;
import com.projetos.manutencao.identidade_acesso.dto.auth.UsuarioDTO;
import com.projetos.manutencao.identidade_acesso.model.Usuario;

public interface UsuarioService {
    Usuario findByEmail(String email);
    void save(UsuarioDTO Usuario);
    void deleteById(UUID id);
    void update(UUID id, UsuarioDTO usuario);
    Usuario findById(UUID id);
    List<Usuario> findAll();


}
