package br.edu.ufersa.rh.domain.enums;

public enum AcessoStatusEnum {
    ATIVO("Ativo"),
    INATIVO("Inativo"),
    BLOQUEADO("Bloqueado"),
    EXPIRADO("Expirado");

    private final String descricao;

    AcessoStatusEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
