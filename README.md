# Sistema de Login e Usuários

Este projeto é uma aplicação Java baseada no framework Spring Boot, que fornece endpoints para o gerenciamento de autenticação (login) e usuários. Utiliza Spring Security com JWT para a segurança, banco de dados H2 para armazenamento, Lombok para redução de boilerplate e Mockito para testes.

## Estrutura do Projeto

O projeto possui dois principais controladores (controllers):

### Login Controller

Responsável por operações relacionadas ao login.

#### Endpoints:

- **POST /login/create**
   - Cria um novo login.
   - Parâmetros: Objeto `CreateLoginDTO` no corpo da requisição.
   - Resposta: Retorna um objeto `LoginDTO` e gera um token JWT.

- **POST /login**
   - Realiza o login.
   - Parâmetros: Objeto `AuthenticatedDTO` no corpo da requisição.
   - Resposta: Retorna um token JWT.

### User Controller

Lida com operações relacionadas a usuários.

#### Endpoints:

- **POST /users**
   - Cria um novo usuário.
   - Parâmetros: Objeto `CreateUserDto` no corpo da requisição.
   - Resposta: Retorna um objeto `UserDTO` e gera um link para o novo usuário criado.

- **GET /users/{id}**
   - Obtém informações de um usuário pelo ID.
   - Parâmetros: ID do usuário na URL.
   - Resposta: Retorna informações do usuário.

- **GET /users**
   - Lista usuários paginados.
   - Parâmetros: Parâmetros de paginação na URL.
   - Resposta: Retorna uma página de usuários.

- **DELETE /users/{id}**
   - Exclui um usuário pelo ID.
   - Parâmetros: ID do usuário na URL.
   - Resposta: Retorna uma resposta vazia.

- **PUT /users**
   - Atualiza informações de um usuário.
   - Parâmetros: Objeto `UpdatedUserDataDto` no corpo da requisição.
   - Resposta: Retorna um objeto `DetailsUserDTO` com as informações atualizadas do usuário.

## Documentação Swagger

A documentação Swagger pode ser acessada em [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Security com JWT
- H2 Database
- Lombok
- Spring Doc
- Mockito

## Instruções de Instalação

1. Clone o repositório.
2. Execute a aplicação usando sua IDE ou ferramenta de build preferida.

## Configurações Adicionais

- Certifique-se de ter o Java instalado.
- Configure corretamente as dependências no arquivo de configuração.

## Contribuição

Sinta-se à vontade para contribuir.

## Contato

Para dúvidas ou mais informações, entre em contato pelo email [alexandre.cst.macedo@gmail.com]()

---
