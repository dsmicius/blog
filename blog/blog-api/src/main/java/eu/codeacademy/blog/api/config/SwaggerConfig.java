package eu.codeacademy.blog.api.config;

import eu.codeacademy.blog.commons.swagger.annotation.OpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(OpenApi.class))
                .build()
                .securitySchemes(List.of(jwtAuthScheme()))
                .securityContexts(List.of(jwtAuthContext()));
    }

    private SecurityContext jwtAuthContext() {
        return SecurityContext.builder()
                .securityReferences(jwtAuthReference())
                .build();
    }

    private List<SecurityReference> jwtAuthReference() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
    }

    private SecurityScheme jwtAuthScheme() {
        return new ApiKey("JWT", HttpHeaders.AUTHORIZATION, "header");
    }

    private static ApiInfo getInfo() {
        return new ApiInfo(
                "Blog RestFull Api Documentation",
                "This is simple documentation using swsagger and springFox",
                "0.0.1",
                "Blog term URL",
                new Contact("Dominykas Šmičius", "www.dsmicius.com", "dsmicius@gmail.com"),
                "Dominykas licence",
                "Licence URL",
                Collections.emptyList()
        );
    }
}

