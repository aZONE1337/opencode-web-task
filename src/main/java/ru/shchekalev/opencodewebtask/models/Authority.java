package ru.shchekalev.opencodewebtask.models;

public enum Authority {
    CREATE("create"),
    READ("read"),
    UPDATE("update"),
    DELETE("delete");

    private final String authority;

    Authority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
