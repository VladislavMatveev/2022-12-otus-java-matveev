package ru.otus.services;

import ru.otus.exceptions.WithdrawException;
import ru.otus.model.ATMInterface;
import ru.otus.model.Banknote;
import ru.otus.services.interfaces.CassetteService;
import ru.otus.services.interfaces.WithdrawService;

import java.util.ArrayList;
import java.util.List;

public class WithdrawServiceImpl implements WithdrawService {

    private final CassetteService service;

    public WithdrawServiceImpl(CassetteService service) {
        this.service = service;
    }

    @Override
    public List<Banknote> withdraw(ATMInterface atm, int sum) {

        List<Banknote> result = new ArrayList<>();

        try {
            for (var entry : atm.getCassettes().entrySet()) {
                var nominalValue = entry.getKey().getValue();
                var cassette = entry.getValue();

                var count = sum / nominalValue;
                var banknoteMinCount = Math.min(count, service.getBanknotesCount(cassette));

                result.addAll(service.withdraw(cassette, banknoteMinCount));
                sum -= banknoteMinCount * nominalValue;
            }
        } catch (RuntimeException e) {
            redoWithdraw(atm, result);
            throw new WithdrawException("Something goes wrong...", e);
        }
        return result;
    }

    private void redoWithdraw(ATMInterface atm, List<Banknote> result) {
        for(Banknote banknote : result) {
            var cassette = atm.getCassetteByNominal(banknote.nominal());
            service.add(cassette, banknote);
        }
    }
}
