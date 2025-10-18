package br.edu.ufersa.rh.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissaoEnum {

    // Permissões de acesso gestao de usuários e perfis
    USUARIO("USUARIO"),
    PERFIL("PERFIL"),

    // Permissões de acesso gestão de relatórios
    REPORT("REPORT"),

    // Permissões de acesso gestão de configurações
    CONFIGURADOR("CONFIGURADOR"),

    // Permissões de acesso gestão de auditoria
    AUDITOR("AUDITOR"),

    // Permissões de acesso gestão de integrações
    INTEGRADOR("INTEGRADOR"),

    //permissoes de admin
    ADMIN("ADMIN");

    private final String descricao;
}