package br.edu.ufersa.rh.domain.dtos.endereco.resquest;

import br.edu.ufersa.rh.domain.entity.Pessoa;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoGetRequest {

    private Long id;

    private Pessoa pessoa;

    private String logradouro;

    private String cep;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;
}
