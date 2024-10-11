// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.server.authorization.feign;

import com.gstdev.cloud.commons.web.Result;
import com.gstdev.cloud.oauth2.server.authorization.constant.OAuth2Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = OAuth2Constants.DEFAULT_USER_DETAIL_URI)
public interface RemoteUserDetailsService {

  @GetMapping("/system/users/userInfo")
  Result<Object> getUserInfoById(@RequestParam("id") String id);
}
