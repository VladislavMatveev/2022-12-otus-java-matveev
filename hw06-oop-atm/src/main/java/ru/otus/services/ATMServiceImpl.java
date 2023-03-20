package ru.otus.services;

import ru.otus.exceptions.NotEnoughMoneyException;
import ru.otus.model.ATMInterface;
import ru.otus.model.Banknote;
import ru.otus.model.Cassette;
import ru.otus.model.enums.Nominal;
import ru.otus.services.interfaces.ATMService;
import ru.otus.services.interfaces.CassetteService;
import ru.otus.services.interfaces.PickupService;
import ru.otus.services.interfaces.WithdrawService;

import java.util.List;
import java.util.Map;

public class ATMServiceImpl implements ATMService {
    private final CassetteService cassetteService;
    private final WithdrawService withdrawService;
    private final PickupService pickupService;

    public ATMServiceImpl(CassetteService cassetteService, WithdrawService withdrawService, PickupService pickupService) {
        this.cassetteService = cassetteService;
        this.withdrawService = withdrawService;
        this.pickupService = pickupService;
    }

    @Override
    public int getATMBalance(ATMInterface atm) {
        var sum = 0;
        for (Map.Entry<Nominal, Cassette> entry : atm.getCassettes().entrySet()) {
            var nominal = entry.getKey().getValue();
            var banknotesCount = cassetteService.getBanknotesCount(entry.getValue());
            sum += nominal * banknotesCount;
        }
        return sum;
    }

    @Override
    public List<Banknote> withdrawBanknotes(ATMInterface atm, int sum) {
        var balance = getATMBalance(atm);
        if (balance < sum) {
            throw new NotEnoughMoneyException("Sorry, can't withdraw such sum");
        }
        return withdrawService.withdraw(atm, sum);
    }

    @Override
    public void pickupBanknots(ATMInterface atm, List<Banknote> banknotes) {
        pickupService.pickup(atm, banknotes);
    }
}
