package ru.shchekalev.opencodewebtask.models;

public enum AnswerStatus {
    NOT_STARTED("not started"),
    IN_PROCESS("in process"),
    COMPLETED("completed");

    private final String status;

    AnswerStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
