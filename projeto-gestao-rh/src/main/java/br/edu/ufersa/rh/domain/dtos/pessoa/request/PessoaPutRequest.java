package br.edu.ufersa.rh.domain.dtos.pessoa.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaPutRequest {

    @NotNull(message = "ID é obrigatório")
    private Long id;

    private String cpf;

    private String nomeCompleto;

    private LocalDate dataNascimento;

    private String sexo;

    private String nomeDaMae;

    private String nomeDoPai;

    private String pis;

    private String rg;

    private String rgOrgaoEmissor;

    private String rgUfEmissor;

    private String nacionalidade;

    private Integer numeroVersao;

    private LocalDate dataCriacao;
}
