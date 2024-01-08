package com.nxg.payina.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserType implements GrantedAuthority {
    PERSONAL,
    SHOP,
    SUPERMARKET,
    CORPORATE,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

}