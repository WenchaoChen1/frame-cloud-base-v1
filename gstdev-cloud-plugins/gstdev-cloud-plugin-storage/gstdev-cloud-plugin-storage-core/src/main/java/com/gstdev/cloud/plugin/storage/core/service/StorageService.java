// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.core.service;


import com.gstdev.cloud.plugin.storage.core.model.FileBucket;
import com.gstdev.cloud.plugin.storage.core.model.FileObject;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface StorageService {

  void createBucket(String bucketName);

  void removeBucket(String bucketName);

  Optional<FileBucket> getBucket(String bucketName);

  List<FileBucket> listBuckets();

  String getObjectURL(String bucketName, String objectName, Integer expires);

  FileObject stateObjectInfo(String bucketName, String objectName);

  void putObject(String bucketName, String objectName, InputStream stream, long size, String contentType);

  void removeObject(String bucketName, String objectName);

  InputStream getObject(String bucketName, String objectName);

}
