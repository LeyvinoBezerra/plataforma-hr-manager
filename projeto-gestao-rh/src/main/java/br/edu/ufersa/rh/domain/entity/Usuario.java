package br.edu.ufersa.rh.domain.entity;


import br.edu.ufersa.rh.domain.enums.UsuarioStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "usu_status", nullable = false)
    private UsuarioStatusEnum status;

    @Column(name = "usu_tentativas_falhas", nullable = false)
    private Integer tentativasFalhas;

    @Column(name = "usu_data_bloqueio")
    private LocalDateTime dataBloqueio;

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
}
