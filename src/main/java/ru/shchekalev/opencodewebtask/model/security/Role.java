package ru.shchekalev.opencodewebtask.model.security;

import java.util.Set;

public enum Role {

    USER(Set.of(Authority.RESTRICTED)),
    ADMIN(Set.of(Authority.FULL));

    private final Set<Authority> authorities;

    Role(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }
}
