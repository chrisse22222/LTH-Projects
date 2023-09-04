package main;

import java.math.BigInteger;

/**
 * @author Christoffer Lindell Bolin, Jonas Andersson
 */
public class PrimeThread extends Thread {
    private RSA rsa;
    public PrimeThread(RSA rsa){
        this.rsa = rsa;
    }

    @Override
    public void run() {
        try{
            while (true){
                BigInteger prime = rsa.generatePrime();
                rsa.addToPrimes(prime);
            }
        } catch (InterruptedException e){
            return;
        }
    }
}
