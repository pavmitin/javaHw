package ru.otus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


class AtmImplTest {
    private AtmImpl atm;

    @BeforeEach
    void setUp() {
        atm = new AtmImpl();
    }

    @Test
    void acceptBanknotes() {
        var cash = Arrays.asList(
                Banknote.THOUSAND,
                Banknote.HUNDRED,
                Banknote.FIFTY,
                Banknote.FIFTY
        );
        atm.acceptBanknotes(cash);
        assertEquals(atm.getAtmCash().entrySet().stream().filter(entry -> entry.getValue().countBanknotes() > 0).count() , 3);
        assertTrue(
                atm.getAtmCash().entrySet().stream()
                        .filter(entry -> entry.getValue().countBanknotes() > 0)
                        .allMatch(
                                entry -> cash.containsAll(entry.getValue().getBanknotes())
                        )
        );
    }

    @Test
    void giveOutAmountWithMinBanknotes() {
        var cash = Arrays.asList(
                Banknote.THOUSAND,
                Banknote.HUNDRED,
                Banknote.FIFTY,
                Banknote.FIFTY
        );
        atm.acceptBanknotes(cash);
        assertThrows(RuntimeException.class, () -> atm.giveOutAmountWithMinBanknotes(1), "Невозможно выдать запрошенную сумму!");
        assertThrows(RuntimeException.class, () -> atm.giveOutAmountWithMinBanknotes(-3), "Запрошенная сумма не может быть отрицательной!");
        assertThrows(RuntimeException.class, () -> atm.giveOutAmountWithMinBanknotes(51), "Невозможно выдать запрошенную сумму!");
        assertThrows(RuntimeException.class, () -> atm.giveOutAmountWithMinBanknotes(1300), "Недостаточно средств в банкомате!");
        assertEquals(atm.giveOutAmountWithMinBanknotes(1200), cash);
        atm.acceptBanknotes(cash);
        assertEquals(atm.giveOutAmountWithMinBanknotes(100), Collections.singletonList(Banknote.HUNDRED));
    }

    @Test
    void getBalance() {
        var cash = Arrays.asList(
                Banknote.THOUSAND,
                Banknote.HUNDRED,
                Banknote.FIFTY,
                Banknote.FIFTY
        );
        atm.acceptBanknotes(cash);
        atm.getBalance();
        Integer expected = atm.getBalance();
        Integer actual = 1200;
        assertEquals(expected, actual);
    }

}