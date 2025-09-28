package br.edu.ufersa.rh.domain.dtos.pessoa.request;

import br.edu.ufersa.rh.domain.enums.SexoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaPostRequest {

    private Long id;

    private String cpf;

    private String nomeCompleto;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private SexoEnum sexo;

    private String nomeDaMae;

    private String nomeDoPai;

    private String pis;

    private String rg;

    private String rgOrgaoEmissor;

    private String rgUfEmissor;

    private String nacionalidade;

    private Integer numeroVersao;
}
