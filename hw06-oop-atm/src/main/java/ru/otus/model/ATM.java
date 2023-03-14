package ru.otus.model;

import ru.otus.exceptions.UnknownBanknoteException;
import ru.otus.model.enums.Nominal;
import ru.otus.services.interfaces.PickupService;
import ru.otus.services.interfaces.WithdrawService;

import java.util.*;

public class ATM {
    private final SortedMap<Nominal, Cassette> cassettes;
    private final WithdrawService withdrawService;
    private final PickupService pickupService;

    public ATM(WithdrawService withdrawService, PickupService pickupService) {
        this.pickupService = pickupService;
        this.withdrawService = withdrawService;

        this.cassettes = new TreeMap<>(Collections.reverseOrder());

    }

    public void addCassette(Cassette cassette) {
        cassettes.put(
                cassette.getBanknotNominal(),
                cassette);
    }
    public SortedMap<Nominal, Cassette> getCassettes() {
        return cassettes;
    }

    public Cassette getCassetteByNominal(Nominal nominal) {
        return Optional.of(cassettes.get(nominal)).orElseThrow(() -> new UnknownBanknoteException("Unknown banknote"));
    }
    public int getATMBalance() {
        var sum = 0;
        for (Map.Entry<Nominal, Cassette> entry: cassettes.entrySet()) {
            var nominal = entry.getKey().get();
            var banknotesCount = entry.getValue().getBanknotesCount();
            sum += nominal * banknotesCount;
        }
        return sum;
    }
    public List<Banknote> withdrawBanknotes(int sum) {
        return withdrawService.withdraw(this, sum);
    }

    public void pickupBanknots(List<Banknote> banknotes) {
        pickupService.pickup(this, banknotes);
    }
}
