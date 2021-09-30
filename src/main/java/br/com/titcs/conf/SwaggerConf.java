package br.com.titcs.conf;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConf implements WebMvcConfigurer{

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors 
			    .basePackage("br.com.titcs.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiAppnatorInfo())
				.securityContexts(Arrays.asList(securityContext()))
			    .securitySchemes(Arrays.asList(apiKey()));				
	}
	
	private ApiInfo apiAppnatorInfo() {
		return new ApiInfoBuilder()
				.title("By Appnator - Framework acelerador de aplicações WEB/Mobile")
				.description("ARQUITETURA DE REFERÊNCIA")
				.version("1.0.0")
				.contact(new Contact("TITCS", "http://titcs.com.br", "contato@titcs.com.br"))
				.build();
	}	 

	private ApiKey apiKey() { 
	    return new ApiKey("JWT", "Authorization", "header"); 
	}
	
	private SecurityContext securityContext() { 
	    return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
	} 
	
	private List<SecurityReference> defaultAuth() { 
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    authorizationScopes[0] = authorizationScope; 
	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
	}
}
