//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev Cloud <support@gstdev.com>
//// Copyright (c) 2022-2025 gstdev.com
////
//// ====================================================
//
//package com.gstdev.cloud.oauth2.server.authorization.service;
//
//import com.gstdev.cloud.oauth2.server.authorization.domain.OAuth2Client;
//import com.gstdev.cloud.oauth2.server.authorization.domain.dto.OAuth2ClientDTO;
//import com.gstdev.cloud.oauth2.server.authorization.mapstruct.OAuth2ClientMapper;
//import com.gstdev.cloud.oauth2.server.authorization.repository.OAuth2ClientRepository;
//import com.gstdev.cloud.web.exception.BadRequestException;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.Assert;
//
//import javax.annotation.Resource;
//import java.time.Instant;
//import java.util.Optional;
//
//@Service
////@AllArgsConstructor
//@AutoConfigureAfter(OAuth2ClientRepository.class)
//public class JpaRegisteredClientRepository implements OAuth2ClientService {
////  private final OAuth2ClientRepository oAuth2ClientRepository;
//
//  @Resource
//  private OAuth2ClientMapper oAuth2ClientMapper;
//
//  @Autowired
//  private OAuth2ClientRepository oAuth2ClientRepository;
//
//  @Override
//  public void save(RegisteredClient registeredClient) {
//    Assert.notNull(registeredClient, "registeredClient cannot be null");
//    this.oAuth2ClientRepository.save(OAuth2Client.fromRegisteredClient(registeredClient));
//  }
//
//  @Override
//  public void saveClient(OAuth2ClientDTO client) {
//    OAuth2Client oAuth2Client = client.toClient();
//    oAuth2Client.setClientIdIssuedAt(Instant.now());
//    this.oAuth2ClientRepository.save(oAuth2Client);
//  }
//
//  @Transactional(rollbackFor = Exception.class)
//  @Override
//  public void update(OAuth2ClientDTO client) {
//    String id = client.getId();
//    OAuth2Client flush = this.oAuth2ClientRepository.findById(id).orElseThrow(()-> new BadRequestException("不存在"));
//    OAuth2Client source = client.toClient();
//    // 忽略密码更新
//    source.setClientSecret(null);
//    oAuth2ClientMapper.merge(source, flush);
//    this.oAuth2ClientRepository.saveAndFlush(flush);
//  }
//
//
//  @Override
//  public RegisteredClient findById(String id) {
//    Assert.hasText(id, "id cannot be empty");
//    return Optional.ofNullable(this.oAuth2ClientRepository.searchOAuth2ClientById(id))
//      .map(OAuth2Client::toRegisteredClient)
//      .orElse(null);
//  }
//
//  @Override
//  public RegisteredClient findByClientId(String clientId) {
//    Assert.hasText(clientId, "clientId cannot be empty");
//    return this.oAuth2ClientRepository.findByClientId(clientId).map(OAuth2Client::toRegisteredClient).orElse(null);
//  }
//
//  /**
//   * Page of OAuth2Client.
//   *
//   * @param pageable the pageable
//   * @return the page
//   */
//  @Override
//  public Page<OAuth2Client> page(Pageable pageable) {
//    return this.oAuth2ClientRepository.findAll(pageable);
//  }
//
//  @Override
//  public OAuth2Client findClientById(String id) {
//    return this.oAuth2ClientRepository.searchOAuth2ClientById(id);
//  }
//
//  @Override
//  public void removeByClientId(String id) {
//    this.oAuth2ClientRepository.deleteById(id);
//  }
//}
