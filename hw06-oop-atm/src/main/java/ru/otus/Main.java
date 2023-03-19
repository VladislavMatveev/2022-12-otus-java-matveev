package ru.otus;

import ru.otus.model.ATM;
import ru.otus.model.ATMInterface;
import ru.otus.model.Banknote;
import ru.otus.model.Cassette;
import ru.otus.model.enums.Nominal;
import ru.otus.services.ATMServiceImpl;
import ru.otus.services.CassetteServiceImpl;
import ru.otus.services.PickupServiceImpl;
import ru.otus.services.WithdrawServiceImpl;
import ru.otus.services.interfaces.ATMService;
import ru.otus.services.interfaces.CassetteService;

import java.util.Comparator;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        CassetteService cassetteService = new CassetteServiceImpl();
        // Заполняем кассету банкнотами по 100
        Cassette cassette100 = new Cassette(Nominal.RUB_100);
        for (int i = 0; i < 10; i++) {
            Banknote banknote = new Banknote(cassette100.getBanknotNominal());
            cassetteService.add(cassette100, banknote);
        }

        // Заполняем кассету банкнотами по 500
        Cassette cassette500 = new Cassette(Nominal.RUB_500);
        for (int i = 0; i < 10; i++) {
            Banknote banknote = new Banknote(cassette500.getBanknotNominal());
            cassetteService.add(cassette500, banknote);
        }

        // Создаем банкомат и заполняем в нем кассеты с банкнотами
        ATMInterface atm = new ATM(
                new TreeMap<>(Comparator.reverseOrder()));

        atm.addCassette(cassette100);
        atm.addCassette(cassette500);

        ATMService atmService = new ATMServiceImpl(
                cassetteService,
                new WithdrawServiceImpl(cassetteService),
                new PickupServiceImpl(cassetteService));

        // остаток
        System.out.println(atmService.getATMBalance(atm));

        // Получаем сумму 4300
        var result = atmService.withdrawBanknotes(atm, 4300);
        System.out.println(result);

        // Оставляем себе 1 банкноту
        result.remove(0);

        // пополняем банкомат оставшимися банкнотами
        atmService.pickupBanknots(atm, result);
        System.out.println(atmService.getATMBalance(atm));
    }
}