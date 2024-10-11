// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.config;

import com.gstdev.cloud.plugin.storage.minio.service.MinioStorageService;
import com.gstdev.cloud.plugin.storage.core.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MinioStorageProperties.class)
@ConditionalOnProperty(prefix = "riching.cloud.storage.minio", name = {"endpoint", "accessKey", "secretKey"})
public class MinioStorageAutoConfiguration {

  @Autowired
  private MinioStorageProperties properties;

  @Bean
  @ConditionalOnMissingBean
  public StorageService storageService() {
    return new MinioStorageService(properties.getEndpoint(), properties.getAccessKey(), properties.getSecretKey());
  }
}
