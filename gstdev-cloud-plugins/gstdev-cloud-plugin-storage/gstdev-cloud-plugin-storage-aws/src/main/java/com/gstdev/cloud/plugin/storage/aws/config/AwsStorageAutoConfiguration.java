// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.aws.config;

import com.gstdev.cloud.plugin.storage.aws.config.AwsStorageProperties;
import com.gstdev.cloud.plugin.storage.core.service.StorageService;
import com.gstdev.cloud.plugin.storage.minio.service.AwsStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AwsStorageProperties.class)
@ConditionalOnProperty(prefix = "gstdev.cloud.storage.aws", name = {"endpoint", "accessKey", "secretKey"})
public class AwsStorageAutoConfiguration {

  @Autowired
  private AwsStorageProperties properties;

  @Bean
  @ConditionalOnMissingBean
  public StorageService storageService() {
    return new AwsStorageService(properties.getEndpoint(), properties.getAccessKey(), properties.getSecretKey());
  }
}
