package ru.shchekalev.opencodewebtask.model.assistant;

public enum QuestionType {
    SINGLE("single"),
    MULTI("multi");

    private final String type;

    QuestionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
