package br.edu.ufersa.rh.domain.dtos.perfil.resquest;

import br.edu.ufersa.rh.domain.dtos.pessoa.request.PessoaPostRequest;
import br.edu.ufersa.rh.domain.enums.StatusEnum;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilGetRequest {

    private Long id;

    private PessoaPostRequest pessoa;

    private String email;

    private StatusEnum status;
}
