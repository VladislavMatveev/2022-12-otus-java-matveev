package ru.otus;

import ru.otus.model.ATM;
import ru.otus.model.Banknote;
import ru.otus.model.Cassette;
import ru.otus.model.enums.Nominal;
import ru.otus.services.PickupServiceImpl;
import ru.otus.services.WithdrawServiceImpl;

public class Main {
    public static void main(String[] args) {

        // Заполняем кассету банкнотами по 100
        Cassette cassette100 = new Cassette(Nominal.RUB_100);
        for (int i = 0; i < 10; i++) {
            Banknote banknote = new Banknote(cassette100.getBanknotNominal());
            cassette100.add(banknote);
        }

        // Заполняем кассету банкнотами по 500
        Cassette cassette500 = new Cassette(Nominal.RUB_500);
        for (int i = 0; i < 10; i++) {
            Banknote banknote = new Banknote(cassette500.getBanknotNominal());
            cassette500.add(banknote);
        }

        // Создаем банкомат и заполняем в нем кассеты с банкнотами
        ATM atm = new ATM(new WithdrawServiceImpl(), new PickupServiceImpl());
        atm.addCassette(cassette100);
        atm.addCassette(cassette500);

        // остаток
        System.out.println(atm.getATMBalance());

        // Получаем сумму 4300
        var result = atm.withdrawBanknotes(4300);
        System.out.println(result);

        // Оставляем себе 1 банкноту
        result.remove(0);

        // пополняем банкомат оставшимися банкнотами
        atm.pickupBanknots(result);
        System.out.println(atm.getATMBalance());
    }
}