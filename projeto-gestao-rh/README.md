# Plataforma HR Manager

Sistema corporativo para gestão de Recursos Humanos desenvolvido com Java + Spring Boot.

---

## Sumário (rápido)
- Executar local: `mvn clean install` + `mvn spring-boot:run` (ou usar Docker Compose)
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- Coleção seed (preenchimento ponta-a-ponta): `src/main/resources/insomnia/API-GESTAO-RH-seed.postman_collection.json`
- Endpoints principais: `/api/v1/auth`, `/api/v1/pessoas`, `/api/v1/funcionarios`, `/api/v1/perfis`, `/api/v1/enderecos`, `/api/v1/permissoes`, `/api/v1/acessos`

---

# Visão de Negócio

A Plataforma HR Manager foi construída para centralizar e automatizar operações essenciais de RH dentro de organizações.

Funções principais:
- cadastro de pessoas
- gestão de funcionários
- controle de usuários e autenticação JWT
- gestão de perfis e permissões (RBAC)
- gestão de endereços
- APIs REST seguras

---

# Stack Tecnológica
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA / Hibernate
- PostgreSQL
- JWT (io.jsonwebtoken)
- OpenAPI / Swagger (springdoc)
- Docker
- Maven

---

# Requisitos de desenvolvimento
- JDK 17
- Maven 3.8+
- PostgreSQL (local ou container)

---

# Configuração rápida (dev)
- Copie/edite `src/main/resources/application-dev.yml` com suas credenciais
- Variáveis de ambiente importantes (exemplo):

```bash
DB_HOST=localhost
DB_PORT=5432
DB_NAME=gestao_rh
DB_USER=hr_user
DB_PASSWORD=123456
JWT_SECRET=SUPER_SECRET_KEY
PORT=8080
```

---

# Executando localmente

Windows (PowerShell):

```powershell
mvn clean install
mvn spring-boot:run
```

Ou com Docker Compose (requer Docker):

```powershell
docker-compose up --build
```

Após subir a aplicação, abra o Swagger UI: `http://localhost:8080/swagger-ui/index.html`

---

## Tutorial Swagger — passo a passo (ponta a ponta)
Abaixo está um tutorial prático que você pode executar diretamente no Swagger UI (ou seguir na coleção Postman/Insomnia). Os exemplos são reais e seguem a ordem correta para popular o sistema e testar um usuário com permissões.

Pré-requisitos:
- Aplicação rodando (http://localhost:8080)
- Swagger aberto: http://localhost:8080/swagger-ui/index.html

Observação importante: o Swagger UI não mantém variáveis entre requisições — ao usar o Swagger copie os IDs retornados (por exemplo `id`) das respostas e recole-os nos campos das próximas requisições. Para automação, use a coleção seed (Postman/Insomnia) que salva variáveis automaticamente.

Passo 1 — Registrar (bootstrap) um usuário ADMIN (apenas para seed)
- Endpoint: POST /api/v1/auth/register
- Body (exemplo):
```json
{
  "username": "admin",
  "password": "Admin@12345",
  "roles": ["ADMIN"]
}
```
- Resposta: `201 Created` com o objeto `Usuario` contendo `id`.

Passo 2 — Login Admin e obter token
- Endpoint: POST /api/v1/auth/login
- Body (exemplo):
```json
{
  "username": "admin",
  "password": "Admin@12345"
}
```
- Resposta: objeto `AuthenticationResponse` com `token` (JWT).
- No Swagger UI: clique em "Authorize" e cole `Bearer <token>` (sem aspas). A partir daí as chamadas protegidas usarão esse token.

Passo 3 — Criar permissão
- Endpoint: POST /api/v1/permissoes
- Authorization: Bearer token do admin
- Body (exemplo):
```json
{
  "nome": "FUNCIONARIO_READ",
  "descricao": "Permite ler funcionários",
  "tipo": "READ",
  "recurso": "funcionarios",
  "ativo": true
}
```
- Copie o `id` retornado (ex.: `permissaoId`) para o próximo passo.

Passo 4 — Criar perfil
- Endpoint: POST /api/v1/perfis
- Authorization: Bearer token do admin
- Body (exemplo):
```json
{
  "nome": "GESTOR",
  "permissoes": "ADMIN",
  "acessoGlobal": false
}
```
- Copie o `id` retornado (ex.: `perfilId`).

Passo 5 — Conceder acesso (perfil → permissão)
- Endpoint: POST /api/v1/acessos/conceder?perfilId={perfilId}&permissaoId={permissaoId}
- Authorization: Bearer token do admin
- Substitua `{perfilId}` e `{permissaoId}` pelos valores copiados.

Passo 6 — Criar Pessoa
- Endpoint: POST /api/v1/pessoas
- Authorization: Bearer token do admin
- Body (exemplo):
```json
{
  "cpf": "12345678901",
  "nomeCompleto": "João da Silva",
  "dataNascimento": "27/04/1990",
  "sexo": "MASCULINO",
  "nomeDaMae": "Maria Silva",
  "nomeDoPai": "José Silva",
  "pis": "12345678901",
  "rg": "MG1234567",
  "rgOrgaoEmissor": "SSP",
  "rgUfEmissor": "MG",
  "nacionalidade": "BR"
}
```
- Copie o `id` retornado (`pessoaId`).

Passo 7 — Criar Endereço para a Pessoa
- Endpoint: POST /api/v1/enderecos
- Authorization: Bearer token do admin
- Body (exemplo):
```json
{
  "pessoa": { "id": 1 },
  "logradouro": "Av. Brasil",
  "cep": "59000000",
  "numero": "100",
  "bairro": "Centro",
  "cidade": "Natal",
  "estado": "RN",
  "enderecoPrincipal": true
}
```
- Substitua `pessoa.id` pelo `pessoaId` obtido.

Passo 8 — Criar Funcionário vinculado à Pessoa
- Endpoint: POST /api/v1/funcionarios
- Authorization: Bearer token do admin
- Body (exemplo):
```json
{
  "pessoa": { "id": 1 },
  "email": "joao.silva@empresa.com",
  "dataAdmissao": "01/01/2025",
  "status": "ATIVO",
  "tipoContrato": "CLT",
  "salarioBase": 3500
}
```
- Substitua `pessoa.id` pelo `pessoaId` e copie o `funcionarioId` na resposta.

Passo 9 — Registrar usuário GESTOR (usuário aplicacional)
- Endpoint: POST /api/v1/auth/register
- Body (exemplo):
```json
{
  "username": "gestor1",
  "password": "Gestor@123",
  "roles": ["GESTOR"]
}
```
- Resposta: `201 Created` com o objeto `Usuario` contendo `id` (copie para `usuarioGestorId`).

Passo 10 — Vincular Usuario ao Funcionario (admin)
- Endpoint: POST /api/v1/usuarios/{usuarioId}/vincular-funcionario?funcionarioId={funcionarioId}
- Authorization: Bearer token do admin
- Substitua `{usuarioId}` e `{funcionarioId}` com os IDs que obteve.
- Resposta: objeto `Usuario` atualizado (com referência ao `Funcionario`).

Passo 11 — Login Gestor e testar acesso
- Endpoint: POST /api/v1/auth/login (com username: gestor1, password: Gestor@123)
- Copie o `token` retornado e clique em "Authorize" no Swagger com `Bearer <token>`.
- Teste uma rota permitida ao gestor, por exemplo:
  - GET /api/v1/funcionarios/buscarPorId?id={funcionarioId}
  - Deve retornar os dados do funcionário.

---

Observações operacionais (Swagger vs Postman)
- Swagger UI não salva variáveis (IDs/tokens) entre chamadas; você precisa copiar manualmente os `id` e o `token` entre requisições.
- Para um tutorial reproduzível (sem copiar manualmente), use a coleção seed Postman/Insomnia em `src/main/resources/insomnia/API-GESTAO-RH-seed.postman_collection.json` — ela automatiza a extração de IDs e tokens.

---

# Segurança e pronta‑produção (resumo)
Prioridades antes de ir para produção:
- JWT secret seguro em env (usar base64/gerenciador de segredos)
- Habilitar HTTPS/TLS (reverse proxy, ALB, nginx)
- Implementar revogação de tokens (blacklist em Redis ou DB) ou refresh tokens rotativos
- Migrations (Flyway/Liquibase) e remover `hibernate.ddl-auto=update` em prod
- Proteção brute-force (rate-limiting, CAPTCHAs)
- Monitoramento centralizado e alertas

Recomendações extras:
- Remover `roles` do body do `register` para produção e adotar convite/bootstrap para criar administradores
- Implementar envio de e-mail para verificação de conta (token de ativação)
- Carregar permissões completas no `UserDetails` (para suportar autorização por permissão além de roles)

---

# Docker
(Arquivo `Dockerfile` e `docker-compose.yml` já incluídos neste repositório)

# Observações finais
- Se quiser, posso:
  - implementar hardening do `register` e adicionar um `AdminBootstrapRunner` que cria o admin a partir de variáveis de ambiente;
  - implementar refresh-token + blacklist em Redis;
  - gerar testes de integração que executem a coleção seed automaticamente.

---

# Contato
Equipe Backend — responsável pela manutenção do repositório.
