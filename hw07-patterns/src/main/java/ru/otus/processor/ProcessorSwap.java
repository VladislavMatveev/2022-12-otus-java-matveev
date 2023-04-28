package ru.otus.processor;

import ru.otus.model.Message;

public class ProcessorSwap implements Processor {

    @Override
    public Message process(Message message) {
        var fieldValue = message.getField12();
        return message.toBuilder()
                .field12(message.getField11())
                .field11(fieldValue)
                .build();

    }
}
