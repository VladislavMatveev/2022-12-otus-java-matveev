package ru.otus.services.interfaces;

import ru.otus.model.ATMInterface;
import ru.otus.model.Banknote;

import java.util.List;

public interface PickupService {
    void pickup(ATMInterface atm, List<Banknote> banknotes);
}
