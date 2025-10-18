package br.edu.ufersa.rh.domain.dtos.endereco.resquest;

import br.edu.ufersa.rh.domain.dtos.pessoa.request.PessoaPutRequest;
import br.edu.ufersa.rh.domain.entity.Pessoa;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoPutRequest {

    @NotNull(message = "O campo 'id' é obrigatório.")
    private Long id;

    @NotNull(message = "O campo 'pessoa' é obrigatório.")
    private Pessoa pessoa;

    @NotNull(message = "O campo 'logradouro' é obrigatório.")
    private String logradouro;

    @NotNull(message = "O campo 'cep' é obrigatório.")
    private String cep;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private Boolean enderecoPrincipal;

    private Integer numeroVersao;
}
