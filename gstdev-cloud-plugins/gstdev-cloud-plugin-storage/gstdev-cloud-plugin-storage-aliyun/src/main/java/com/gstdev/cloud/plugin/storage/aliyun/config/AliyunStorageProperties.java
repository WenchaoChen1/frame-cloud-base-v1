// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.aliyun.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "gstdev.cloud.storage.aliyun", ignoreUnknownFields = true)
public class AliyunStorageProperties {

  private String endpoint;

  private String accessKeyId;

  private String accessKeySecret;
}
