package com.cornerstone.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author: liyl
 * @date: 2018/2/7 上午11:27
 * @since 1.0.0
 */
public class GrantedAuthorityImpl implements GrantedAuthority{
    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
