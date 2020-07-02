package ru.otus;

import java.util.ArrayList;
import java.util.List;

public class BanknoteCellImpl implements BanknoteCell {

    private final Banknote banknote;

    private final List<Banknote> banknotes = new ArrayList<>();

    public BanknoteCellImpl(Banknote banknote) {
        this.banknote = banknote;
    }

    public Banknote getBanknote() {
        return banknote;
    }

    public void addBanknotes(Banknote banknote) {
        banknotes.add(banknote);
    }

    public List<Banknote> removeBanknotes(int count) {
        List<Banknote> removedBanknotes = new ArrayList<>();
        for (int i = 0; i <= count; i++) {
            removedBanknotes.add(banknotes.remove(banknotes.size() - 1));
            i++;
        }
        return removedBanknotes;
    }

    public int countBanknotes() {
        return banknotes.size();
    }

    @Override
    public String toString() {
        return countBanknotes() +
                " номиналом " + banknote.getNominal();
    }
}
