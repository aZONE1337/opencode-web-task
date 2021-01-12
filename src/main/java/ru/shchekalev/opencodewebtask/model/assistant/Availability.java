package ru.shchekalev.opencodewebtask.model.assistant;

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
