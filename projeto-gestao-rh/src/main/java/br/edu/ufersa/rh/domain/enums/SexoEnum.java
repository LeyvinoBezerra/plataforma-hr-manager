package br.edu.ufersa.rh.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexoEnum {
    MASCULINO("Masculino"), FEMININO("Feminino"), NAO_INFORMADO("Não Informado");

    private final String descricao;
}
