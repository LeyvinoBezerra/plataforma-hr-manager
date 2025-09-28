package br.edu.ufersa.rh.domain.dtos.funcionario.resquest;

import br.edu.ufersa.rh.domain.dtos.pessoa.request.PessoaPostRequest;
import br.edu.ufersa.rh.domain.entity.Pessoa;
import br.edu.ufersa.rh.domain.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioPostRequest {

    private Long id;

    @NotNull(message = "O campo 'pessoa' é obrigatório.")
    private PessoaPostRequest pessoa;

    @NotNull(message = "O campo 'email' é obrigatório.")
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAdmissao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDemissao;

    @NotNull(message = "O campo 'status' é obrigatório.")
    private StatusEnum status;

    private String tipoContrato;

    private BigDecimal salarioBase;

    private LocalDateTime dataCriacao;

    private Integer numeroVersao;
}
