package ru.otus.services.interfaces;

import ru.otus.model.ATMInterface;
import ru.otus.model.Banknote;

import java.util.List;

public interface WithdrawService {
    List<Banknote> withdraw(ATMInterface atm, int sum);
}
