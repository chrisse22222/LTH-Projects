package com.company;

public interface Currency {

    public double getValue();
    public void add(double amount);
    public void subtract(double amount);
    public double convert(Currency to);
    public double getRate();
}
