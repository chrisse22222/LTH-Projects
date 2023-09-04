import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import main.PrimeThread;
import main.RSA;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Christoffer Lindell Bolin, Jonas Andersson
 */
public class JUnitTest {
    private RSA rsa;
    private List<PrimeThread> primeThreads;
    private BigInteger p = new BigInteger("5847326370274734640418268886684775502560998026036986813046271395625622743874394529103271387915921796955857258949786518156071640611484011135123220931671247");
    private BigInteger q = new BigInteger("6935138736426782626981616030335320478743569950681200844318148672790845399359933178736398038230748652690546732307807455974512051536772428455974731148704207");
    private BigInteger e = BigInteger.TWO.pow(16).add(BigInteger.ONE);

    @Before
    public void setUp(){
        primeThreads = new LinkedList<>();
        rsa = new RSA();
        for (int i = 0; i < 16; i++) {
            primeThreads.add(new PrimeThread(rsa));
        }
    }

    @Test
    public void generateOnePrimeNumber(){
        rsa.setBitSize(512);
        System.out.println(rsa.generatePrime().toString());
    }

    @Test
    public void testPrimeNumbers(){
        BigInteger n1 = new BigInteger("126127044061845607346687423429");
        BigInteger n2 = new BigInteger("126127044061844607346687423429");
        BigInteger n3 = new BigInteger("11");
        assertTrue(rsa.isPrime(n1, 20));
        assertFalse(rsa.isPrime(n2, 20));
        assertTrue(rsa.isPrime(n3, 20));
    }

    @Test
    public void test512Bits(){
        test100Primes(512);
    }

    @Test
    public void test1024Bits(){
        test100Primes(1024);
    }

    @Test
    public void test2048Bits(){
        test100Primes(2048);
    }

    @Test
    public void test4096Bits(){
        test100Primes(4096);
    }

    private void test100Primes(int bitSize){
        rsa.setBitSize(bitSize);
        primeThreads.forEach(t -> t.start());
        try {
            rsa.done(primeThreads);
        } catch (InterruptedException e){
            return;
        }
    }

    @Test
    public void inverseModSmallNumbers(){
        System.out.println(rsa.inverseMod(new BigInteger("2"), new BigInteger("3")));
        System.out.println(rsa.inverseMod(new BigInteger("5"), new BigInteger("10")));
    }

    @Test
    public void checkD(){
        BigInteger d = rsa.calculateD(p, q, e);
        assertFalse(d.compareTo(BigInteger.ZERO) < 0);
        System.out.println(d);
    }

    @Test
    public void encryptDecrypt(){
        BigInteger d = rsa.calculateD(p, q, e);
        BigInteger N = p.multiply(q);
        BigInteger s = rsa.randomBigInteger(BigInteger.TWO, N.subtract(BigInteger.ONE));
        System.out.println("The message is: " + s + "\n");
        BigInteger c = rsa.encrypt(s, e, N);
        System.out.println("Ciphertext: " + c + "\n");
        BigInteger z = rsa.decrypt(c, d, N);
        System.out.println("Decrypted message is: " + z);
        assertTrue(s.equals(z));
    }
}
