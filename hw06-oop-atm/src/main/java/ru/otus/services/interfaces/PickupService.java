package ru.otus.services.interfaces;

import ru.otus.model.ATM;
import ru.otus.model.Banknote;

import java.util.List;

public interface PickupService {
    void pickup(ATM atm, List<Banknote> banknotes);
}
