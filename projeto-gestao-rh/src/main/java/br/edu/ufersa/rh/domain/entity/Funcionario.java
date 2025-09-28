package br.edu.ufersa.rh.domain.entity;


import br.edu.ufersa.rh.domain.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(schema = "rh", name = "funcionarios")
@EntityListeners(AuditingEntityListener.class)
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fun_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fun_pes_id", referencedColumnName = "pes_id", nullable = false)
    private Pessoa pessoa;

    @Column(name = "fun_email_corporativo", nullable = false, length = 100)
    private String email;

    @Column(name="fun_data_admissao", nullable = false)
    private LocalDate dataAdmissao;

    @Column(name="fun_data_demissao")
    private LocalDate dataDemissao;

    @Enumerated(EnumType.STRING)
    @Column(name="fun_status", nullable = false, length = 20)
    private StatusEnum status;

    @Column(name = "fun_modalidade_contrato", nullable = false, length = 20)
    private String tipoContrato;

    @Column(name = "fun_salario_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal salarioBase;

    @CreationTimestamp
    @Column(name = "fun_data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "fun_data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Version
    @Column(name = "fun_versao", nullable = false)
    private Integer numeroVersao;
}
