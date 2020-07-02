package ru.otus;

import java.util.*;

public class AtmImpl implements Atm {

    private Map<Banknote, BanknoteCellImpl> atmCash = new TreeMap<>(
            Comparator.comparing(Banknote::getNominal).reversed()
    );

    public AtmImpl() {
        Arrays.stream(Banknote.values()).forEach(
                banknote ->
                        atmCash.put(
                                banknote,
                                new BanknoteCellImpl(banknote)
                        )
        );
    }

    @Override
    public void acceptBanknotes(List<Banknote> banknotes) {
        banknotes.forEach(
                banknote ->
                        atmCash
                                .get(banknote)
                                .addBanknotes(banknote)
        );
    }

    @Override
    public List<Banknote> giveOutAmountWithMinBanknotes(int amount) {

        List<Banknote> needCash = new ArrayList<>();
        if (amount < 0) {
            throw new RuntimeException("Запрошенная сумма не может быть отрицательной!");
        }
        if (amount > getBalance()) {
            throw new RuntimeException("Недостаточно средств в банкомате!");
        }
        if (amount % getExistMinNominal() > 0) {
            throw new RuntimeException("Невозможно выдать запрошенную сумму!");
        }
        for (BanknoteCellImpl banknoteCell : atmCash.values()) {
            if (banknoteCell.countBanknotes() > 0) {
                int nominalCounter = amount / banknoteCell.getBanknote().getNominal();
                if (banknoteCell.countBanknotes() < nominalCounter) {
                    nominalCounter = banknoteCell.countBanknotes();
                }
                amount = amount - nominalCounter * banknoteCell.getBanknote().getNominal();
                if (nominalCounter != 0) {
                    needCash.addAll(banknoteCell.removeBanknotes(nominalCounter));
                }
            }
        }
        return needCash;
    }

    private Integer getExistMinNominal() {
        return atmCash.values().stream()
                .filter(banknoteCell -> banknoteCell.countBanknotes() > 0)
                .map(banknoteCell -> banknoteCell.getBanknote().getNominal())
                .min(
                        Comparator.comparing(Integer::intValue)
                )
                .get();
    }

    public Integer getBalance() {
        return atmCash.entrySet().stream()
                .mapToInt(
                        entry ->
                                entry.getValue().countBanknotes() * entry.getKey().getNominal()
                )
                .sum();
    }

    public Map<Banknote, BanknoteCellImpl> getAtmCash() {
        return atmCash;
    }
}
