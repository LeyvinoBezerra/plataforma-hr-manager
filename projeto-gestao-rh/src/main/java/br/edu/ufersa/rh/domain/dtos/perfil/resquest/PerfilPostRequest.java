package br.edu.ufersa.rh.domain.dtos.perfil.resquest;

import br.edu.ufersa.rh.domain.enums.PermissaoEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilPostRequest {

    private Long id;

    @NotNull(message = "O campo 'nome' é obrigatório.")
    private String nome;

    private PermissaoEnum permissoes;

    private Boolean acessoGlobal;
}
