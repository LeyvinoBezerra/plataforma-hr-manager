package br.edu.ufersa.rh.domain.entity;

import br.edu.ufersa.rh.domain.enums.PermissaoTipoEnum;
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
@Table(schema = "rh", name = "permissoes")
@EntityListeners(AuditingEntityListener.class)
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perm_id")
    private Long id;

    @Column(name = "perm_nome", nullable = false, unique = true, length = 50)
    private String nome;

    @Column(name = "perm_descricao", length = 255)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "perm_tipo", nullable = false)
    private PermissaoTipoEnum tipo;

    @Column(name = "perm_recurso", length = 100)
    private String recurso;

    @Column(name = "perm_ativo", nullable = false)
    private Boolean ativo;

    @CreationTimestamp
    @Column(name = "perm_data_criacao")
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "perm_data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Version
    @Column(name = "perm_versao", nullable = false)
    private Integer versao;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Permissao permissao = (Permissao) o;
        return getId() != null && Objects.equals(getId(), permissao.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
