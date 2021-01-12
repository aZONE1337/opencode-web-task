package ru.shchekalev.opencodewebtask.model.assistant;

import java.util.Set;

public enum Role {
    USER(Set.of(Authority.READ)),
    ADMIN(Set.of(Authority.CREATE, Authority.READ, Authority.UPDATE, Authority.DELETE));

    private final Set<Authority> authorities;

    Role(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }
}
