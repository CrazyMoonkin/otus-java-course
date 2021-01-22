package ru.crazymoonkin.otus;

import java.util.*;

public class Vault<T extends Bill> {
    private final Set<T> bills = new HashSet<>();
    private Integer score = 0;

    public void put(T bill) {
        bills.add(bill);
        score += bill.amount();
    }

    public List<T> give(Integer count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Requested count must be more than 0");
        }
        if (count > bills.size()) {
            throw new IllegalArgumentException("Requested count more than store in vault");
        }
        List<T> billList = new ArrayList<>();

        Iterator<T> iter = bills.iterator();
        while (billList.size() <= count && iter.hasNext()) {
            T bill = iter.next();
            billList.add(bill);
            iter.remove();
            score -= bill.amount();
        }

        if (billList.size() != count) {
            throw new IllegalStateException("billList size not equals requested size");
        }
        return billList;
    }

    public Integer getScore() {
        return score;
    }

    public Integer billCount() {
        return bills.size();
    }
}
