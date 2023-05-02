package ru.otus.processor;

import ru.otus.model.Message;
import ru.otus.provider.DateTimeProvider;

public class ExceptionThrowProcessor implements Processor {

    private final DateTimeProvider localDateTime;

    public ExceptionThrowProcessor(DateTimeProvider localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public Message process(Message message) {

        if(localDateTime.getDate().getSecond()%2 == 0) {
            throw new RuntimeException();
        }

        return message;
    }
}
