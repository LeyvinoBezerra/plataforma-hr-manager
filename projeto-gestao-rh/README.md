# Plataforma HR Manager

Sistema corporativo para gestão de Recursos Humanos desenvolvido com Java + Spring Boot.

---

# Visão de Negócio

A Plataforma HR Manager foi construída para centralizar e automatizar operações essenciais de RH dentro de organizações.

O sistema permite:

- cadastro de pessoas
- gestão de funcionários
- controle de usuários
- autenticação JWT
- controle de perfis e permissões
- gestão de endereços
- segurança baseada em roles
- APIs REST corporativas

O objetivo principal é fornecer uma arquitetura segura, escalável e preparada para ambientes enterprise.

---

# Principais Funcionalidades

## Pessoas

- cadastro de pessoas físicas
- atualização cadastral
- consulta por ID
- listagem completa
- exclusão lógica/física

## Funcionários

- admissão
- tipo de contrato
- status funcional
- salário base
- vínculo com pessoa

## Perfis

- criação de perfis
- gestão de permissões
- acesso administrativo
- controle RBAC

## Usuários

- autenticação JWT
- cadastro de usuários
- controle de roles
- acesso seguro via token

## Segurança

- Spring Security
- JWT Authentication
- BCrypt Password Encoder
- Stateless API
- Swagger protegido
- CORS configurado
- HSTS
- AuthenticationEntryPoint customizado

---

# Stack Tecnológica

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- JWT
- Swagger OpenAPI
- Docker
- Maven

---

# Executando Localmente

## Requisitos

### Windows

- Java 21+
- Maven 3.9+
- PostgreSQL 15+
- Git

### Linux

```bash
sudo apt update
sudo apt install openjdk-21-jdk maven postgresql postgresql-contrib -y
```

---

# Clonar Projeto

```bash
git clone https://github.com/LeyvinoBezerra/plataforma-hr-manager.git
cd plataforma-hr-manager
```

---

# Banco PostgreSQL

```sql
CREATE DATABASE gestao_rh;

CREATE USER hr_user WITH PASSWORD '123456';

GRANT ALL PRIVILEGES ON DATABASE gestao_rh TO hr_user;
```

---

# application-dev.yml

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/gestao_rh
    username: hr_user
    password: 123456

  jpa:
    hibernate:
      ddl-auto: update

    show-sql: true

jwt:
  secret: SUA_CHAVE_JWT_SUPER_SECRETA
  expiration: 900000
```

---

# Executar Projeto

## Windows

```powershell
mvn clean install
mvn spring-boot:run
```

## Linux

```bash
mvn clean install
mvn spring-boot:run
```

---

# Swagger

```text
http://localhost:8080/swagger-ui/index.html
```

---

# Docker

## Dockerfile

```dockerfile
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

# Docker Compose

```yaml
version: '3.9'

services:

  postgres:
    image: postgres:15

    environment:
      POSTGRES_DB: gestao_rh
      POSTGRES_USER: hr_user
      POSTGRES_PASSWORD: 123456

    ports:
      - "5432:5432"

  app:
    build: .

    ports:
      - "8080:8080"

    depends_on:
      - postgres
```

---

# Executar Docker

```bash
docker-compose up --build
```

---

# Variáveis Produção

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

# Endpoints Principais

## Registrar Usuário

```http
POST /api/v1/auth
```

```json
{
  "username": "admin",
  "password": "12345",
  "roles": [
    "ADMIN"
  ]
}
```

---

## Buscar Pessoas

```http
GET /api/v1/pessoas/listarTodas
```

---

## Salvar Funcionário

```http
POST /api/v1/funcionarios/salvar
```

```json
{
  "pessoa": {
    "id": 1
  },
  "email": "funcionario@empresa.com",
  "dataAdmissao": "01/01/2025",
  "status": "ATIVO",
  "tipoContrato": "CLT",
  "salarioBase": "3500"
}
```

---

# Segurança Implementada

- JWT Authentication
- BCrypt Password Encoder
- Stateless Session
- HSTS
- CORS
- Swagger JWT
- Protected Endpoints

---

# Melhorias Futuras

- Refresh Token
- Redis Token Revocation
- Rate Limiting
- Flyway
- Kubernetes
- OpenTelemetry
- Grafana
- Prometheus

---

# Collection Postman

Arquivo:

```text
API-GESTAO-RH.postman_collection.json
```

Configurar:

```text
baseURL=http://localhost:8080
```
