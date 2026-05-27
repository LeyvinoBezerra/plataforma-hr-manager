package br.edu.ufersa.rh.domain.enums;

public enum PermissaoTipoEnum {
    READ("Leitura"),
    WRITE("Escrita"),
    DELETE("Deletar"),
    EXECUTE("Executar");

    private final String descricao;

    PermissaoTipoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
