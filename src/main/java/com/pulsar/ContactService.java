package com.pulsar;

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
}
