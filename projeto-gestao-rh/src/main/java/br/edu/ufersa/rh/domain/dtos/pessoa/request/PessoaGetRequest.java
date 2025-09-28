package br.edu.ufersa.rh.domain.dtos.pessoa.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaGetRequest {

    private Long id;

    private String cpf;

    private String nomeCompleto;

    private LocalDate dataNascimento;
}
