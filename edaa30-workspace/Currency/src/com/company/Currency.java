package com.company;

public class Currency {

    private String currencyType;
    private double reference;

    public Currency(String currencyType, double reference){
        this.currencyType = currencyType;
        this.reference = reference;
    }

    public double getRate(Currency c){
        return this.reference / c.reference;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Currency){
            Currency c = (Currency) obj;
            return currencyType.equals(c.currencyType);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return currencyType.hashCode() * 41;
    }
}
