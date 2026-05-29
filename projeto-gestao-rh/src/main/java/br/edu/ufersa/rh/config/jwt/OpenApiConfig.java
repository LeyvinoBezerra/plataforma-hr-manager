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
                version = "v1",
                description = "API segura para gestão de RH",
                contact = @Contact(
                        name = "Equipe Backend"
                )
        ),
        security = @SecurityRequirement(name = "bearerAuth"),
        tags = {
                @Tag(name = "Authentication", description = "Login, register e logout"),
                @Tag(name = "Access Management", description = "Gerenciamento de acessos e atribuições (Acessos, Permissões)"),
                @Tag(name = "Permissions", description = "CRUD de Permissões"),
                @Tag(name = "Profiles", description = "CRUD de Perfis (roles)") ,
                @Tag(name = "Pessoas", description = "Gerenciamento de pessoas"),
                @Tag(name = "Endereços", description = "Gerenciamento de endereços"),
                @Tag(name = "Funcionários", description = "Gerenciamento de funcionários"),
                @Tag(name = "Users", description = "Endpoints relacionados a usuários")
        }
)

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)

public class OpenApiConfig {
}
