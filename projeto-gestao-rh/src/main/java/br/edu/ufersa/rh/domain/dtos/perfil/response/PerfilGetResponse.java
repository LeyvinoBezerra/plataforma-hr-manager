package br.edu.ufersa.rh.domain.dtos.perfil.response;

import br.edu.ufersa.rh.domain.enums.PermissaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilGetResponse {

    private Long id;

    private String nome;

    private PermissaoEnum permissoes;

    private Boolean acessoGlobal;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    private Integer numeroVersao;
}
