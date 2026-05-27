package br.edu.ufersa.rh.core.repository.acesso;

import br.edu.ufersa.rh.domain.entity.Acesso;
import br.edu.ufersa.rh.domain.enums.AcessoStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long> {
    List<Acesso> findByPerfilId(Long perfilId);
    List<Acesso> findByPermissaoId(Long permissaoId);
    List<Acesso> findByStatus(AcessoStatusEnum status);
    List<Acesso> findByPerfilIdAndStatus(Long perfilId, AcessoStatusEnum status);

    @Query("SELECT a FROM Acesso a WHERE a.perfil.id = :perfilId AND a.status = :status AND a.dataFim IS NULL")
    List<Acesso> findAcessosAtivos(@Param("perfilId") Long perfilId, @Param("status") AcessoStatusEnum status);

    @Query("SELECT a FROM Acesso a WHERE a.dataFim < :dataAtual AND a.status = :status")
    List<Acesso> findAcessosExpirados(@Param("dataAtual") LocalDateTime dataAtual, @Param("status") AcessoStatusEnum status);
}
