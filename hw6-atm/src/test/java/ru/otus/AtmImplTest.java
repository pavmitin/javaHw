package ru.otus;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AtmImplTest {
    private AtmImpl atm;

    @Test
    void acceptBanknotes() {
        Map<Banknote, Integer> cash = new HashMap<>();
        cash.put(Banknote.FIVE_THOUSAND, 5);
        cash.put(Banknote.FIFTY, 100);
        atm = new AtmImpl(cash);
        atm.acceptBanknotes(cash);
        Integer expectedFifty = atm.getAtmCash().get(Banknote.FIFTY);
        Integer expectedFiveThusand = atm.getAtmCash().get(Banknote.FIVE_THOUSAND);
        Integer actualFifty = 200;
        Integer actualFiveThusand = 10;
        assertEquals(expectedFifty, actualFifty);
        assertEquals(expectedFiveThusand, actualFiveThusand);
    }

    @Test
    void giveOutAmountWithMinBanknotes() {
        Map<Banknote, Integer> cash = new HashMap<>();
        cash.put(Banknote.FIVE_THOUSAND, 5);
        cash.put(Banknote.FIFTY, 100);
        cash.put(Banknote.FIVE_HUNDRED, 10);
        atm = new AtmImpl(cash);
        assertThrows(RuntimeException.class, () -> atm.giveOutAmountWithMinBanknotes(1), "Невозможно выдать запрошенную сумму!");
        assertThrows(RuntimeException.class, () -> atm.giveOutAmountWithMinBanknotes(-3), "Запрошенная сумма не может быть отрицательной!");
        assertThrows(RuntimeException.class, () -> atm.giveOutAmountWithMinBanknotes(51), "Невозможно выдать запрошенную сумму!");
        assertThrows(RuntimeException.class, () -> atm.giveOutAmountWithMinBanknotes(35050), "Недостаточно средств в банкомате!");
        Map<Banknote, Integer> actualMap = new HashMap<>() {{
            put(Banknote.FIVE_HUNDRED, 2);
        }};
        assertEquals(atm.giveOutAmountWithMinBanknotes(1000), actualMap);
    }

    @Test
    void getBalance() {
        Map<Banknote, Integer> cash = new HashMap<>();
        cash.put(Banknote.FIVE_THOUSAND, 5);
        cash.put(Banknote.FIFTY, 100);
        cash.put(Banknote.FIVE_HUNDRED, 10);
        atm = new AtmImpl(cash);
        atm.giveOutAmountWithMinBanknotes(35000);
        Integer expected = atm.getBalance();
        Integer actual = 0;
        assertEquals(expected, actual);
    }
}