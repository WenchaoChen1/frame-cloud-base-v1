// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.aliyun.service;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.gstdev.cloud.plugin.storage.core.service.StorageService;
import com.gstdev.cloud.plugin.storage.core.model.FileBucket;
import com.gstdev.cloud.plugin.storage.core.model.FileObject;
import com.gstdev.cloud.plugin.storage.core.service.AbstractFileService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AliyunStorageService extends AbstractFileService implements StorageService {

  final static Logger log = Logger.getLogger(AliyunStorageService.class.getName());

  private String endpoint;
  private String accessKeyId;
  private String accessKeySecret;

  @Override
  public void createBucket(String bucketName) {
    OSS ossClient = getClient();

    try {
      if (ossClient.doesBucketExist(bucketName)) {
        log.info(bucketName + "存储空间已经存在");
      } else {
        ossClient.createBucket(bucketName);
        ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
      }
    } catch (OSSException oe) {
      oe.printStackTrace();
    } catch (ClientException ce) {
      ce.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ossClient.shutdown();
    }
  }

  @Override
  public void removeBucket(String bucketName) {
    OSS ossClient = getClient();

    try {
      if (ossClient.doesBucketExist(bucketName)) {
        ossClient.deleteBucket(bucketName);
      } else {
        log.info(bucketName + "存储空间不存在");
      }
    } catch (OSSException oe) {
      oe.printStackTrace();
    } catch (ClientException ce) {
      ce.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ossClient.shutdown();
    }
  }

  @Override
  public Optional<FileBucket> getBucket(String bucketName) {
    OSS ossClient = getClient();

    try {
      if (ossClient.doesBucketExist(bucketName)) {
        BucketInfo ossBucketInfo = ossClient.getBucketInfo(bucketName);
        Bucket ossBucket = ossBucketInfo.getBucket();

        return Optional.of(FileBucket.builder().name(ossBucket.getName()).build());
      } else {
        // Log Exception
      }
    } catch (OSSException oe) {
      oe.printStackTrace();
    } catch (ClientException ce) {
      ce.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ossClient.shutdown();
    }

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
      return segements[0] + "://" + bucketName + "." + segements[1] + "/" + objectName;
    }

    OSS ossClient = getClient();

    try {
      if (ossClient.doesObjectExist(bucketName, objectName)) {
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, new Date(new Date().getTime() + expires));
        return url.toString();
      } else {
        log.info(objectName + "文件不存在");
      }
    } catch (OSSException oe) {
      oe.printStackTrace();
    } catch (ClientException ce) {
      ce.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ossClient.shutdown();
    }

    return null;
  }

  @Override
  public FileObject stateObjectInfo(String bucketName, String objectName) {
    return null;
  }

  @Override
  public void putObject(String bucketName, String objectName, InputStream stream, long size, String contentType) {
    OSS ossClient = getClient();

    try {
      ObjectMetadata objectMetadata = new ObjectMetadata();
      objectMetadata.setContentType(contentType.replaceAll("image/jpeg", "image/jpg"));
      objectMetadata.setContentLength(size);

      ossClient.putObject(bucketName, objectName, stream, objectMetadata);
      ossClient.setObjectAcl(bucketName, objectName, CannedAccessControlList.PublicRead);
    } catch (OSSException oe) {
      oe.printStackTrace();
    } catch (ClientException ce) {
      ce.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ossClient.shutdown();
    }
  }

  @Override
  public void removeObject(String bucketName, String objectName) {
    OSS ossClient = getClient();

    try {
      if (ossClient.doesObjectExist(bucketName, objectName)) {
        ossClient.deleteObject(bucketName, objectName);
      } else {
        log.info(objectName + "文件不存在");
      }
    } catch (OSSException oe) {
      oe.printStackTrace();
    } catch (ClientException ce) {
      ce.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ossClient.shutdown();
    }
  }

  @Override
  public InputStream getObject(String bucketName, String objectName) {
    return null;
  }

  private OSS getClient() {
    return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
  }
}
