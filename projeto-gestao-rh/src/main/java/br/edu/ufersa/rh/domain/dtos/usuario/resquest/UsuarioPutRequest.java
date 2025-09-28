package br.edu.ufersa.rh.domain.dtos.usuario.resquest;

import br.edu.ufersa.rh.domain.dtos.pessoa.request.PessoaPutRequest;
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
public class UsuarioPutRequest {

    @NotNull(message = "O campo 'pessoa' é obrigatório.")
    private Long id;

    @NotNull(message = "O campo 'pessoa' é obrigatório.")
    private PessoaPutRequest pessoa;

    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAdmissao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDemissao;

    private StatusEnum status;

    private String tipoContrato;

    private BigDecimal salarioBase;

    private LocalDateTime dataCriacao;

    private Integer numeroVersao;
}
