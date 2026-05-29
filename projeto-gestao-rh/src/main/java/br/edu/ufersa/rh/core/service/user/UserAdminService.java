package br.edu.ufersa.rh.core.service.user;

import br.edu.ufersa.rh.core.repository.funcionario.FuncionarioRepository;
import br.edu.ufersa.rh.core.repository.usuario.UsuarioRepository;
import br.edu.ufersa.rh.domain.entity.Funcionario;
import br.edu.ufersa.rh.domain.entity.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAdminService {

    private final UsuarioRepository usuarioRepository;
    private final FuncionarioRepository funcionarioRepository;

    public UserAdminService(UsuarioRepository usuarioRepository, FuncionarioRepository funcionarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public Usuario vincularFuncionarioAoUsuario(Long usuarioId, Long funcionarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado: " + usuarioId));

        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new IllegalArgumentException("Funcionario não encontrado: " + funcionarioId));

        usuario.setFuncionario(funcionario);
        return usuarioRepository.save(usuario);
    }
}
