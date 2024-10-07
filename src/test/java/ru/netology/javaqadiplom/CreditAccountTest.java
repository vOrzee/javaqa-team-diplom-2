package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CreditAccountTest {

    @Test
    public void shouldThrowExceptionForNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            new CreditAccount(1000, 5000, -10)
        );
    }

    @Test
    public void shouldThrowExceptionForNegativeCreditLimit() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            new CreditAccount(1000, -5000, 15)
        );
    }

    @Test
    public void shouldThrowExceptionForNegativeInitialBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            new CreditAccount(-1000, 5000, 15)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "5000, 3000, 2000, true",
            "5000, 7000, -2000, true",
            "5000, 10000, -5000, true",
            "5000, 10001, 5000, false",
            "5000, 5000, 0, true",
            "5000, -1, 5000, false",
            "5000, 0, 5000, false",
    })
    public void shouldTestPay(int initialBalance, int amount, int expectedBalance, boolean expectedResult) {
        CreditAccount account = new CreditAccount(initialBalance, 5000, 15);
        boolean result = account.pay(amount);
        Assertions.assertEquals(expectedResult, result);
        Assertions.assertEquals(expectedBalance, account.getBalance());
    }

    @ParameterizedTest
    @CsvSource({
            "1000, 500, 1500, true",
            "1000, -500, 1000, false",
            "1000, 0, 1000, false"
    })
    public void shouldTestAdd(int initialBalance, int amount, int expectedBalance, boolean expectedResult) {
        CreditAccount account = new CreditAccount(initialBalance, 5000, 15);
        boolean result = account.add(amount);
        Assertions.assertEquals(expectedResult, result);
        Assertions.assertEquals(expectedBalance, account.getBalance());
    }

    @ParameterizedTest
    @CsvSource({
            "-200, 15, -30",
            "200, 15, 0",
            "0, 15, 0"
    })
    public void shouldTestYearChange(int balance, int rate, int expectedChange) {
        CreditAccount account = new CreditAccount(balance, 5000, rate);
        int result = account.yearChange();
        Assertions.assertEquals(expectedChange, result);
    }
}
