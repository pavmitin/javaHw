package ru.otus;

import java.util.List;

public interface Atm {

    void acceptBanknotes(List<Banknote> cash);

    List<Banknote> giveOutAmountWithMinBanknotes(int amount);

    Integer getBalance();
}
