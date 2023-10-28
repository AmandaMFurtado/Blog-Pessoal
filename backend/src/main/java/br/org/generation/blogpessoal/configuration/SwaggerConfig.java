package br.org.generation.blogpessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

/**
 * Configurações do Swagger para documentação da API.
 * Esta classe define as informações básicas da API, bem como os padrões de respostas para as operações.
 */
@Configuration
public class SwaggerConfig {


	/**
	 * Configura e retorna uma instância OpenAPI com informações básicas sobre a API do Blog Pessoal.
	 *
	 * @return uma instância configurada do OpenAPI.
	 */
	@Bean
	public OpenAPI springBlogPessoalOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Projeto Blog Pessoal")
				.description("Projeto Blog Pessoal - Generation Brasil")
				.version("v0.0.1").license(new License()
				.name("generation.org.br")
				.url("http://springdoc.org"))
				.contact(new Contact()
				.name("Amanda Marques")
				.url("https://github.com/AmandaMFurtado")
				.email("andaemc@gmail.com")))
				.externalDocs(new ExternalDocumentation()
				.description("Github")
				.url("https://github.com/AmandaMFurtado"));
	}


	/**
	 * Customiza a documentação Swagger/OpenAPI, adicionando respostas padrão para todas as operações.
	 *
	 * @return um customizador OpenApi que define respostas padrão para as operações.
	 */
	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));

			}));
		};
	}

	/**
	 * Cria uma ApiResponse com uma mensagem fornecida.
	 *
	 * @param message mensagem para a descrição da resposta.
	 * @return uma instância de ApiResponse com a mensagem fornecida.
	 */
	private ApiResponse createApiResponse(String message) {

		return new ApiResponse().description(message);

	}

}
