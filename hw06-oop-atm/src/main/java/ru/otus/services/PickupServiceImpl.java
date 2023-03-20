package ru.otus.services;

import ru.otus.model.ATMInterface;
import ru.otus.model.Banknote;
import ru.otus.services.interfaces.CassetteService;
import ru.otus.services.interfaces.PickupService;

import java.util.List;

public class PickupServiceImpl implements PickupService {
    private final CassetteService cassetteService;

    public PickupServiceImpl(CassetteService cassetteService) {
        this.cassetteService = cassetteService;
    }

    @Override
    public void pickup(ATMInterface atm, List<Banknote> banknotes) {
        for (Banknote banknote : banknotes) {
            var cassette = atm.getCassetteByNominal(banknote.nominal());
            cassetteService.add(cassette, banknote);
        }
    }
}
