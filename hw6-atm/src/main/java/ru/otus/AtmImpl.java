package ru.otus;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class AtmImpl implements Atm {

    private final Map<Banknote, Integer> atmCash;

    public AtmImpl(Map<Banknote, Integer> cash) {
        this.atmCash = new TreeMap<>() {{
            putAll(cash);
        }};
    }

    public Map<Banknote, Integer> getAtmCash() {
        return atmCash;
    }

    @Override
    public void acceptBanknotes(Map<Banknote, Integer> cash) {
        cash.forEach((key, value) -> atmCash.put(key, atmCash.get(key) + value));
    }

    @Override
    public Map<Banknote, Integer> giveOutAmountWithMinBanknotes(int amount) {
        Map<Banknote, Integer> needCash = new TreeMap<>();
        if (amount < 0) {
            throw new RuntimeException("Запрошенная сумма не может быть отрицательной!");
        }
        if (amount > getBalance()) {
            throw new RuntimeException("Недостаточно средств в банкомате!");
        }
        if (amount % getExistMinNominal() > 0) {
            throw new RuntimeException("Невозможно выдать запрошенную сумму!");
        }
        for (Map.Entry<Banknote, Integer> entry : atmCash.entrySet()) {
            int nominalCounter = amount / entry.getKey().getNominal();
            if (entry.getValue() < nominalCounter) {
                nominalCounter = entry.getValue();
            }
            amount = amount - nominalCounter * entry.getKey().getNominal();
            if (nominalCounter != 0) {
                needCash.put(entry.getKey(), nominalCounter);
            }
        }
        needCash.forEach((key, value) -> atmCash.put(key, atmCash.get(key) - value));

        printCash(needCash);

        return needCash;
    }

    private Integer getExistMinNominal() {
        return atmCash.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .map(Map.Entry::getKey)
                .min(Comparator
                        .comparing(
                                Banknote::getNominal
                        )
                )
                .get()
                .getNominal();
    }

    @Override
    public Integer getBalance() {
        return atmCash.entrySet().stream().mapToInt(entry -> entry.getValue() * entry.getKey().getNominal()).sum();
    }

    private void printCash(Map<Banknote, Integer> cash) {
        System.out.println("Выдано:");
        cash.forEach((key, value) -> System.out.println(value + " банкнот номиналом " + key.getNominal()));
    }
}