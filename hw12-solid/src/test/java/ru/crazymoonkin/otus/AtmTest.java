package ru.crazymoonkin.otus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

class AtmTest {

    @Test
    void putBill() {
        Atm atm = new Atm();
        Bill bill = new HundredBill();
        atm.put(bill);

        Assertions.assertEquals(100, atm.getScore());
    }

    @Test
    void getMoney() {
        Vault<HundredBill> hundredBillVault = mock(Vault.class);
        Vault<ThousandBill> thousandBillVault = mock(Vault.class);
        Atm atm = new Atm(hundredBillVault, thousandBillVault);

        HundredBill hundredBill = new HundredBill();
        ThousandBill thousandBill = new ThousandBill();

        Mockito.when(hundredBillVault.getScore()).thenReturn(100);
        Mockito.when(hundredBillVault.billCount()).thenReturn(1);
        Mockito.when(hundredBillVault.give(eq(1))).thenReturn(List.of(hundredBill));

        Mockito.when(thousandBillVault.billCount()).thenReturn(1);
        Mockito.when(thousandBillVault.getScore()).thenReturn(1000);
        Mockito.when(thousandBillVault.give(eq(1))).thenReturn(List.of(thousandBill));


        atm.put(hundredBill);
        atm.put(thousandBill);

        List<Bill> money = atm.getMoney(1100);
        assertThat(money).containsOnly(hundredBill, thousandBill);
    }

    @Test
    void checkScoreWith100Test() {
        Atm atm = new Atm();
        Bill bill = new HundredBill();
        atm.put(bill);

        Assertions.assertEquals(100, atm.getScore());
    }

    @Test
    void checkScoreWith1100Test() {
        Atm atm = new Atm();
        Bill hundredBill = new HundredBill();
        Bill thousandBill = new ThousandBill();
        atm.put(hundredBill);
        atm.put(thousandBill);

        Assertions.assertEquals(1100, atm.getScore());
    }
}
