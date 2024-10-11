// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.core.constant;

/**
 * 错误码常量
 */
public class FileMessageConstant {

  /**
   * 文件上传失败,请检查配置信息
   */
  public static final String ERROR_FILE_UPDATE = "hfle.error.file.upload";
  /**
   * 下载文件失败
   */
  public static final String ERROR_DOWNLOAD_FILE = "hfle.error.download.file";
  /**
   * 删除文件失败
   */
  public static final String ERROR_DELETE_FILE = "hfle.error.delete.file";
  /**
   * 桶不存在
   */
  public static final String BUCKET_NOT_EXISTS = "hfle.error.bucket_not_exists";
  private FileMessageConstant() {
  }
}
