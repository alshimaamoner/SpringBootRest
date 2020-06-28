package com.example.springMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Locale;

@SpringBootApplication
@EnableSwagger2WebMvc
public class SpringMicroServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringMicroServiceApplication.class, args);
	}
	//Instead of passing language as a parameter using AcceptHeader and in controller using
	//LocalContextHolder.getLocale
	@Bean
    public AcceptHeaderLocaleResolver localResolver(){
		AcceptHeaderLocaleResolver localeResolver=new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	//Instead of Creating Bean to ResourceBuudle add massaged in application.properties

//	@Bean
//	public ResourceBundleMessageSource bundleMessageSource(){
//		ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
//		messageSource.setBasename("messages");
//		return messageSource;
//	}
}
