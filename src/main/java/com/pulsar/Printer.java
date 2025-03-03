package com.pulsar;

public final class Printer {

    private static final String BOLD_RED = "\033[1;31m";
    private static final String RESET = "\033[0m";

    private Printer() {}

    public static void mainMenu() {
        System.out.println("1. Добавить контакт");
        System.out.println("2. Просмотреть контакты");
        System.out.println("3. Найти контакт");
        System.out.println("4. Удалить контакт");
        System.out.println("5. Выйти");
    }
}
