// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.server.authorization.service;

import com.gstdev.cloud.oauth2.server.authorization.domain.OAuth2Client;
import com.gstdev.cloud.oauth2.server.authorization.domain.dto.OAuth2ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

public interface OAuth2ClientService extends RegisteredClientRepository {

  /**
   * Save client.
   *
   * @param client the client
   */
  void saveClient(OAuth2ClientDTO client);

  /**
   * Update.
   *
   * @param client the client
   */
  void update(OAuth2ClientDTO client);

  /**
   * Page page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<OAuth2Client> page(Pageable pageable);

  /**
   * Find client by id o auth 2 client.
   *
   * @param id the id
   * @return the o auth 2 client
   */
  OAuth2Client  findClientById(String id);

  /**
   * Remove by client id.
   *
   * @param id the id
   */
  void removeByClientId(String id);
}
