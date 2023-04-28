package ru.otus.processor;

import ru.otus.model.Message;

import java.time.LocalDateTime;

public class ExceptionThrowProcessor implements Processor {

    private final LocalDateTime localDateTime;

    public ExceptionThrowProcessor(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public Message process(Message message) {

        if(localDateTime.getSecond()%2 == 0) {
            throw new RuntimeException();
        }

        return message;
    }
}
