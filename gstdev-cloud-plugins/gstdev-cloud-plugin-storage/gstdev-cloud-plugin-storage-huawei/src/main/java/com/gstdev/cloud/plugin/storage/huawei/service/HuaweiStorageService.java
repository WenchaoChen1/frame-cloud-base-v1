// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.huawei.service;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.model.*;
import com.gstdev.cloud.commons.exception.BadRequestException;
import com.gstdev.cloud.plugin.storage.core.model.FileBucket;
import com.gstdev.cloud.plugin.storage.core.model.FileObject;
import com.gstdev.cloud.plugin.storage.core.service.AbstractFileService;
import com.gstdev.cloud.plugin.storage.core.service.StorageService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
@Setter
public class HuaweiStorageService extends AbstractFileService implements StorageService {
  private ObsClient client;

  private String endpoint;
  private String accessKey;
  private String secretKey;

  public HuaweiStorageService(String endpoint, String accessKey, String secretKey) {
    this.endpoint = endpoint;
    this.accessKey = accessKey;
    this.secretKey = secretKey;
  }

  @Override
  public void createBucket(String bucketName) {
    try {
      client = getClient();

      if (!client.headBucket(bucketName)) {
        CreateBucketRequest request = new CreateBucketRequest();
        request.setBucketName(bucketName);
        // 设置桶访问权限为公共读，默认是私有读写
        request.setAcl(AccessControlList.REST_CANNED_PUBLIC_READ);
        // 设置桶的存储类型为归档存储
        //request.setBucketStorageClass(StorageClassEnum.COLD);
        // 设置桶区域位置
        //request.setLocation("bucketlocation");
        // 指定创建多AZ桶，如果不设置，默认创建单AZ桶
        //request.setAvailableZone(AvailableZoneEnum.MULTI_AZ);
        // 创建桶
        client.createBucket(request);
      }
    } catch (ObsException e) {
      // 创建桶失败
      log.info("HTTP Code: " + e.getResponseCode());
      log.info("Error Code:" + e.getErrorCode());
      log.info("Error Message: " + e.getErrorMessage());
      log.info("Request ID:" + e.getErrorRequestId());
      log.info("Host ID:" + e.getErrorHostId());
    } catch (Exception ex) {
      log.info("创建Bucket失败");
    }
  }

  @Override
  public void removeBucket(String bucketName) {
    try {
      getClient().deleteBucket(bucketName);
    } catch (Exception ex) {
      log.info("删除Bucket失败");
    }
  }

  @Override
  public Optional<FileBucket> getBucket(String bucketName) {
    return null;
  }

  @Override
  public List<FileBucket> listBuckets() {
    return null;
  }

  @Override
  public String getObjectURL(String bucketName, String objectName, Integer expires) {
    if (expires <= 0) {
      String[] segments = endpoint.split("://");
      return segments[0] + "://" + bucketName + "." + segments[1]  + "/" + objectName;
    }

    String url = null;

    try {
      TemporarySignatureRequest request = new TemporarySignatureRequest(HttpMethodEnum.GET, expires.longValue());
      request.setBucketName(bucketName);
      request.setObjectKey(objectName);
      TemporarySignatureResponse response = getClient().createTemporarySignature(request);
      url = response.getSignedUrl();
    } catch (Exception ex) {
      log.info("获得FileObject失败");
    }

    return url;
  }

  @Override
  public FileObject stateObjectInfo(String bucketName, String objectName) {
    return null;
  }

  @Override
  public void putObject(String bucketName, String objectName, InputStream stream, long size, String contentType) {
    try {
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentType(contentType);
      getClient().putObject(bucketName, objectName, stream, metadata);
    } catch (Exception ex) {
      log.info("创建FileObject失败", ex);
    }
  }

  @Override
  public void removeObject(String bucketName, String objectName) {
    try {
      getClient().deleteObject(bucketName, objectName);
    } catch (Exception ex) {
      log.info("删除FileObject失败");
    }
  }

  @Override
  public InputStream getObject(String bucketName, String objectName) {
    try {
      ObsObject object = getClient().getObject(bucketName, objectName);
      return object.getObjectContent();
    } catch (Exception ex) {
      log.info("获取FileObject失败");
    }

    return null;
  }

  private ObsClient getClient() {

    if (client == null) {
      try {
        client = new ObsClient(accessKey, secretKey, endpoint);
      } catch (Exception e) {
        throw new BadRequestException(e);
      }
    }
    return client;
  }
}
