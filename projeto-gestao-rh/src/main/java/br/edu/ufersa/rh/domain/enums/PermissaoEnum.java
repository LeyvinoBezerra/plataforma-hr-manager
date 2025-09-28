package br.edu.ufersa.rh.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PermissaoEnum {

    // Permissões de acesso gestao de usuários e perfis
    GERENCIAR_USUARIOS("Gerenciar Usuários"),
    GERENCIAR_PERFIS("Gerenciar Perfis"),

    // Permissões de acesso gestão de relatórios
    GERENCIAR_RELATORIOS("Gerenciar Relatórios"),

    // Permissões de acesso gestão de configurações
    GERENCIAR_CONFIGURACOES("Gerenciar Configurações"),

    // Permissões de acesso gestão de auditoria
    GERENCIAR_AUDITORIA("Gerenciar Auditoria"),

    // Permissões de acesso gestão de integrações
    GERENCIAR_INTEGRACOES("Gerenciar Integrações");

    private String descricao;

}