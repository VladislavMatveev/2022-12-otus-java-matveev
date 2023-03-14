package ru.otus.services;

import ru.otus.model.ATM;
import ru.otus.model.Banknote;
import ru.otus.services.interfaces.PickupService;

import java.util.List;

public class PickupServiceImpl implements PickupService {
    @Override
    public void pickup(ATM atm, List<Banknote> banknotes) {
        for (Banknote banknote : banknotes) {
            var cassette = atm.getCassetteByNominal(banknote.nominal());
            cassette.add(banknote);
        }
    }
}
