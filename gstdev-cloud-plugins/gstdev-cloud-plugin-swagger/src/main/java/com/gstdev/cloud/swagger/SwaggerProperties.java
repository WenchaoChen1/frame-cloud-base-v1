package com.gstdev.cloud.swagger;


//import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "gstdev.cloud.swagger")
public class SwaggerProperties {

  private String title = SwaggerDefaults.TITLE;

  private String description = SwaggerDefaults.DESCRIPTION;

  private String version = SwaggerDefaults.VERSION;

  private String termsOfServiceUrl = SwaggerDefaults.TERMS_OF_SERVICE_URL;

  private String contactName = SwaggerDefaults.CONTACT_NAME;

  private String contactUrl = SwaggerDefaults.CONTACT_URL;

  private String contactEmail = SwaggerDefaults.CONTACT_EMAIL;

  private String license = SwaggerDefaults.LICENSE;

  private String licenseUrl = SwaggerDefaults.LICENSE_URL;

  private String defaultIncludePattern = SwaggerDefaults.DEFAULT_INCLUDE_PATTERN;

  private String host = SwaggerDefaults.HOST;

  private String[] protocols = SwaggerDefaults.PROTOCOLS;

  private boolean useDefaultResponseMessages = SwaggerDefaults.USE_DEFAULT_RESPONSE_MESSAGES;

  private boolean enabled = SwaggerDefaults.ENABLED;
}
