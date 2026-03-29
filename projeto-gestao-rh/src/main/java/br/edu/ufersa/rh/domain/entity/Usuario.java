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
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(schema = "rh", name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_fun_id", referencedColumnName = "fun_id")
    @ToString.Exclude
    private Funcionario funcionario;

    @Column(name = "usu_username", nullable = false, unique = true)
    private String username;

    @Column(name = "usu_password", nullable = false)
    private String password;

    @Column(name = "usu_role", nullable = false)
    private String role;

    @Column(name = "usu_ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "usu_ultimo_acesso")
    private LocalDateTime ultimoAcesso;

    @CreationTimestamp
    @Column(name = "usu_data_criacao")
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "usu_data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Version
    @Column(name = "usu_versao", nullable = false)
    private Integer versao;

    public Usuario(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Usuario usuario = (Usuario) o;
        return getId() != null && Objects.equals(getId(), usuario.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
