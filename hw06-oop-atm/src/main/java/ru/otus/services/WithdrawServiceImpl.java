package ru.otus.services;

import ru.otus.exceptions.NotEnoughMoneyException;
import ru.otus.model.ATM;
import ru.otus.model.Banknote;
import ru.otus.services.interfaces.WithdrawService;

import java.util.ArrayList;
import java.util.List;

public class WithdrawServiceImpl implements WithdrawService {
    @Override
    public List<Banknote> withdraw(ATM atm, int sum) {
        var balance = atm.getATMBalance();
        if (balance < sum) {
            throw new NotEnoughMoneyException("Sorry, can't withdraw such sum");
        }

        List<Banknote> result = new ArrayList<>();
        for (var entry: atm.getCassettes().entrySet()) {
            var count = sum / entry.getKey().get();
            var cassette = entry.getValue();
            var banknoteMinCount = Math.min(count, cassette.getBanknotesCount());
            result.addAll(cassette.withdraw(banknoteMinCount));
            sum -= banknoteMinCount * entry.getKey().get();
        }

        return result;
    }
}
