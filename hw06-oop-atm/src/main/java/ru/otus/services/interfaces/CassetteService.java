package ru.otus.services.interfaces;

import ru.otus.model.Banknote;
import ru.otus.model.Cassette;

import java.util.List;

public interface CassetteService {
    List<Banknote> withdraw(Cassette cassette, int sum);

    void add(Cassette cassette, Banknote banknote);

    int getBanknotesCount(Cassette cassette);
}
