package ru.otus;

import java.util.Map;

public interface Atm {

    void acceptBanknotes(Map<Banknote, Integer> cash);

    Map<Banknote, Integer> giveOutAmountWithMinBanknotes(int amount);

    Integer getBalance();
}
