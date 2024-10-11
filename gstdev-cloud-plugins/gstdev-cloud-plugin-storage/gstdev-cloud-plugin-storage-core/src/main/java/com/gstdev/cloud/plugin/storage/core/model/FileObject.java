// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.core.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileObject implements Serializable {

  private static final long serialVersionUID = -2733279188828202835L;

  private String hash;

  private String bucketName;

  private String name;

  private String originalName;

  private String link;

  private String contentType;

  private long length;

  private String etag;
}
