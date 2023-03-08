package ru.otus;

import ru.otus.logging.annotations.Log;

public class TestLogging implements TestLoggingInterface{

    @Override
    public void calculation(int param) {
        System.out.println(param);
    }

    @Log
    @Override
    public void calculation(int param1, int param2) {
        System.out.println(param1 + param2);
    }
}
