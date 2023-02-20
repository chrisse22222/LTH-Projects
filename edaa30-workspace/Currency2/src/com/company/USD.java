package com.company;

public class USD implements Currency, Comparable<USD>{

    private double balance;
    private double rate;
    public USD (double value, double rate){
        this.rate = rate;
        this.balance = value;
    }

    @Override
    public int compareTo(USD other) {
        if (balance > other.balance){
            return 1;
        } else if (balance < other.balance){
            return -1;
        } else{
            return 0;
        }
    }

    @Override
    public double getValue() {
        return balance;
    }

    @Override
    public void add(double amount) {
        balance += amount;
    }

    @Override
    public void subtract(double amount) {
        balance -= amount;
    }

    @Override
    public double convert(Currency to) {
        return (rate / to.getRate()) * balance;
    }

    @Override
    public double getRate() {
        return rate;
    }
}

