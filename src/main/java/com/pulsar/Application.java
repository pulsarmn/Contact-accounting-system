package com.pulsar;

import java.util.Scanner;

public class Application {

    private final Storage storage;
    private final Validator validator;
    private final Scanner terminal;

    private static final int ADD_CONTACT = 1;
    private static final int VIEW_CONTACTS = 2;
    private static final int FIND_CONTACT = 3;
    private static final int DELETE_CONTACT = 4;
    private static final int EXIT = 5;

    public Application() {
        this(new Storage());
    }

    public Application(int storageSize) {
        this(new Storage(storageSize));
    }

    public Application(Storage storage) {
        this.storage = storage;
        this.validator = new Validator();
        this.terminal = new Scanner(System.in);
    }
}
