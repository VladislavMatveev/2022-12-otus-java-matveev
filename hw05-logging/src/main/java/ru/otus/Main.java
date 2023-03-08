package ru.otus;

import ru.otus.logging.Ioc;

public class Main {
    public static void main(String[] args) {
        TestLoggingInterface testLogging = Ioc.createMyClass(new TestLogging());
        testLogging.calculation(10);
        testLogging.calculation(20, 30);
    }

}