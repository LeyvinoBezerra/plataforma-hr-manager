package br.edu.ufersa.rh.domain.entity;

import br.edu.ufersa.rh.domain.enums.AcessoStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(schema = "rh", name = "acessos")
@EntityListeners(AuditingEntityListener.class)
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aces_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aces_perfil_id", referencedColumnName = "per_id", nullable = false)
    @ToString.Exclude
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aces_permissao_id", referencedColumnName = "perm_id", nullable = false)
    @ToString.Exclude
    private Permissao permissao;

    @Enumerated(EnumType.STRING)
    @Column(name = "aces_status", nullable = false)
    private AcessoStatusEnum status;

    @Column(name = "aces_data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "aces_data_fim")
    private LocalDateTime dataFim;

    @CreationTimestamp
    @Column(name = "aces_data_criacao")
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "aces_data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Version
    @Column(name = "aces_versao", nullable = false)
    private Integer versao;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Acesso acesso = (Acesso) o;
        return getId() != null && Objects.equals(getId(), acesso.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
