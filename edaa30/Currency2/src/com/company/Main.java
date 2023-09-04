package com.company;

public class Main {

    public static void main(String[] args) {
        Currency c1 = new USD(100., 0.83);
        Currency c2 = new SEK(1000., 0.100);

        System.out.println(c1.convert(c2));
    }
}
