//// ====================================================
////
//// This file is part of the Riching Cloud Platform.
////
//// Create by Riching Tech <support@richingtech.com>
//// Copyright (c) 2020-2025 richingtech.com
////
//// ====================================================
//
//package com.gstdev.cloud.commons.exception;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import feign.FeignException;
//import feign.Response;
//import feign.Util;
//import feign.codec.ErrorDecoder;
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//
//@AllArgsConstructor
//@Configuration
//public class FeignExceptionDecoder implements ErrorDecoder {
//
//  private final ObjectMapper objectMapper;
//
//  @Override
//  public Exception decode(String methodKey, Response response) {
//
//    try {
//      if (response.body() != null) {
//        @SuppressWarnings("all")
//        String jsonStr = Util.toString(response.body().asReader());
//
//        JsonNode jsonNode = objectMapper.readTree(jsonStr);
//        String message = jsonNode.get("message").asText();
//
//        return new BadRequestException(message);
//      }
//
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//
//    return FeignException.errorStatus(methodKey, response);
//  }
//}
