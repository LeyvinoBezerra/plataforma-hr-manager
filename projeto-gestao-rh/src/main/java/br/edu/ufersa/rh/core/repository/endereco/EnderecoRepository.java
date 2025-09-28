package br.edu.ufersa.rh.core.repository.endereco;

import br.edu.ufersa.rh.domain.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
