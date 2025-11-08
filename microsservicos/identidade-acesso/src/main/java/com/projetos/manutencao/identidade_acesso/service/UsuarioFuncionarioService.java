package com.projetos.manutencao.identidade_acesso.service;

import com.projetos.manutencao.identidade_acesso.dto.auth.UsuarioDTO;

public interface UsuarioFuncionarioService {
    void criarUsuarioParaFuncionário(String matricula, UsuarioDTO usuarioDTO);
    public void deleteByIdUsuarioVinculado(String idFuncionario);
}
