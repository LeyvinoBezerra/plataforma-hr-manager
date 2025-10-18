package br.edu.ufersa.rh.domain.dtos.endereco.resquest;

import br.edu.ufersa.rh.domain.entity.Pessoa;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoPostRequest {

    private Long id;

    @NotNull(message = "O campo 'pessoa' é obrigatório.")
    private Pessoa pessoa;

    @NotNull(message = "O campo 'logradouro' é obrigatório.")
    private String logradouro;

    @NotNull(message = "O campo 'CEP' é obrigatório.")
    private String cep;

    @NotNull(message = "O campo 'número' é obrigatório.")
    private String numero;

    private String complemento;

    @NotNull(message = "O campo 'bairro' é obrigatório.")
    private String bairro;

    @NotNull(message = "O campo 'cidade' é obrigatório.")
    private String cidade;

    @NotNull(message = "O campo 'estado' é obrigatório.")
    private String estado;

    private Boolean enderecoPrincipal;
}
