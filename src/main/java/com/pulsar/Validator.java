package com.pulsar;

public class Validator {

    public void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Некорректное имя пользователя: %s".formatted(name));
        }
    }
}
