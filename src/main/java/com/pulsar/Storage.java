package com.pulsar;

public class Storage {

    private Contact[] contacts;
    private int size;

    public Storage() {
        this(100);
    }

    public Storage(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("The size must be greater then 0");
        }
        this.contacts = new Contact[size];
    }
}
