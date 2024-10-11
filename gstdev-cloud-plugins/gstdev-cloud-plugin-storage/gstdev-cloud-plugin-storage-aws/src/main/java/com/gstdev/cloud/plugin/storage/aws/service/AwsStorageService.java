// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.gstdev.cloud.commons.exception.BadRequestException;
import com.gstdev.cloud.plugin.storage.core.model.FileBucket;
import com.gstdev.cloud.plugin.storage.core.model.FileObject;
import com.gstdev.cloud.plugin.storage.core.service.AbstractFileService;
import com.gstdev.cloud.plugin.storage.core.service.StorageService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
@Setter
public class AwsStorageService extends AbstractFileService implements StorageService {
//  private AwsConfig awsConfig;

  private String endpoint;
  private String accessKey;
  private String secretKey;

  public AwsStorageService(String endpoint, String accessKey, String secretKey) {
    this.endpoint = endpoint;
    this.accessKey = accessKey;
    this.secretKey = secretKey;
  }

  @Override
  public void createBucket(String bucketName) {
//    try {
//      client = getClient();
//
//      if (!client.bucketExists(bucketName)) {
//        client.makeBucket(bucketName);
//
//        String bucketPolicy = "{\n" +
//          "  \"Statement\": [\n" +
//          "    {\n" +
//          "      \"Action\": [\"s3:GetBucketLocation\", \"s3:ListBucket\"],\n" +
//          "      \"Effect\": \"Allow\",\n" +
//          "      \"Principal\": \"*\",\n" +
//          "      \"Resource\": \"arn:aws:s3:::" + bucketName +
//          "    },\n" +
//          "    {\n" +
//          "      \"Action\": \"s3:GetObject\",\n" +
//          "      \"Effect\": \"Allow\",\n" +
//          "      \"Principal\": \"*\",\n" +
//          "      \"Resource\": \"arn:aws:s3:::" + bucketName + "/*\"\n" +
//          "    }\n" +
//          "  ],\n" +
//          "  \"Version\": \"2012-10-17\"\n" +
//          "}\n";
//        client.setBucketPolicy(bucketName, bucketPolicy);
//      }
//    } catch (Exception ex) {
//      log.info("创建Bucket失败");
//    }
  }

  @Override
  public void removeBucket(String bucketName) {
//    try {
//      getClient().removeBucket(bucketName);
//    } catch (Exception ex) {
//      log.info("删除Bucket失败");
//    }
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
      String[] segements = endpoint.split("://");
      return segements[0] + "://" + segements[1] + "/" + bucketName + "/" + objectName;
    }

    String url = null;

    try {
//      url = getClient().presignedGetObject(bucketName, objectName);
      url = getClient().generatePresignedUrl(new GeneratePresignedUrlRequest(bucketName, objectName)).toString();
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
//      PutObjectOptions options = new PutObjectOptions(size, -1);
//      options.setContentType(contentType);
//
//      getClient().putObject(bucketName, objectName, stream, options);
//      String key = BUCKET_NAME_URL_STRING+objectName;
      PutObjectRequest request = new PutObjectRequest(bucketName, objectName, stream,null);
      request.withCannedAcl(CannedAccessControlList.PublicRead);
      PutObjectResult putObjectResult = getClient().putObject(request);
    } catch (Exception ex) {
      log.info("创建FileObject失败", ex);
    }
  }

  @Override
  public void removeObject(String bucketName, String objectName) {
    try {
      getClient().deleteObject(new DeleteObjectRequest(bucketName, objectName));
//      getClient().removeObject(bucketName, objectName);
    } catch (Exception ex) {
      log.info("删除FileObject失败");
    }
  }

  @Override
  public InputStream getObject(String bucketName, String objectName) {
    try {
      return getClient().getObject(new GetObjectRequest(bucketName,objectName)).getObjectContent();
//      return getClient().getObject(bucketName, objectName);
    } catch (Exception ex) {
      log.info("删除FileObject失败");
    }

    return null;
  }

//  private MinioClient getClient() {
//
//    if (client == null) {
//      try {
//        client = new MinioClient(endpoint, accessKey, secretKey);
//      } catch (Exception e) {
//        throw new BadRequestException(e);
//      }
//    }
//    return client;
//  }
  private AmazonS3 getClient() {
    return AmazonS3ClientBuilder.standard()
      .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
      .withRegion(Regions.US_EAST_1)
      .build();
  }
}
