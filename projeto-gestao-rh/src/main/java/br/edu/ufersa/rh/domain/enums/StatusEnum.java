package br.edu.ufersa.rh.domain.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    ATIVO("Ativo"),
    INATIVO("Inativo"),
    AFASTADO("Afastado"),
    LICENCA("Licença"),
    DEMITIDO("Demitido"),
    APOSENTADO("Aposentado");

    private final String descricao;
}
