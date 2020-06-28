package com.example.springMicroService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.w3c.dom.DocumentType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.*;
import java.util.function.Predicate;

//Configuration
//Enable Swagger
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
    public static final Contact DEFAULT_CONTACT = new Contact("shimaa", "https://www.linkedin.com/in/alshimaa-elnady", "alshimaaelnady@gmail.com");
    public static final ApiInfo  DEFAULT_API_INFO = new ApiInfo
                ("Awesome Api Documentation", "Awesome Api Description",
                    "1.0", "urn:tos", DEFAULT_CONTACT.toString(),
                    "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
    private static final Set<String> DEFAULT_PRODUCER_CONSUMER = new HashSet<String>(Arrays.asList("application/json","application/xml") );

    // Swagger 2-->version
    //All the path
    //all the apis

    @Primary
    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }

    //define Bean
    @Bean
    public Docket api() {
        //return new Docket(DocumentationType.SWAGGER_2);
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.springMicroService.controller"))
//                .build();
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO ).consumes(DEFAULT_PRODUCER_CONSUMER).produces(DEFAULT_PRODUCER_CONSUMER);
    }

    // Describe your apis
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(" Rest APIs")
                .description("This page lists all the rest apis for Test App.")
                .version("1.0-SNAPSHOT")
                .build();
    }
}


