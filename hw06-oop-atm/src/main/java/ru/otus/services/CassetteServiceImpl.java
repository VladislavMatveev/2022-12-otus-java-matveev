package ru.otus.services;

import ru.otus.model.Banknote;
import ru.otus.model.Cassette;
import ru.otus.services.interfaces.CassetteService;

import java.util.ArrayList;
import java.util.List;

public class CassetteServiceImpl implements CassetteService {
    @Override
    public List<Banknote> withdraw(Cassette cassette, int banknoteCount) {

        List<Banknote> result = new ArrayList<>();

        for (int i = 0; i < banknoteCount; i++) {
            result.add(cassette.getBanknotes().remove());
        }
        return result;
    }

    @Override
    public void add(Cassette cassette, Banknote banknote) {
        cassette.getBanknotes().add(banknote);
    }

    @Override
    public int getBanknotesCount(Cassette cassette) {
        return cassette.getBanknotes().size();
    }

}
