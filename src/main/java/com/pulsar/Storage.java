package com.pulsar;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class Storage {

    private final Contact[] contacts;
    private int size;
    private static final int INDEX_OCCUPIED = -1;

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
            if (contact != null && contact.name().equalsIgnoreCase(name)) {
                fillEmptySpace(i);
                size--;
                break;
            }
        }
    }

    public Optional<String> findContact(String name) {
        return Arrays.stream(contacts)
                .filter(Objects::nonNull)
                .filter(contact -> contact.name().equalsIgnoreCase(name))
                .map(Contact::phoneNumber)
                .findFirst();
    }

    public void print() {
        Printer.println("Список ваших контактов:");

        for (int i = 0; i < size; i++) {
            Printer.println("%s. %s".formatted(i + 1, contacts[i]));
        }

        Printer.println("");
    }

    public boolean hasFreeSpace() {
        return size < contacts.length;
    }

    private void fillEmptySpace(int index) {
        while (index != contacts.length - 1) {
            contacts[index] = contacts[++index];
        }
        contacts[contacts.length - 1] = null;
    }

    private int findFreeIndex() {
        int index = INDEX_OCCUPIED;

        if (size != contacts.length && contacts[size] == null) {
            index = size;
        }

        return index;
    }
}
