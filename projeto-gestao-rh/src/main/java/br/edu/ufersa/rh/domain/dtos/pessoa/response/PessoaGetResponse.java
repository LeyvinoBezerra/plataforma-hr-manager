package br.edu.ufersa.rh.domain.dtos.pessoa.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaGetResponse {

    private Long id;

    private String cpf;

    private String nomeCompleto;

    private LocalDate dataNascimento;

    private String sexo;

    private String nacionalidade;
}
