package com.company;

import java.util.Map;

public class Account {
    private Map<Currency, Double> balance;
    public Account (Map<Currency, Double> balance){
        this.balance = balance;
    }

    public void add(Currency c, double value){
        if (balance.containsKey(c)){
            balance.put(c, balance.get(c) + value);
        } else{
            balance.put(c, value);
        }
    }

    public void sub(Currency c, double value){
        if (balance.containsKey(c)){
            balance.put(c, balance.get(c) - value);
        }

        throw new UnsupportedOperationException();
    }

    public double convert(Currency from, Currency to, double amount){
        if (balance.containsKey(from)){
            double val = from.getRate(to) * amount;
            balance.put(from, balance.get(from) - val);
            if (balance.containsKey(to)){
                balance.put(to, balance.get(to) + val);
            } else{
                balance.put(to, val);
            }

            return val;
        }

        throw new UnsupportedOperationException();
    }

    public int compare(Currency c, double value){
        if (balance.containsKey(c)){
            return balance.get(c).compareTo(value);
        }

        throw new UnsupportedOperationException();
    }
}
