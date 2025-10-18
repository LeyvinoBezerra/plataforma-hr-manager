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
    @Column(name = "end_data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "end_data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Version
    @Column(name = "end_versao")
    private Integer numeroVersao;

}
