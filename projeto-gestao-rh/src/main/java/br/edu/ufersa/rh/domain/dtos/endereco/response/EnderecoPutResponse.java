package br.edu.ufersa.rh.domain.dtos.endereco.response;

import br.edu.ufersa.rh.domain.dtos.pessoa.response.PessoaPutResponse;
import br.edu.ufersa.rh.domain.enums.StatusEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoPutResponse {

    private Long id;

    private PessoaPutResponse pessoa;

    private String email;

    private LocalDate dataAdmissao;

    private LocalDate dataDemissao;

    private StatusEnum status;

    private String tipoContrato;

    private BigDecimal salarioBase;
}
