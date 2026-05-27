package br.edu.ufersa.rh.domain.enums;

public enum UsuarioStatusEnum {
    ATIVO("Ativo"),
    INATIVO("Inativo"),
    BLOQUEADO("Bloqueado"),
    PENDENTE("Pendente");

    private final String descricao;

    UsuarioStatusEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
