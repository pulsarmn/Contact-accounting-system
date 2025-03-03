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
                }catch (IllegalArgumentException e) {
                    Printer.error(e.getMessage());
                    continue;
                }

                boolean added = storage.add(name, phoneNumber);
                if (added) {
                    Printer.success("Запись успешно добавлена!");
                }else {
                    Printer.error("Запись не добавлена!");
                }
                break;
            }else {
                Printer.error("Хранилище переполнено!");
            }
        }
    }

    public void viewContacts() {
        storage.print();
    }
}
