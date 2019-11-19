package br.com.sicredi.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket swaggerCooperativeVoteSystemApi10() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("cooperative-vote-system-api-1.0.0").select()
				.apis(RequestHandlerSelectors.basePackage("br.com.sicredi.system.controller"))
				.paths(PathSelectors.any()).build()
				.apiInfo(new ApiInfoBuilder()
						.version("1.0.0")
						.title("Cooperative Vote System API")
						.description("Documentation cooperative vote system API v1.0.0").build());
	}
}
