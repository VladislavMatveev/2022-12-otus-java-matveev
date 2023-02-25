package ru.otus;

import ru.otus.logging.Ioc;

public class Main {
    public static void main(String[] args) {
        TestLoggingInterface testLogging = Ioc.createMyClass();
        testLogging.calculation(10);
        testLogging.calculation(20, 30);
    }

}