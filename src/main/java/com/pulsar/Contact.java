package com.pulsar;

public record Contact(String name,
                      String phoneNumber) {

    @Override
    public String toString() {
        return "%s - %s".formatted(name, phoneNumber);
    }
}
