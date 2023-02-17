package ru.otus;

import ru.otus.unitTest.UnitTestApplication;

public class Start {
    public static void main(String[] args) {
        var testApplication = new UnitTestApplication();
        testApplication.start("ru.otus.testClass");
    }
}
