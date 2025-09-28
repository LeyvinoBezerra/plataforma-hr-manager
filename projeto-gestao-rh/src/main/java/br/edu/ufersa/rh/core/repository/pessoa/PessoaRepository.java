package br.edu.ufersa.rh.core.repository.pessoa;

import br.edu.ufersa.rh.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
