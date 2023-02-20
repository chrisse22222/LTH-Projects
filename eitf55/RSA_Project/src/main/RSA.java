package main;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Christoffer Lindell Bolin, Jonas Andersson
 */
public class RSA {
    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger THREE = new BigInteger("3");
    private BigInteger[] standard_bases;
    private Random random = new Random();
    private List<BigInteger> primes = new LinkedList<>();
    private int count;
    private int bitsize;

    public RSA() {
        standard_bases = new BigInteger[]{
                new BigInteger("2047"), new BigInteger("1373653"),
                new BigInteger("25326001"), new BigInteger("3215031751"),
                new BigInteger("2152302898747"), new BigInteger("3474749660383"),
                new BigInteger("341550071728321"), new BigInteger("341550071728321")};
    }

    public void setBitSize(int bitSize){
        this.bitsize = bitSize;
    }

    /**
     * @param n > 3, an odd integer to be tested for primality
     * @param iterations number of iterations, the higher number of iterations the lower probability that
     * the prime is falsely predicted 1/4^(iterations)
     * @returns True if n is a prime, otherwise false
     */
    public boolean isPrime(BigInteger n, int iterations) {
        if (n.compareTo(THREE) <= 0)
            throw new ArithmeticException("n needs to be greater than 3");
        if (n.mod(TWO).equals(ZERO))
            return false;

        BigInteger s = n.subtract(ONE); // s = n-1,   n-1 = s*2^(r)
        int r = s.getLowestSetBit();
        s = s.shiftRight(r);

        BigInteger a;
        for (int i = 0; i < iterations; i++) {
            if (i < standard_bases.length) {
                a = standard_bases[i];
            } else {
                a = randomBigInteger(TWO, n.subtract(TWO));
            }

            if (!rabinMiller(a, n, s, r))
                return false;
        }

        return true;
    }

    private boolean rabinMiller(BigInteger a, BigInteger n, BigInteger s, int r){
        BigInteger x = a.modPow(s, n); //a^s mod n
        if (x.equals(ONE) || x.equals(n.subtract(ONE)))
            return true;

        for (int j = 1; j <= r - 1; j++) {
            //x = a.modPow(TWO.pow(j).multiply(s), n); // x = a^(2^j)*s % n
            x = x.modPow(TWO, n); // <-- Faster, less calculations x^2 % n, x^2 = a^(s*2^(j))
            if (x.equals(ONE))
                return false;
            if (x.equals(n.subtract(ONE)))
                return true;
        }

        return false;
    }

    /**
     * Calculates the inverse mod
     * @param a integer value
     * @param m integer value
     * @return a modular inverse of a%m, "v" such that a*v ≡ 1 (mod m).
     */
    public BigInteger inverseMod(BigInteger a, BigInteger m){
        BigInteger v1, v2, t3, q, v, d, d2, d1, t2;

        v1 = ZERO; v2 = ONE; d2 = a; d1 = m;
        while(!d2.equals(ZERO)){
            //System.out.println("\nNew loop: ");
            q = d1.divide(d2);
            t2 = v1.subtract(q.multiply(v2));
            t3 = d1.subtract(q.multiply(d2));

            v1 = v2; d1 = d2;
            v2 = t2; d2 = t3;
            //System.out.println("\nq: " + q + "\nv1: " + v1 + "\nv2: " + v2 + "\nd1: " + d1 + "\nd2: " + d2 + "\nt2: " + t2 + "\nt3: " + t3);
        }

        v = v1;
        d = d1;
        if (v.compareTo(ZERO) < 0)
            v = v.add(m);

        return d.equals(ONE) ? v : ZERO.subtract(ONE);
    }

    /**
     * Calculates the private key exponent d
     * @param p is a prime
     * @param q is a prime
     * @param e is the public key exponent
     * @returns d, -1 if no inverse could be found
     */
    public BigInteger calculateD(BigInteger p, BigInteger q, BigInteger e){
        BigInteger fi_of_n = p.subtract(ONE).multiply(q.subtract(ONE));
        return inverseMod(e,fi_of_n);
    }

    /**
     * Encrypts the message s
     * @param s is the message
     * @param e is the public key exponent
     * @param N is a product of two primes p and q
     * @return ciphertext from plaintext s
     */
    public BigInteger encrypt(BigInteger s, BigInteger e, BigInteger N){
        return s.modPow(e, N);
    }

    /**
     * Decrypts message c
     * @param c is the ciphertext
     * @param d is the private key exponent
     * @param N is a product of two primes p and q
     * @return
     */
    public BigInteger decrypt(BigInteger c, BigInteger d,  BigInteger N){
        return c.modPow(d, N);
    }

    /**
     * Generates random prime with a certain bitsize
     */
    public BigInteger generatePrime(){
        while(true){
            BigInteger n = new BigInteger(bitsize, random);
            if (isPrime(n, 20)){  //O(n)
                return n;
            }
        }
    }

    /**
     * @param lowerBound lowest value the number can be
     * @param upperBound highest value the number can be
     * @returns randomly generated value within lower and upper bounds
     */
    public BigInteger randomBigInteger(BigInteger lowerBound, BigInteger upperBound) {
        BigInteger value;
        do {
            value = new BigInteger(upperBound.bitLength(), random);
        } while (value.compareTo(lowerBound) < 0 || value.compareTo(upperBound) > 0);

        return value;
    }

    public synchronized void done(List<PrimeThread> threads) throws InterruptedException {
        while (count < 100){
            wait();
        }

        threads.forEach(t -> t.interrupt());
    }

    public synchronized void addToPrimes(BigInteger prime) throws InterruptedException {
        if (count == 100)
            wait();

        count++;
        primes.add(prime);
        notifyAll();
    }
}
