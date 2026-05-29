# 🚀 Tutorial Completo - Ponta a Ponta

## HR Manager API - Exemplo Prático de Uso Completo

Este tutorial guia você através de um fluxo completo e real de como usar a Plataforma HR Manager, do registro até a criação de um funcionário com todas as permissões corretamente configuradas.

---

## ✅ Pré-requisitos

- Aplicação rodando: `http://localhost:8080`
- Swagger disponível: `http://localhost:8080/swagger-ui/index.html`
- **Recomendado:** Use o **Postman** com a coleção `API-GESTAO-RH-ORGANIZED.json`

---

## 📋 Cenário Prático

Vamos criar um cenário real:

1. **Admin (você)** registra e faz login
2. **Admin** cria as permissões básicas do sistema
3. **Admin** cria os perfis (ADMIN, GESTOR, USUARIO)
4. **Admin** vincula permissões aos perfis
5. **Admin** cria uma pessoa física
6. **Admin** cria um endereço para essa pessoa
7. **Admin** cria um funcionário vinculado à pessoa
8. **Admin** registra um novo usuário (GESTOR)
9. **Admin** vincula o usuário gestor ao funcionário
10. **Gestor** faz login e acessa os dados

---

# PASSO 1: Autenticação — Registrar e Login

## 1.1 Registrar Usuário ADMIN

**Endpoint:** `POST /api/v1/auth/register`

**No Swagger:**
- Navegue até: **1. Authentication** → **Register - criar usuário**
- Clique em "Try it out"
- Cole o body abaixo

```json
{
  "username": "admin",
  "password": "Admin@12345",
  "roles": ["ADMIN"]
}
```

**Resposta esperada (201 Created):**
```json
{
  "id": 1,
  "username": "admin",
  "password": "$2a$12$...",
  "role": "ADMIN",
  "ativo": true,
  "status": "ATIVO",
  "tentativasFalhas": 0,
  "dataCriacao": "2026-05-29T10:30:00",
  "dataAtualizacao": "2026-05-29T10:30:00",
  "versao": 0
}
```

✅ **Anote o `id`: 1** (será usado depois)

---

## 1.2 Login com Admin

**Endpoint:** `POST /api/v1/auth/login`

**No Swagger:**
- Navegue até: **1. Authentication** → **Login - obter token**
- Clique em "Try it out"
- Cole o body

```json
{
  "username": "admin",
  "password": "Admin@12345"
}
```

**Resposta esperada (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "roles": ["ADMIN"],
  "username": "admin"
}
```

✅ **Copie o `token`**

**No Swagger:**
- Clique em **Authorize** (botão superior direito com ícone de cadeado)
- Cole: `Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...` (sem aspas)
- Clique em "Authorize"
- Clique em "Close"

Agora todos os endpoints autenticados funcionarão com seu token!

---

# PASSO 2: Permissões — Criar Permissões Base

Vamos criar 3 permissões básicas: READ, WRITE, DELETE

## 2.1 Criar Permissão READ

**Endpoint:** `POST /api/v1/permissoes`

**No Swagger:**
- Navegue até: **3. Permissions** → **Criar permissão**
- Clique em "Try it out"
- Cole o body

```json
{
  "nome": "FUNCIONARIO_READ",
  "descricao": "Permite ler dados de funcionários",
  "tipo": "READ",
  "recurso": "funcionarios",
  "ativo": true
}
```

**Resposta esperada (201 Created):**
```json
{
  "id": 1,
  "nome": "FUNCIONARIO_READ",
  "descricao": "Permite ler dados de funcionários",
  "tipo": "READ",
  "recurso": "funcionarios",
  "ativo": true,
  "dataCriacao": "2026-05-29T10:32:00",
  "dataAtualizacao": "2026-05-29T10:32:00"
}
```

✅ **Anote o `id`: 1** (será a `permissaoId`)

---

## 2.2 Criar Permissão WRITE

**Endpoint:** `POST /api/v1/permissoes`

```json
{
  "nome": "FUNCIONARIO_WRITE",
  "descricao": "Permite criar e editar funcionários",
  "tipo": "WRITE",
  "recurso": "funcionarios",
  "ativo": true
}
```

**Resposta:** `id: 2`

✅ **Anote: `permissaoWriteId = 2`**

---

## 2.3 Criar Permissão DELETE

**Endpoint:** `POST /api/v1/permissoes`

```json
{
  "nome": "FUNCIONARIO_DELETE",
  "descricao": "Permite deletar funcionários",
  "tipo": "DELETE",
  "recurso": "funcionarios",
  "ativo": true
}
```

**Resposta:** `id: 3`

✅ **Anote: `permissaoDeleteId = 3`**

---

# PASSO 3: Perfis — Criar Perfis de Acesso

## 3.1 Criar Perfil ADMIN

**Endpoint:** `POST /api/v1/perfis/salvar`

**No Swagger:**
- Navegue até: **5. Profiles** → **Criar perfil**
- Clique em "Try it out"

```json
{
  "nome": "ADMINISTRADOR",
  "permissoes": "GERENCIAR_USUARIOS",
  "acessoGlobal": true
}
```

**Resposta:** `id: 1`

✅ **Anote: `perfilAdminId = 1`**

---

## 3.2 Criar Perfil GESTOR

**Endpoint:** `POST /api/v1/perfis/salvar`

```json
{
  "nome": "GESTOR",
  "permissoes": "GERENCIAR_RELATORIOS",
  "acessoGlobal": false
}
```

**Resposta:** `id: 2`

✅ **Anote: `perfilGestorId = 2`**

---

## 3.3 Criar Perfil USUARIO_PADRAO

**Endpoint:** `POST /api/v1/perfis/salvar`

```json
{
  "nome": "USUARIO_PADRAO",
  "permissoes": "GERENCIAR_RELATORIOS",
  "acessoGlobal": false
}
```

**Resposta:** `id: 3`

✅ **Anote: `perfilUsuarioId = 3`**

---

# PASSO 4: Acessos — Vincular Permissões a Perfis

Agora vamos conceder as permissões aos perfis.

## 4.1 Conceder READ ao Perfil GESTOR

**Endpoint:** `POST /api/v1/acessos/conceder?perfilId=2&permissaoId=1`

**No Swagger:**
- Navegue até: **2. Access Management** → **Conceder acesso (Perfil + Permissão)**
- Clique em "Try it out"
- Deixe os Query Parameters como:
  - `perfilId`: 2 (GESTOR)
  - `permissaoId`: 1 (READ)
- Clique em "Send"

**Resposta:** `201 Created`

---

## 4.2 Conceder WRITE ao Perfil GESTOR

**Endpoint:** `POST /api/v1/acessos/conceder?perfilId=2&permissaoId=2`

Query Parameters:
- `perfilId`: 2
- `permissaoId`: 2 (WRITE)

---

## 4.3 Listar Acessos do Perfil GESTOR

Para verificar que funcionou:

**Endpoint:** `GET /api/v1/acessos/perfil/2/ativos`

**Resposta esperada:**
```json
[
  {
    "id": 1,
    "perfilId": 2,
    "permissaoId": 1,
    "ativo": true,
    "dataCriacao": "..."
  },
  {
    "id": 2,
    "perfilId": 2,
    "permissaoId": 2,
    "ativo": true,
    "dataCriacao": "..."
  }
]
```

✅ O Gestor agora tem acesso READ e WRITE!

---

# PASSO 5: Pessoas — Criar Dados Pessoais

## 5.1 Criar uma Pessoa

**Endpoint:** `POST /api/v1/pessoas/salvar`

**No Swagger:**
- Navegue até: **7. Pessoas** → **Criar pessoa**
- Clique em "Try it out"

```json
{
  "cpf": "12345678901",
  "nomeCompleto": "João Silva Santos",
  "dataNascimento": "27/04/1990",
  "sexo": "MASCULINO",
  "nomeDaMae": "Maria Silva",
  "nomeDoPai": "José Silva",
  "pis": "12345678901",
  "rg": "MG1234567"
}
```

**Resposta:** `201 Created`

```json
{
  "id": 1,
  "cpf": "12345678901",
  "nomeCompleto": "João Silva Santos",
  "dataNascimento": "1990-04-27",
  "sexo": "MASCULINO",
  "nomeDaMae": "Maria Silva",
  "nomeDoPai": "José Silva",
  "pis": "12345678901",
  "rg": "MG1234567",
  "dataCriacao": "2026-05-29T10:40:00",
  "dataAtualizacao": "2026-05-29T10:40:00",
  "numeroVersao": 0
}
```

✅ **Anote: `pessoaId = 1`**

---

# PASSO 6: Endereços — Criar Endereço

## 6.1 Criar Endereço Principal

**Endpoint:** `POST /api/v1/enderecos`

**No Swagger:**
- Navegue até: **6. Endereços** → **Criar endereço**
- Clique em "Try it out"

```json
{
  "pessoa": {
    "id": 1
  },
  "logradouro": "Av. Brasil",
  "cep": "59000000",
  "numero": "100",
  "bairro": "Centro",
  "cidade": "Natal",
  "estado": "RN",
  "enderecoPrincipal": true
}
```

**Resposta:** `201 Created`

```json
{
  "id": 1,
  "pessoa": {
    "id": 1
  },
  "logradouro": "Av. Brasil",
  "cep": "59000000",
  "numero": "100",
  "bairro": "Centro",
  "cidade": "Natal",
  "estado": "RN",
  "enderecoPrincipal": true,
  "dataCriacao": "2026-05-29T10:42:00",
  "dataAtualizacao": "2026-05-29T10:42:00",
  "numeroVersao": 0
}
```

✅ **Anote: `enderecoId = 1`**

---

# PASSO 7: Funcionários — Criar Funcionário

## 7.1 Criar Funcionário

**Endpoint:** `POST /api/v1/funcionarios/salvar`

**No Swagger:**
- Navegue até: **8. Funcionários** → **Criar funcionário**
- Clique em "Try it out"

```json
{
  "pessoa": {
    "id": 1
  },
  "email": "joao.silva@empresa.com",
  "dataAdmissao": "01/01/2025",
  "status": "ATIVO",
  "tipoContrato": "CLT",
  "salarioBase": 3500
}
```

**Resposta:** `201 Created`

```json
{
  "id": 1,
  "pessoa": {
    "id": 1,
    "nomeCompleto": "João Silva Santos"
  },
  "email": "joao.silva@empresa.com",
  "dataAdmissao": "2025-01-01",
  "status": "ATIVO",
  "tipoContrato": "CLT",
  "salarioBase": 3500,
  "dataCriacao": "2026-05-29T10:45:00",
  "dataAtualizacao": "2026-05-29T10:45:00",
  "numeroVersao": 0
}
```

✅ **Anote: `funcionarioId = 1`**

---

# PASSO 8: Usuários — Registrar Novo Usuário (GESTOR)

## 8.1 Registrar Usuário GESTOR

**Endpoint:** `POST /api/v1/auth/register`

**No Swagger:**
- Navegue até: **1. Authentication** → **Register - criar usuário**
- Clique em "Try it out"

```json
{
  "username": "gestor",
  "password": "Gestor@12345",
  "roles": ["GESTOR"]
}
```

**Resposta:** `201 Created`

```json
{
  "id": 2,
  "username": "gestor",
  "role": "GESTOR",
  "ativo": true,
  "status": "ATIVO",
  "tentativasFalhas": 0,
  "dataCriacao": "2026-05-29T10:47:00",
  "dataAtualizacao": "2026-05-29T10:47:00",
  "versao": 0
}
```

✅ **Anote: `usuarioGestorId = 2`**

---

# PASSO 9: Vincular Usuário ao Funcionário

## 9.1 Vincular GESTOR ao Funcionário João

**Endpoint:** `POST /api/v1/usuarios/2/vincular-funcionario?funcionarioId=1`

**No Swagger:**
- Navegue até: **4. Users** → **Vincular funcionário a usuário**
- Clique em "Try it out"
- Path Variables:
  - `usuarioId`: 2 (GESTOR)
- Query Parameters:
  - `funcionarioId`: 1 (João)
- Clique em "Send"

**Resposta:** `200 OK`

```json
{
  "id": 2,
  "username": "gestor",
  "role": "GESTOR",
  "ativo": true,
  "funcionario": {
    "id": 1,
    "pessoa": {
      "nomeCompleto": "João Silva Santos"
    }
  },
  "status": "ATIVO",
  "dataCriacao": "2026-05-29T10:47:00",
  "dataAtualizacao": "2026-05-29T10:50:00",
  "versao": 1
}
```

✅ **Perfeito! O Gestor agora está vinculado ao Funcionário João**

---

# PASSO 10: Login como GESTOR e Testar Acesso

## 10.1 Logout como ADMIN (Opcional)

**Endpoint:** `POST /api/v1/auth/logout`

Você pode fazer logout (clique em Authorize → Logout) ou apenas pegar um novo token.

---

## 10.2 Login como GESTOR

**Endpoint:** `POST /api/v1/auth/login`

**No Swagger:**
- Navegue até: **1. Authentication** → **Login - obter token**
- Clique em "Try it out"

```json
{
  "username": "gestor",
  "password": "Gestor@12345"
}
```

**Resposta:** `200 OK`

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "roles": ["GESTOR"],
  "username": "gestor"
}
```

✅ **Copie o novo token**

**No Swagger:**
- Clique em **Authorize**
- Cole: `Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...`
- Clique em "Authorize"

---

## 10.3 Testar Acesso — Listar Funcionários

**Endpoint:** `GET /api/v1/funcionarios/listarTodos`

**No Swagger:**
- Navegue até: **8. Funcionários** → **Listar todos os funcionários**
- Clique em "Try it out"
- Clique em "Send"

**Resposta esperada (200 OK):**

```json
[
  {
    "id": 1,
    "pessoa": {
      "id": 1,
      "nomeCompleto": "João Silva Santos",
      "cpf": "12345678901"
    },
    "email": "joao.silva@empresa.com",
    "dataAdmissao": "2025-01-01",
    "status": "ATIVO",
    "tipoContrato": "CLT",
    "salarioBase": 3500,
    "dataCriacao": "2026-05-29T10:45:00",
    "dataAtualizacao": "2026-05-29T10:45:00",
    "numeroVersao": 0
  }
]
```

✅ **Sucesso! O Gestor conseguiu listar os funcionários!**

---

## 10.4 Testar Acesso — Buscar Funcionário por ID

**Endpoint:** `GET /api/v1/funcionarios/buscarPorId?id=1`

**No Swagger:**
- Navegue até: **8. Funcionários** → **Obter funcionário por ID**
- Clique em "Try it out"
- Query Parameters: `id=1`
- Clique em "Send"

**Resposta:** `200 OK` com os dados do funcionário João

✅ **O Gestor conseguiu acessar os detalhes específicos!**

---

# 🎯 Resumo do que foi feito

| Etapa | O que foi criado | ID | Status |
|-------|------------------|-----|--------|
| 1 | Usuário ADMIN | 1 | ✅ Registrado e autenticado |
| 2 | Permissões (READ, WRITE, DELETE) | 1, 2, 3 | ✅ Criadas |
| 3 | Perfis (ADMIN, GESTOR, USUARIO) | 1, 2, 3 | ✅ Criados |
| 4 | Acessos (Perfil ↔ Permissão) | 1, 2 | ✅ Vinculados |
| 5 | Pessoa (João Silva) | 1 | ✅ Cadastrada |
| 6 | Endereço (Natal/RN) | 1 | ✅ Criado |
| 7 | Funcionário | 1 | ✅ Criado |
| 8 | Usuário GESTOR | 2 | ✅ Registrado |
| 9 | Vínculo Usuário ↔ Funcionário | - | ✅ Estabelecido |
| 10 | Login e Teste | - | ✅ Funcionário consegue acessar dados |

---

# 📊 Fluxo Visual

```
┌─────────────────────────────────────┐
│  ADMIN (Você)                       │
│  - Registra e faz login             │
└──────────────┬──────────────────────┘
               │
               ├─→ Cria Permissões (READ, WRITE, DELETE)
               │
               ├─→ Cria Perfis (ADMIN, GESTOR, USUARIO)
               │
               ├─→ Vincula Permissões aos Perfis
               │
               ├─→ Cria Pessoa (João Silva)
               │
               ├─→ Cria Endereço (para João)
               │
               ├─→ Cria Funcionário (para João)
               │
               └─→ Registra Usuário GESTOR
                   │
                   └─→ Vincula GESTOR ao Funcionário
                       │
                       └─→ GESTOR faz login
                           │
                           └─→ GESTOR acessa dados dos funcionários
                               ✅ SUCESSO!
```

---

# 💡 Próximos Passos

Agora que você entende o fluxo:

1. **Crie mais pessoas e funcionários** seguindo o mesmo padrão
2. **Registre mais usuários** com diferentes perfis
3. **Configure permissões granulares** para diferentes departamentos
4. **Teste diferentes cenários** de acesso

---

# ❓ Dúvidas Comuns

### "Por que meu login está dando erro?"
- Verifique se o usuário foi registrado corretamente
- Confirme que a senha está correta
- Verifique os logs para detalhes

### "Como funciona o RBAC (Role-Based Access Control)?"
- Usuários têm **roles** (ex: ADMIN, GESTOR)
- Roles estão vinculados a **perfis**
- Perfis têm **acessos** (permissões específicas)
- Isso permite controle granular de quem acessa o quê

### "Posso criar múltiplos usuários com o mesmo perfil?"
- Sim! Crie quantos usuários precisar
- Cada um pode ter diferentes vinculações com funcionários

### "Como modifico permissões depois de criadas?"
- Use `PUT /api/v1/permissoes/{id}` para atualizar
- Use `PUT /api/v1/acessos/{id}` para atualizar vinculações

---

# 📚 Documentação Adicional

- Veja `README.md` para informações sobre configuração e stack
- Consulte o Swagger em `http://localhost:8080/swagger-ui/index.html` para detalhes de cada endpoint
- Coleção Postman: `API-GESTAO-RH-ORGANIZED.json`

---

**Pronto para começar? Abra o Swagger e siga as etapas acima!** 🚀
