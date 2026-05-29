# Plataforma HR Manager

Sistema corporativo para gestão de Recursos Humanos desenvolvido com Java + Spring Boot.

---

## Sumário (rápido)
- **Executar local:** `mvn clean install` + `mvn spring-boot:run` (ou usar Docker Compose)
- **Swagger UI:** `http://localhost:8080/swagger-ui/index.html` ✅ Agora organizado igual à coleção!
- **Tutorial Completo Ponta a Ponta:** `TUTORIAL-COMPLETO.md` ⭐ **LEIA ISTO PRIMEIRO!**
- **Coleção Postman Organizada:** `src/main/resources/postman/API-GESTAO-RH-ORGANIZED.json`
- **Coleção seed original:** `src/main/resources/insomnia/API-GESTAO-RH-seed.postman_collection.json`
- **Endpoints principais:** `/api/v1/auth`, `/api/v1/acessos`, `/api/v1/permissoes`, `/api/v1/usuarios`, `/api/v1/perfis`, `/api/v1/enderecos`, `/api/v1/pessoas`, `/api/v1/funcionarios`

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

## 🎯 COMECE AQUI: Tutorial Completo Ponta a Ponta

**Leia o arquivo `TUTORIAL-COMPLETO.md` para um guia prático e detalhado de como usar o sistema inteiro!**

Este tutorial inclui:
- ✅ Exemplo real passo a passo
- ✅ Como registrar e autenticar
- ✅ Como criar permissões, perfis e acessos
- ✅ Como criar pessoas, endereços e funcionários
- ✅ Como registrar novos usuários e vinculá-los
- ✅ Como fazer login como diferentes usuários
- ✅ Como testar o acesso baseado em roles

**Tempo estimado:** 10-15 minutos para completar

---

## Tutorial Completo — Usando Postman (RECOMENDADO)

Para uma experiência melhor e automática, use a **coleção Postman organizada**: `API-GESTAO-RH-ORGANIZED.json`

### Importar a coleção no Postman:
1. Abra o Postman
2. Clique em **Import** (botão no canto superior esquerdo)
3. Selecione **Upload Files** → navegue até `src/main/resources/postman/API-GESTAO-RH-ORGANIZED.json`
4. Clique em **Import**

A coleção será carregada com **8 seções organizadas em ordem de execução**:

#### 📋 Sequência recomendada (siga nesta ordem):

1. **1. Authentication** (Autenticação básica)
   - Registre um usuário ADMIN
   - Faça login e copie o token (ele é salvo automaticamente em `authToken`)

2. **2. Access Management** (Gestão de acessos)
   - Conceda permissões a perfis
   - Consulte acessos

3. **3. Permissions** (Permissões)
   - Crie as permissões do sistema (READ, WRITE, DELETE)

4. **4. Users** (Usuários)
   - Vincule funcionários a usuários

5. **5. Profiles** (Perfis)
   - Crie os perfis (ADMIN, GESTOR, USUARIO_PADRAO)

6. **6. Endereços** (Endereços)
   - Crie endereços para as pessoas

7. **7. Pessoas** (Dados pessoais)
   - Registre as pessoas no sistema

8. **8. Funcionários** (Dados profissionais)
   - Crie os funcionários vinculados às pessoas

### 🔑 Variáveis de Ambiente (Postman):
A coleção já tem **2 variáveis** pré-configuradas:

| Variável | Valor Padrão | Descrição |
|----------|---|---|
| `baseURL` | `http://localhost:8080` | URL da API |
| `authToken` | (vazio) | Token JWT salvo automaticamente após login |

**Cada requisição de Login salva o token automaticamente** no script de teste (veja a aba "Tests" nas requisições de login).

### 🚀 Fluxo passo a passo (prático):

#### Passo 1: Registrar usuário ADMIN
- Ir para seção **1. Authentication** → **1.1 Register - Criar usuário admin**
- Clique em **Send**
- Resposta: `201 Created` com dados do usuário

#### Passo 2: Login e obter token
- Ir para **1.3 Login - Obter token (admin)**
- Clique em **Send**
- O token é automaticamente salvo em `{{authToken}}`
- (Você verá a confirmação no console do Postman)

#### Passo 3: Criar Permissões
- Ir para **3. Permissions** → **3.1 Criar permissão - FUNCIONARIO_READ**
- Clique em **Send**
- Copie o `id` retornado para o próximo passo (se precisar referenciá-lo)
- Repita para **3.2** e **3.3**

#### Passo 4: Criar Perfis
- Ir para **5. Profiles** → **5.1 Criar perfil - ADMIN**
- Clique em **Send**
- Copie o `id` retornado
- Repita para **5.2** e **5.3**

#### Passo 5: Conceder Acessos
- Ir para **2. Access Management** → **2.1 Conceder acesso**
- Clique em **Send** (usa os IDs de perfil e permissão criados)

#### Passo 6: Criar Pessoa
- Ir para **7. Pessoas** → **7.1 Criar pessoa**
- Clique em **Send**
- Copie o `id` retornado (`pessoaId`)

#### Passo 7: Criar Endereço
- Ir para **6. Endereços** → **6.1 Criar endereço**
- Na requisição, substitua `"id": 1` em `"pessoa": { "id": 1 }` pelo `pessoaId` obtido
- Clique em **Send**

#### Passo 8: Criar Funcionário
- Ir para **8. Funcionários** → **8.1 Criar funcionário**
- Na requisição, substitua `"id": 1` em `"pessoa": { "id": 1 }` pelo `pessoaId`
- Clique em **Send**
- Copie o `id` retornado (`funcionarioId`)

#### Passo 9: Registrar usuário GESTOR
- Voltar para **1. Authentication** → **1.2 Register - Criar usuário gestor**
- Clique em **Send**
- Copie o `id` retornado (`usuarioGestorId`)

#### Passo 10: Vincular Usuário ao Funcionário
- Ir para **4. Users** → **4.1 Vincular funcionário a usuário**
- Na requisição, substitua `/1/` pelo `usuarioGestorId` e `funcionarioId=1` pelo `funcionarioId` obtido
- Clique em **Send**

#### Passo 11: Testar com Usuário Gestor
- Ir para **1.3 Login - Obter token (admin)**, mas mude o body para:
  ```json
  {
    "username": "gestor",
    "password": "Gestor@12345"
  }
  ```
- Clique em **Send**
- O novo token é salvo em `{{authToken}}`
- Agora teste um endpoint restrito, ex: **8.3 Obter funcionário por ID**

---

## Tutorial Swagger — passo a passo (alternativa)

Se preferir usar o Swagger UI, abra: `http://localhost:8080/swagger-ui/index.html`

**Nota importante:** O Swagger UI **NÃO salva variáveis automaticamente**. Você terá que **copiar manualmente** os IDs e tokens entre as requisições. Para uma experiência melhor, use o **Postman com a coleção organizada** (conforme instruído acima).

### Passo 1 — Registrar usuário ADMIN
- Endpoint: `POST /api/v1/auth/register`
- Body:
```json
{
  "username": "admin",
  "password": "Admin@12345",
  "roles": ["ADMIN"]
}
```
- Copie o `id` retornado

### Passo 2 — Login Admin e obter token
- Endpoint: `POST /api/v1/auth/login`
- Body:
```json
{
  "username": "admin",
  "password": "Admin@12345"
}
```
- Copie o valor de `token` retornado
- No Swagger: clique em **Authorize** (botão no topo) e cole `Bearer <token_copiado>`

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

# Coleções disponíveis

## 1. **API-GESTAO-RH-ORGANIZED.json** ⭐ (RECOMENDADA)
- **Localização:** `src/main/resources/postman/API-GESTAO-RH-ORGANIZED.json`
- **Descrição:** Coleção completamente reorganizada e categorizada por fluxo lógico
- **Seções:** Authentication → Access Management → Permissions → Users → Profiles → Endereços → Pessoas → Funcionários
- **Uso:** Importar no Postman para teste completo ponta-a-ponta
- **Benefício:** Fluxo intuitivo com etapas claramente marcadas (1.1, 1.2, etc.)

## 2. **API-GESTAO-RH.json** (Original)
- **Localização:** `src/main/resources/postman/API-GESTAO-RH.json`
- **Descrição:** Coleção original com estrutura básica
- **Uso:** Referência ou backup

## 3. **API-GESTAO-RH-seed.postman_collection.json**
- **Localização:** `src/main/resources/insomnia/API-GESTAO-RH-seed.postman_collection.json`
- **Descrição:** Coleção com seed data (dados iniciais)
- **Uso:** Para popular o banco com dados de exemplo

---

# Troubleshooting

## Erro: "Token inválido ou expirado"
- Verifique se o token ainda é válido (TTL configurável em `application-dev.yml`)
- Faça login novamente para obter um novo token
- No Postman, clique em **Authorize** com o novo token

## Erro: "Unauthorized - não tem permissão"
- Verifique se o usuário tem o role correto
- Confirme que o perfil do usuário tem a permissão necessária
- Use `GET /api/v1/acessos/perfil/{perfilId}/ativos` para listar permissões

## Erro: "null value in column 'usu_ativo'"
- Certifique-se de que ao registrar um usuário, o campo `roles` não está vazio
- Use a coleção Postman que já tem valores padrão

## A aplicação não inicia
- Verifique se o PostgreSQL está rodando
- Confirme as credenciais de BD em `application-dev.yml`
- Verifique o arquivo `target/classes/application-dev.yml` para confirmar as configurações

## CORS error
- Se receber erro CORS ao chamar de frontend, configure o CORS em `SecurityConfiguration`
- Certifique-se de que o header `Authorization` está permitido

---

# Contato e Suporte
Equipe Backend — responsável pela manutenção do repositório.

Para dúvidas sobre a API, use o Swagger: `http://localhost:8080/swagger-ui/index.html`
Para problemas, abra uma issue no repositório ou entre em contato com o time.

