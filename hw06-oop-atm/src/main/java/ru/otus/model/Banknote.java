package ru.otus.model;

import ru.otus.model.enums.Nominal;

public record Banknote(Nominal nominal) {
    @Override
    public String toString() {
        return "Banknote{" +
                nominal.get() +
                '}';
    }
}
