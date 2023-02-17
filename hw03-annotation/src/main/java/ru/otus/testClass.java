package ru.otus;

import ru.otus.unitTest.annotations.After;
import ru.otus.unitTest.annotations.Before;
import ru.otus.unitTest.annotations.Test;

public class testClass {

    @Before
    public void beforeTest1() {
        System.out.println("Before1 test");
    }

    @Before
    public void beforeTest2() {
        System.out.println("Before2 test");
    }

    @Test
    public void firstTest() {
        System.out.println("First test running...");
        throw new RuntimeException();
    }

    @Test
    public void secondTest() {
        System.out.println("Second test running...");
    }

    @After
    public void afterTest() {
        System.out.println("After test");
    }

}
