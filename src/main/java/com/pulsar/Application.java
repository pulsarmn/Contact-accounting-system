package com.pulsar;

import java.util.Scanner;

public class Application {

    private final Scanner terminal;
    private final ContactService contactService;

    private static final int ADD_CONTACT = 1;
    private static final int VIEW_CONTACTS = 2;
    private static final int FIND_CONTACT = 3;
    private static final int DELETE_CONTACT = 4;
    private static final int EXIT = 5;

    public Application() {
        this.terminal = new Scanner(System.in);
        this.contactService = new ContactService(terminal);
    }

    public Application(ContactService contactService) {
        this.terminal = new Scanner(System.in);
        this.contactService = contactService;
    }

    public void start() {
        while (true) {
            Printer.mainMenu();
            Printer.inputRequest();

            String userInput = terminal.nextLine();
            int number;

            try {
                number = Integer.parseInt(userInput);
            }catch (NumberFormatException e) {
                Printer.inputError();
                continue;
            }

            switch (number) {
                case ADD_CONTACT -> contactService.addContact();
                case VIEW_CONTACTS -> contactService.viewContacts();
                case FIND_CONTACT -> contactService.findContact();
                case DELETE_CONTACT -> contactService.deleteContact();
                case EXIT -> System.exit(0);
                default -> Printer.inputError();
            }
        }
    }
}
