// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.data.jpa.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface AbstractMapper<D, E> {

  E toEntity(D dto);

  D toDto(E entity);

  List<E> toEntity(List<D> dtoList);

  List<D> toDto(List<E> entityList);

  default Page<E> toEntity(Page<D> page) {
    List<E> responses = toEntity(page.getContent());
    return new PageImpl<>(responses, page.getPageable(), page.getTotalElements());
  }

  default Page<D> toDto(Page<E> page) {
    List<D> responses = toDto(page.getContent());
    return new PageImpl<>(responses, page.getPageable(), page.getTotalElements());
  }
}
