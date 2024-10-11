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
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@IdClass(OAuth2GrantType.GrantTypeId.class)
@Table(name = "oauth2_grant_type")
public class OAuth2GrantType implements Serializable {
    private static final long serialVersionUID = -6157485899335872648L;
    @Id
    @Column(name = "client_id", insertable = false, updatable = false)
    private String clientId;
    @Id
    private String grantTypeName;

    /**
     * The type Grant type id.
     */
    @Data
    public static class GrantTypeId implements Serializable {
        private static final long serialVersionUID = 4877568519791270151L;
        private String clientId;
        private String grantTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OAuth2GrantType that = (OAuth2GrantType) o;
        return clientId != null && Objects.equals(clientId, that.clientId)
                && grantTypeName != null && Objects.equals(grantTypeName, that.grantTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, grantTypeName);
    }

    /**
     * To grant type authorization grant type.
     *
     * @return the authorization grant type
     */
    public AuthorizationGrantType toGrantType() {
        return new AuthorizationGrantType(grantTypeName);
    }

}
