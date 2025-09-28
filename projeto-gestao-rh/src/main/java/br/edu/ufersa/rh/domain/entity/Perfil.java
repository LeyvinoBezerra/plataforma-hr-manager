package br.edu.ufersa.rh.domain.entity;

import br.edu.ufersa.rh.domain.enums.PermissaoEnum;
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
@Entity
@Table(schema = "rh", name = "perfis")
@EntityListeners(AuditingEntityListener.class)
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id")
    private Long id;

    @Column(name = "per_nome", nullable = false, length = 30)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "per_descricao", length = 50)
    private PermissaoEnum permissoes;

    @Column(name = "per_acesso_global")
    private Boolean acessoGlobal;

    @CreationTimestamp
    @Column(name = "per_data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "per_data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Version
    @Column(name = "per_numero_versao", nullable = false)
    private Integer numeroVersao;

}
