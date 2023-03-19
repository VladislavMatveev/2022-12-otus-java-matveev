package ru.otus.model;

import ru.otus.exceptions.UnknownBanknoteException;
import ru.otus.model.enums.Nominal;

import java.util.Optional;
import java.util.SortedMap;

public class ATM implements ATMInterface {
    private final SortedMap<Nominal, Cassette> cassettes;

    public ATM(SortedMap<Nominal, Cassette> cassettes) {
        this.cassettes = cassettes;
    }

    public void addCassette(Cassette cassette) {
        cassettes.put(
                cassette.getBanknotNominal(),
                cassette);
    }

    @Override
    public SortedMap<Nominal, Cassette> getCassettes() {
        return cassettes;
    }

    @Override
    public Cassette getCassetteByNominal(Nominal nominal) {
        return Optional.of(cassettes.get(nominal)).orElseThrow(() -> new UnknownBanknoteException("Unknown banknote"));
    }

}
