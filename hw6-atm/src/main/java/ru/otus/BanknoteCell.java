package ru.otus;

import java.util.List;

public interface BanknoteCell {

    void addBanknotes(Banknote banknote);

    List<Banknote> removeBanknotes(int count);

    int countBanknotes();

}
