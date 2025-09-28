package br.edu.ufersa.rh.domain.dtos.perfil.response;

import br.edu.ufersa.rh.domain.dtos.pessoa.response.PessoaGetResponse;
import br.edu.ufersa.rh.domain.enums.StatusEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilGetResponse {

    private Long id;

    private PessoaGetResponse pessoa;

    private String email;

    private LocalDate dataAdmissao;

    private LocalDate dataDemissao;

    private StatusEnum status;

    private String tipoContrato;

    private BigDecimal salarioBase;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    private Integer numeroVersao;
}
