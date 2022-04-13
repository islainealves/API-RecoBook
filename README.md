# Recobook - API de recomendação de livros

Link da API no heroku:
https://recobook-web.herokuapp.com/livro

## Tecnologias usadas
Framework Spring Boot

Linguagem Java

Banco de dados PostgreSQL

Framework Swagger para documentação

Bean Validation para fazer as validações


## Passo a passo para subir a api localmente:
- Clonar o repositório:
git clone https://github.com/islainealves/API-RecoBook.git
- Abrir o projeto com a IDE de preferência
Recomendação: IntelliJ IDEA ou Eclipse IDE
- Adicionar as configurações do seu banco de dados no arquivo application.properties localizado em /src/main/resources/
- Criar o a database recobook no postgresql
- Executar a ApiApplication.java localizada em /src/main/java/ufg/br/
- Acessar pelo navegador: http://localhost:8080/livro
- Documentação Swagger para fazer as requests: http://localhost:8080/swagger-ui.html

API pronta, agora é só fazer as requisições HTTP.

Recomendações: Postman ou o web client (https://github.com/islainealves/ClienteRecobook.git)

## Link do client no github pages:
https://islainealves.github.io/ClienteRecobook/

## Link do client no github:
https://github.com/islainealves/ClienteRecobook.git

Disciplina: Desenvolvimento Full-Stack

![recobook](https://user-images.githubusercontent.com/45128957/163187618-e553828f-2803-414c-ba1d-4a4bb9487ea8.png)
