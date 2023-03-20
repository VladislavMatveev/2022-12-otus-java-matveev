package ru.otus.services.interfaces;

import ru.otus.model.ATMInterface;
import ru.otus.model.Banknote;

import java.util.List;

public interface ATMService {
    int getATMBalance(ATMInterface atm);
    List<Banknote> withdrawBanknotes(ATMInterface atm, int sum);
    void pickupBanknots(ATMInterface atm, List<Banknote> banknotes);
}
