package com.gstdev.cloud.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;

@Slf4j
@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(prefix = "gstdev.cloud.swagger", value = "enabled", matchIfMissing = true)
@EnableOpenApi
public class SwaggerAutoConfiguration {
  private final SwaggerProperties properties;

  public SwaggerAutoConfiguration(SwaggerProperties properties) {
    this.properties = properties;
  }

  @Bean
  public Docket swaggerApiDocket() {
    // @formatter:off
    ApiInfo apiInfo = new ApiInfoBuilder()
      .title(properties.getTitle())
      .description(properties.getDescription())
      .version(properties.getVersion())
      .contact(new Contact(properties.getContactName(), properties.getContactUrl(), properties.getContactEmail()))
      .termsOfServiceUrl(properties.getTermsOfServiceUrl())
      .build();
    // @formatter:on

    Docket docket = createDocket();

    // @formatter:off
    docket.apiInfo(apiInfo)
      .useDefaultResponseMessages(properties.isUseDefaultResponseMessages())
      .host(properties.getHost())
      .protocols(new HashSet<>(Arrays.asList(properties.getProtocols())))
      .forCodeGeneration(true)
      .directModelSubstitute(ByteBuffer.class, String.class)
      .genericModelSubstitutes(ResponseEntity.class)
      .select()
      .paths(PathSelectors.any())
      .build();
    // @formatter:on

    return docket;
  }

  @Bean
  public ViewResolver getViewResolver() {
    log.info("[Riching Cloud] |- Swagger start.");
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/");
    resolver.setSuffix(".html");

    return resolver;
  }

  private Docket createDocket() {
    return new Docket(DocumentationType.OAS_30);
  }
}
