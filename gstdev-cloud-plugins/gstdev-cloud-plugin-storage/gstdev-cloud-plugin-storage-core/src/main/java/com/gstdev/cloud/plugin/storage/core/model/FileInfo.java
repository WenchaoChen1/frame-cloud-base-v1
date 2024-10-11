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
public class FileInfo {

  @ApiModelProperty("附件集UUID")
  private String attachmentUuid;
  @ApiModelProperty("上传目录")
  private String directory;
  @ApiModelProperty("文件地址")
  private String fileUrl;
  @ApiModelProperty("文件类型")
  private String fileType;
  @ApiModelProperty("文件名称")
  private String fileName;
  @ApiModelProperty("文件大小")
  private Long fileSize;
  @ApiModelProperty("文件目录")
  private String bucketName;
  @ApiModelProperty("对象KEY")
  private String fileKey;
  @ApiModelProperty("租户Id")
  private Long tenantId;
  @ApiModelProperty("文件MD5")
  private String md5;
  @ApiModelProperty("存储编码")
  private String storageCode;
  @ApiModelProperty("来源类型")
  private String sourceType;
  @ApiModelProperty("服务器编码")
  private String serverCode;
}
