package ru.otus.model;

import ru.otus.model.enums.Nominal;

import java.util.SortedMap;

public interface ATMInterface {
    void addCassette(Cassette cassette);
    SortedMap<Nominal, Cassette> getCassettes();
    Cassette getCassetteByNominal(Nominal nominal);
}
