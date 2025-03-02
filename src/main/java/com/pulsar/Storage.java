package com.pulsar;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class Storage {

    private Contact[] contacts;
    private int size;
    private static final int INDEX_OCCUPIED = -1;

    public Storage() {
        this(100);
    }

    public Storage(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("The size must be greater then 0");
        }
        this.contacts = new Contact[size];
    }

    public boolean add(String name, String phoneNumber) {
        if (size == contacts.length) {
            return false;
        }

        int index = findFreeIndex();
        if (index != INDEX_OCCUPIED) {
            Contact contact = new Contact(name, phoneNumber);
            contacts[index] = contact;
            size++;
            return true;
        }

        return false;
    }

    public void delete(String name) {
        for (int i = 0; i < contacts.length; i++) {
            Contact contact = contacts[i];
            if (contact != null && contact.getName().equals(name)) {
                contacts[i] = null;
                break;
            }
        }
    }

    public Optional<String> findContact(String name) {
        return Arrays.stream(contacts)
                .filter(Objects::nonNull)
                .filter(contact -> contact.getName().equals(name))
                .map(Contact::getPhoneNumber)
                .findFirst();
    }

    private int findFreeIndex() {
        int index = INDEX_OCCUPIED;

        for (int i = 0; i < contacts.length; i++) {
            if (contacts[i] == null) {
                index = i;
                break;
            }
        }

        return index;
    }
}
