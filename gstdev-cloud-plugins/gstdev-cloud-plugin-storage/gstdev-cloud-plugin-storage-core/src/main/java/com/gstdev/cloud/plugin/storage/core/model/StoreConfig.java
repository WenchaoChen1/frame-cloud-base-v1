// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.core.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description
 */
@Data
public class StoreConfig {

  @ApiModelProperty("存储类型")
  private Integer storageType;
  @ApiModelProperty("绑定域名")
  private String domain;
  @ApiModelProperty("EndPoint")
  private String endPoint;
  @ApiModelProperty("AccessKeyId")
  private String accessKeyId;
  @ApiModelProperty("AccessKeySecret")
  private String accessKeySecret;
  @ApiModelProperty("腾讯云AppId")
  private Integer appId;
  @ApiModelProperty("腾讯云COS所属地区")
  private String region;
  @ApiModelProperty("默认标识，0:不启用，1:启用")
  private Integer defaultFlag;
  @ApiModelProperty("租户ID")
  private Long tenantId;
  @ApiModelProperty("bucket权限控制")
  private String accessControl;
  @ApiModelProperty("bucket前缀")
  private String bucketPrefix;
  @ApiModelProperty("存储编码")
  private String storageCode;
  @ApiModelProperty("文件名前缀策略")
  private String prefixStrategy;
  @ApiModelProperty("自动创建bucket，0:不启用，1:启用")
  private Integer createBucketFlag;
}
