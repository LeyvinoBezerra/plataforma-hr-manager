package br.edu.ufersa.rh.core.repository.funcionario;

import br.edu.ufersa.rh.domain.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
