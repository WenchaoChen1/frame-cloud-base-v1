// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.server.authorization.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@IdClass(RedirectUri.RedirectUriId.class)
@Table(name = "redirect_uri")
public class RedirectUri implements Serializable{
    private static final long serialVersionUID = 9221146008979764156L;
    @Id
    @Column(name="client_id",insertable = false,updatable = false)
    private String clientId;
    @Id
    private String redirectUri;

    @Data
    public static class RedirectUriId implements Serializable {
        private static final long serialVersionUID = -8081989338438799123L;
        private String clientId;
        private String redirectUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RedirectUri that = (RedirectUri) o;
        return clientId != null && Objects.equals(clientId, that.clientId)
                && redirectUri != null && Objects.equals(redirectUri, that.redirectUri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, redirectUri);
    }
}
