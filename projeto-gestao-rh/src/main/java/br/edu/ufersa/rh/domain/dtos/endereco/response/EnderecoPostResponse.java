package br.edu.ufersa.rh.domain.dtos.endereco.response;

import br.edu.ufersa.rh.domain.entity.Pessoa;
import br.edu.ufersa.rh.domain.enums.StatusEnum;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoPostResponse {

    private Long id;

    private Pessoa pessoa;

    private String email;

    private StatusEnum status;
}
