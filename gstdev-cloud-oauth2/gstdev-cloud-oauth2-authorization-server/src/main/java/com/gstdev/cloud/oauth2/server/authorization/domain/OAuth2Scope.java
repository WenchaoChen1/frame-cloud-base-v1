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
@IdClass(OAuth2Scope.OAuth2ScopeId.class)
@Getter
@Setter
@ToString
@Table(name = "oauth2_scope")
public class OAuth2Scope implements Serializable {
    private static final long serialVersionUID = 6603836530809864931L;
    @Id
    @Column(name = "client_id", insertable = false, updatable = false)
    private String clientId;
    @Id
    private String scope;
    private String description;

    @Data
    public static class OAuth2ScopeId implements Serializable {
        private static final long serialVersionUID = 1991088202139468930L;
        private String clientId;
        private String scope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OAuth2Scope that = (OAuth2Scope) o;
        return clientId != null && Objects.equals(clientId, that.clientId)
                && scope != null && Objects.equals(scope, that.scope);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, scope);
    }
}
