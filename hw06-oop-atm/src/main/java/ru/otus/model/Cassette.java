package ru.otus.model;

import ru.otus.model.enums.Nominal;

import java.util.ArrayDeque;
import java.util.Queue;

public class Cassette {
    private final Nominal banknotNominal;
    private final Queue<Banknote> banknotes;

    public Cassette(Nominal banknotNominal) {
        this.banknotNominal = banknotNominal;
        this.banknotes = new ArrayDeque<>();
    }

    public Nominal getBanknotNominal() {
        return banknotNominal;
    }

    public Queue<Banknote> getBanknotes() {
        return banknotes;
    }
}
