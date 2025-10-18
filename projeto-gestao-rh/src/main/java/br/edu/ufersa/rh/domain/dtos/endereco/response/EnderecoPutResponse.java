package br.edu.ufersa.rh.domain.dtos.endereco.response;

import br.edu.ufersa.rh.domain.entity.Pessoa;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoPutResponse {

    private Long id;

    private Pessoa pessoa;

    private String logradouro;

    private String cep;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private Boolean enderecoPrincipal;

    private LocalDateTime dataAtualizacao;

    private Integer numeroVersao;
}
