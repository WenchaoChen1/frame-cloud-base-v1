// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.data.jpa.repository;

import com.gstdev.cloud.data.commons.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AbstractRepository<E extends AbstractEntity, ID> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {

}
