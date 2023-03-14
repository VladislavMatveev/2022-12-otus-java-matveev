package ru.otus.model;

import ru.otus.model.enums.Nominal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Cassette {
    private final Nominal banknotNominal;
    private final Queue<Banknote> banknotes;

    public Cassette(Nominal banknotNominal) {
        this.banknotNominal = banknotNominal;
        this.banknotes = new ArrayDeque<>();
    }

    public int getBanknotesCount() {
        return banknotes.size();
    }

    public Nominal getBanknotNominal() {
        return banknotNominal;
    }

    public void add(Banknote banknote) {
        banknotes.add(banknote);
    }

    public List<Banknote> withdraw(int banknoteCount) {

        List<Banknote> result = new ArrayList<>();

        for (int i = 0; i < banknoteCount; i++) {
            result.add(banknotes.remove());
        }
        return result;
    }
}
