package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {


    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void shouldAddBeforeMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(8_000);

        Assertions.assertEquals(2_000 + 8_000, account.getMaxBalance());
    }

    @Test
    public void shouldAddMoreThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(9_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayBeforeNegativeBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(3_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayMoreThanMinBalance() {
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );

        account.pay(1_000);

        Assertions.assertEquals(3_000 - 1_000, account.getBalance());
    }

    @Test
    public void shouldPayLessThanMinBalance() {
        SavingAccount account = new SavingAccount(
                3_000,
                2_000,
                10_000,
                5
        );

        account.pay(2_500);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldPayBeforeMinBalance() {
        SavingAccount account = new SavingAccount(
                3_000,
                2_000,
                10_000,
                5
        );

        account.pay(1_000);

        Assertions.assertEquals(3_000 - 1_000, account.getMinBalance());
    }

    @Test
    public void exceptionForNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new SavingAccount(2_000, 1_000, 10_000, -5));
    }

    @Test
    public void exceptionForNegativeMinBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new SavingAccount(2_000, -1_000, 10_000, 5));
    }

    @Test
    public void exceptionForNegativeMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new SavingAccount(2_000, 1_000, -10_000, -5));
    }

    @Test
    public void exceptionForNegativeInitialBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new SavingAccount(-2_000, 1_000, 10_000, -5));
    }

    @Test
    public void calculationPercent() {
        SavingAccount account = new SavingAccount(
                200,
                2_000,
                10_000,
                15
        );
        account.yearChange();

        Assertions.assertEquals(30, account.yearChange());
    }

    @Test
    public void calculationPercentForNull() {
        SavingAccount account = new SavingAccount(
                0,
                2_000,
                10_000,
                15
        );
        account.yearChange();

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void yearChange() {
        SavingAccount account = new SavingAccount(
                99,
                2_000,
                10_000,
                80
        );
        account.yearChange();

        Assertions.assertEquals(79, account.yearChange());
    }
}
