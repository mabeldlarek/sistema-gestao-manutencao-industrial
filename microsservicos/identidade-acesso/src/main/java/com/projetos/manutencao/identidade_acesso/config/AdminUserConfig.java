package com.projetos.manutencao.identidade_acesso.config;

import com.projetos.manutencao.identidade_acesso.model.Role;
import com.projetos.manutencao.identidade_acesso.model.Usuario;
import com.projetos.manutencao.identidade_acesso.repository.RoleRepository;
import com.projetos.manutencao.identidade_acesso.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UsuarioRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository,
                           UsuarioRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Role roleAdmin = roleRepository.findByNameIgnoreCase(Role.Values.ADMIN.name());

        if (roleAdmin == null) {
            throw new RuntimeException("Role ADMIN não encontrada");
        }

        var userAdmin = userRepository.findByNome("ADMIN");

        userAdmin.ifPresentOrElse(
                user -> System.out.println("admin já existe"),
                () -> {
                    var user = new Usuario();
                    user.setNome("ADMIN");
                    user.setSenha(passwordEncoder.encode("123"));
                    user.setRoles(Set.of(roleAdmin));
                    user.setAtivo(true);
                    user.setTipoUsuario(roleAdmin.getName());
                    user.setEmail("email@teste.com");
                    userRepository.save(user);
                }
        );
    }
}
