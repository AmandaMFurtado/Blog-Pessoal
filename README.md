# Blog Pessoal

Esse projeto é uma plataforma de blog pessoal onde os usuários podem criar, atualizar e gerenciar postagens. Os temas para postagens também podem ser gerenciados, e os usuários têm a capacidade de se autenticar na plataforma.

## Índice

- [Tecnologias](#tecnologias)
- [Instalação e Uso](#instalação-e-uso)
- [Endpoints](#endpoints)
- [Contribuição](#contribuição)

## Tecnologias

- Java
- Spring Boot
- JPA/Hibernate
- MySQL
- Swagger (OpenAPI)
- Spring Security

## Instalação e Uso

1. **Clone este repositório**
    ```bash
    git clone https://github.com/AmandaMFurtado/blogpessoal.git
    ```

2. **Navegue até o diretório e execute o projeto usando o Maven**
    ```bash
    cd blogpessoal
    ./mvnw spring-boot:run
    ```

3. **Acesso Local**
   O servidor iniciará, e a aplicação estará disponível em `http://localhost:8080`.

## Endpoints

- `GET /postagens`: Lista todas as postagens.
- `GET /postagens/{id}`: Obtém uma postagem por ID.
- `POST /postagens`: Cria uma nova postagem.
- ... (outros endpoints relacionados a postagens, temas e usuários)

Para uma lista completa e detalhada dos endpoints, consulte a documentação Swagger disponível em `http://localhost:8080/swagger-ui.html` após iniciar a aplicação.

## Contribuição

1. **Fork esse repositório**
2. **Crie sua feature branch** 
    ```bash
    git checkout -b minha-feature
    ```
3. **Commit suas mudanças** 
    ```bash
    git commit -m 'feature: Minha nova feature'
    ```
4. **Push para a branch** 
    ```bash
    git push origin minha-feature
    ```

Antes de enviar o Pull Request, certifique-se de que a aplicação ainda está funcionando corretamente.


---

Desenvolvido com ♥ por [Amanda Marques](https://github.com/AmandaMFurtado)
