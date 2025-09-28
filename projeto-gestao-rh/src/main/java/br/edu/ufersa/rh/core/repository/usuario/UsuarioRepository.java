package br.edu.ufersa.rh.core.repository.usuario;

import br.edu.ufersa.rh.domain.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Funcionario, Long> {
}
