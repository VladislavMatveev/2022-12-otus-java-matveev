package ru.otus.services.interfaces;

import ru.otus.model.ATM;
import ru.otus.model.Banknote;

import java.util.List;

public interface WithdrawService {
    List<Banknote> withdraw(ATM atm, int sum);
}
