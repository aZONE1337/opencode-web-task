package ru.shchekalev.opencodewebtask.model.security;

public enum Authority {

    FULL("full"),
    RESTRICTED("restricted");

    private final String authority;

    Authority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
