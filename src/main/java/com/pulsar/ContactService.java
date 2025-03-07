package com.pulsar;

import java.util.Optional;
import java.util.Scanner;

public class ContactService {

    private final Storage storage;
    private final Scanner terminal;
    private final Validator validator;

    public ContactService(Scanner terminal) {
        this(terminal, new Storage(5));
    }

    public ContactService(Scanner terminal, int size) {
        this(terminal, new Storage(size));
    }

    public ContactService(Scanner terminal, Storage storage) {
        this.terminal = terminal;
        this.storage = storage;
        this.validator = new Validator();
    }

    public void addContact() {
        while (true) {
            if (storage.hasFreeSpace()) {
                Printer.print("Введите имя: ");
                String name = terminal.nextLine();

                Printer.print("Введите номер телефона: ");
                String phoneNumber = terminal.nextLine();

                try {
                    validator.validateName(name);
                    validator.validatePhoneNumber(phoneNumber);
                } catch (IllegalArgumentException e) {
                    Printer.error(e.getMessage());
                    continue;
                }

                boolean added = storage.add(name, phoneNumber);
                if (added) {
                    Printer.success("Запись успешно добавлена!");
                } else {
                    Printer.error("Запись не добавлена!");
                }
            } else {
                Printer.error("Хранилище переполнено!");
            }
            break;
        }
    }

    public void viewContacts() {
        storage.print();
    }

    public void findContact() {
        Printer.print("Введите имя контакта для поиска: ");

        String name = terminal.nextLine();
        Optional<String> contact = storage.findContact(name);

        if (contact.isPresent()) {
            Printer.success("Номер контакта %s: %s".formatted(name, contact.get()));
        } else {
            Printer.error("Номер для контакта %s не найден".formatted(name));
        }
    }

    public void deleteContact() {
        Printer.print("Введите имя контакта для удаления: ");
        String name = terminal.nextLine();

        try {
            storage.delete(name);
        } catch (IllegalArgumentException e) {
            Printer.error(e.getMessage());
        }
    }
}
