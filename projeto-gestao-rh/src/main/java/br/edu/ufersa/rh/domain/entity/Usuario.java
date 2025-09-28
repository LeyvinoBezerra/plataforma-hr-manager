package br.edu.ufersa.rh.domain.entity;


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
@EqualsAndHashCode
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
    private Funcionario funcionario;

    @Column(name = "usu_login", nullable = false, unique = true)
    private String login;

    @Column(name = "usu_senha_hash", nullable = false)
    private String senha;

    @Column(name = "usu_ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "usu_ultimo_acesso")
    private LocalDateTime ultimoAcesso;

    @CreationTimestamp
    @Column(name = "usu_data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "usu_data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Version
    @Column(name = "usu_versao", nullable = false)
    private Integer numeroVersao;
}
