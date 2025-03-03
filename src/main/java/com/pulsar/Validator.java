package com.pulsar;

public class Validator {

    private static final String PHONE_NUMBER_PATTERN = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";

    public void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Некорректное имя пользователя: %s".formatted(name));
        }
    }

    public void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches(PHONE_NUMBER_PATTERN)) {
            throw new IllegalArgumentException("Неверный формат номера телефона: %s".formatted(phoneNumber));
        }
    }
}
