package eu.codeacademy.blog.api.config;

import eu.codeacademy.blog.commons.swagger.annotation.OpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(getInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(OpenApi.class))
                .build();
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

