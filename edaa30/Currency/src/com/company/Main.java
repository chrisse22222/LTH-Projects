package com.company;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // Referens är €
        Currency usDollar = new Currency("$", 0.83);
        Currency sek = new Currency("SEK", 0.100);
        Currency pound = new Currency("£", 1.15);

        HashMap<Currency, Double> balance = new HashMap<>();
        balance.put(usDollar, 100.);
        balance.put(sek, 500.);
        Account a = new Account(balance);

        balance = new HashMap<>();
        balance.put(sek, 1000.);

        Account b = new Account(balance);
        System.out.println(b.compare(sek, 1000));
    }
}
