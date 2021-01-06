package ru.shchekalev.opencodewebtask.models;

public enum Availability {
    AVAILABLE("available"),
    UNAVAILABLE("unavailable");

    private final String status;

    Availability(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
