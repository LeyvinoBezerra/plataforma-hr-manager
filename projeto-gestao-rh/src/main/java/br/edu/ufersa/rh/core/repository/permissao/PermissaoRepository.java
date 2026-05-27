package br.edu.ufersa.rh.core.repository.permissao;

import br.edu.ufersa.rh.domain.entity.Permissao;
import br.edu.ufersa.rh.domain.enums.PermissaoTipoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
    Optional<Permissao> findByNome(String nome);
    List<Permissao> findByAtivo(Boolean ativo);
    List<Permissao> findByTipo(PermissaoTipoEnum tipo);
    List<Permissao> findByRecurso(String recurso);
}
