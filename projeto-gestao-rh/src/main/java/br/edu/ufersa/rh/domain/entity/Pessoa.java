package br.edu.ufersa.rh.domain.entity;

import br.edu.ufersa.rh.domain.enums.SexoEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Por regra de negócio, cada pessoa vai ter um cadastro único via CPF valido.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "rh", name = "pessoas")
@EntityListeners(AuditingEntityListener.class)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pes_id")
    private Long id;

    @Column(name = "pes_cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "pes_nome_completo", nullable = false, length = 128)
    private String nomeCompleto;

    @Column(name = "pes_data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "pes_sexo", nullable = false, length = 16)
    private SexoEnum sexo;

    @Column(name = "pes_nome_mae", length = 128)
    private String nomeDaMae;

    @Column(name = "pes_nome_pai", length = 128)
    private String nomeDoPai;

    @Column(name = "pes_pis", length = 14)
    private String pis;

    @Column(name = "pes_rg", nullable = false, length = 20)
    private String rg;

    @Column(name = "pes_rg_orgao_emissor", nullable = false, length = 20)
    private String rgOrgaoEmissor;

    @Column(name = "pes_rg_uf_emissor", nullable = false, length = 2)
    private String rgUfEmissor;

    @Column(name = "pes_nacionalidade", nullable = false, length = 50)
    private String nacionalidade;

    @CreationTimestamp
    @Column(name = "pes_data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "pes_data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Version
    @Column(name = "pes_versao")
    private Integer numeroVersao;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Pessoa pessoa = (Pessoa) o;
        return getId() != null && Objects.equals(getId(), pessoa.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
