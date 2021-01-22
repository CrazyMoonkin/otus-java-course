package ru.crazymoonkin.otus;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Stream.empty;

public class Atm {

    public Atm() {
        vaultMap.put(100, new Vault<>());
        vaultMap.put(1000, new Vault<>());
    }

    public Atm(Vault<HundredBill> hundredVault, Vault<ThousandBill> thousandVault) {
        vaultMap.put(100, hundredVault);
        vaultMap.put(1000, thousandVault);
    }

    private final Map<Integer, Vault> vaultMap = new TreeMap<>(Collections.reverseOrder());
    private Integer divider = null;

    public void put(Bill bill) {
        if (bill instanceof HundredBill) {
            vaultMap.get(bill.amount()).put(bill);
        } else if (bill instanceof ThousandBill) {
            vaultMap.get(bill.amount()).put(bill);
        } else {
            throw new IllegalArgumentException("ATM not support such bill");
        }

        divider = computeDivider(bill.amount());
    }

    public List<Bill> getMoney(Integer amount) {
        Integer currentAmount = getScore();
        if (amount > currentAmount || currentAmount == 0) {
            throw new IllegalArgumentException("Requested amount more than score");
        }

        if (amount % divider != 0) {
            throw new IllegalArgumentException("Requested amount more than score");
        }
        AtomicInteger amount2 = new AtomicInteger(amount);
        return (List<Bill>) vaultMap.entrySet()
                .stream()
                .flatMap(
                        integerVaultEntry -> {
                            Integer billCount = amount2.get() / integerVaultEntry.getKey();
                            if (integerVaultEntry.getValue().billCount() < billCount) {
                                billCount = integerVaultEntry.getValue().billCount();
                            }
                            Integer finalBillCount = billCount;
                            amount2.updateAndGet(v -> v - (integerVaultEntry.getKey() * finalBillCount));
                            return finalBillCount != 0 ? integerVaultEntry.getValue().give(billCount).stream() : empty();
                        }
                )
                .collect(Collectors.toList());
    }

    public Integer getScore() {
        return vaultMap.values().stream().map(Vault::getScore).reduce(0, Integer::sum);
    }

    private Integer computeDivider(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        Integer minValue = divider;

        if (minValue == null) {
            minValue = value;
        } else if (value < minValue) {
            minValue = value;
        }
        return minValue;
    }
}
