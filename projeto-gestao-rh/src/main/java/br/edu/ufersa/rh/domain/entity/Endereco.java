package br.edu.ufersa.rh.domain.entity;

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
@Table(schema = "rh", name = "enderecos")
@EntityListeners(AuditingEntityListener.class)
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "end_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "end_pes_id", referencedColumnName = "pes_id")
    private Pessoa pessoa;

    @Column(name = "end_logradouro", nullable = false, length = 100)
    private String logradouro;

    @Column(name = "end_cep", nullable = false, length = 8)
    private String cep;

    @Column(name = "end_numero", nullable = false, length = 10)
    private String numero;

    @Column(name = "end_complemento", length = 50)
    private String complemento;

    @Column(name = "end_bairro", nullable = false, length = 50)
    private String bairro;

    @Column(name = "end_cidade", nullable = false, length = 50)
    private String cidade;

    @Column(name = "end_estado", nullable = false, length = 2)
    private String estado;

    @Column(name = "end_principal")
    private Boolean enderecoPrincipal;

    @CreationTimestamp
    @Column(name = "end_data_criacao")
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "end_data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Version
    @Column(name = "end_versao")
    private Integer numeroVersao;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Endereco endereco = (Endereco) o;
        return getId() != null && Objects.equals(getId(), endereco.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
