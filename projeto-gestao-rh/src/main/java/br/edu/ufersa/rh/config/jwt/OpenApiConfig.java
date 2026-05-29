package br.edu.ufersa.rh.config.jwt;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration

@OpenAPIDefinition(
        info = @Info(
                title = "HR Manager API",
                version = "v1.0",
                description = "API segura para gestão de Recursos Humanos com autenticação JWT e controle de acesso baseado em roles (RBAC). " +
                        "Siga a sequência recomendada: Authentication → Access Management → Permissions → Users → Profiles → Endereços → Pessoas → Funcionários",
                contact = @Contact(
                        name = "Equipe Backend"
                )
        ),
        security = @SecurityRequirement(name = "bearer-jwt"),
        tags = {
                @Tag(name = "1. Authentication", description = "Fluxo de autenticação: Registro, Login e Logout de usuários"),
                @Tag(name = "2. Access Management", description = "Gestão de acessos e atribuição de permissões a perfis"),
                @Tag(name = "3. Permissions", description = "Criação e gestão de permissões do sistema"),
                @Tag(name = "4. Users", description = "Gestão de usuários e vinculação com funcionários"),
                @Tag(name = "5. Profiles", description = "Criação e gestão de perfis de acesso (roles)"),
                @Tag(name = "6. Endereços", description = "Gestão de endereços das pessoas"),
                @Tag(name = "7. Pessoas", description = "Cadastro e gestão de dados pessoais"),
                @Tag(name = "8. Funcionários", description = "Gestão de funcionários e dados profissionais")
        }
)

@SecurityScheme(
        name = "bearer-jwt",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "Token JWT obtido através do endpoint POST /api/v1/auth/login"
)

public class OpenApiConfig {
}
