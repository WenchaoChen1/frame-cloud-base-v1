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
 * 文件服务常量类
 */
public class FileConstant {
  /**
   * 默认文件分隔符
   */
  public static final String DIRECTORY_SEPARATOR = "/";
  /**
   * 默认Multi上传文件类型
   */
  public static final String DEFAULT_MULTI_TYPE = "application/octet-stream";
  /**
   * 代理地址桶名占位符
   */
  public static final String DOMAIN_BUCKET_NAME = "{bucketName}";

  private FileConstant() {
  }

  /**
   * Minio桶权限
   */
  public static final class MinioAccessControl {
    public static final String NONE = "none";
    public static final String READ_ONLY = "read-only";
    public static final String WRITE_ONLY = "write-only";
    public static final String READ_WRITE = "read-write";
    private MinioAccessControl() {
    }
  }

  /**
   * 阿里桶权限
   */
  public static final class AliyunAccessControl {
    public static final String DEFAULT = "default";
    public static final String PRIVATE = "private";
    public static final String PUBLIC_READ = "public-read";
    public static final String PUBLIC_READ_WRITE = "public-read-write";
    private AliyunAccessControl() {
    }
  }

  /**
   * 华为桶权限
   */
  public static final class HuaweiAccessControl {
    public static final String PRIVATE = "private";
    public static final String PUBLIC_READ = "public-read";
    public static final String PUBLIC_READ_WRITE = "public-read-write";
    private HuaweiAccessControl() {
    }
  }

  /**
   * 七牛桶权限
   */
  public static final class QcloudAccessControl {
    public static final String DEFAULT = "default";
    public static final String PRIVATE = "private";
    public static final String PUBLIC_READ = "public-read";
    public static final String PUBLIC_READ_WRITE = "public-read-write";
    private QcloudAccessControl() {
    }
  }

  /**
   * 本地存储桶权限
   */
  public static final class LocalAccessController {
    public static final String PUBLIC_READ_WRITE = "public-read-write";

    private LocalAccessController() {
    }
  }

  /**
   * AWS S3和京东权限
   */
  public static final class AwsAccessControl {
    public static final String PRIVATE = "private";
    public static final String PUBLIC_READ = "public-read";
    public static final String PUBLIC_READ_WRITE = "public-read-write";
    private AwsAccessControl() {
    }
  }

  /**
   * 百度 桶权限
   */
  public static final class BaiduAccessControl {
    public static final String PRIVATE = "private";
    public static final String PUBLIC_READ = "public-read";
    public static final String PUBLIC_READ_WRITE = "public-read-write";
    private BaiduAccessControl() {
    }
  }

  /**
   * 微软 容器权限
   */
  public static final class MicrosoftControl {
    /**
     * 禁止匿名读
     */
    public static final String OFF = "off";
    /**
     * 允许匿名读(容器级别)
     */
    public static final String CONTAINER = "container";
    /**
     * 允许匿名读(blob级别)
     */
    public static final String BLOB = "blob";
    private MicrosoftControl() {
    }
  }

  public static final class Protocol {
    public static final String HTTP = "http";
    public static final String HTTPS = "https";
    private Protocol() {
    }
  }
}
