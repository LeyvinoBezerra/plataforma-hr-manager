package br.edu.ufersa.rh.domain.dtos.perfil.response;

import br.edu.ufersa.rh.domain.entity.Pessoa;
import br.edu.ufersa.rh.domain.enums.PermissaoEnum;
import br.edu.ufersa.rh.domain.enums.StatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilPostResponse {

    private Long id;

    private String nome;

    private PermissaoEnum permissoes;

    private Boolean acessoGlobal;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    private Integer numeroVersao;
}
