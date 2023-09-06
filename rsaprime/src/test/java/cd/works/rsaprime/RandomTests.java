package cd.works.rsaprime;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class RandomTests {

    private static final int BIT_LENGTH = 5;
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger e;
    private BigInteger d;

    private static BigInteger gcd(BigInteger a, BigInteger b) {
        while (b.compareTo(BigInteger.ZERO) != 0) {
            BigInteger temp = a;
            a = b;
            b = temp.mod(b);
        }
        return a;
    }

    public void RSA() {
        // Generate two prime numbers
        p = BigInteger.probablePrime(BIT_LENGTH, new java.security.SecureRandom());
        q = BigInteger.probablePrime(BIT_LENGTH, new java.security.SecureRandom());

        // Calculate the public key
        n = p.multiply(q);
        e = BigInteger.probablePrime(BIT_LENGTH, new java.security.SecureRandom());
        while (!gcd(e, (p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)))).equals(BigInteger.ONE)) {
            e = BigInteger.probablePrime(BIT_LENGTH, new java.security.SecureRandom());
        }

        // Calculate the private key
        d = e.modInverse((p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)));
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger ciphertext) {
        return ciphertext.modPow(d, n);
    }

    @Test
    public void testRSA() {
        RSA();
        BigInteger encryptedMessage = encrypt(BigInteger.valueOf(123423459L));
        System.out.println("Encrypted number: " + encryptedMessage);

        System.out.println("Decrypted number: " + decrypt(encryptedMessage));

        System.out.println("n: " + n);
        System.out.println("Public key: e" + e);
        System.out.println("Private key: d" + d);

        System.out.println("p: " + p + " ; " + Integer.toBinaryString(p.intValue()));
        System.out.println("q: " + q);
    }

    @Test
    public void check() {
        System.out.println(3136 / 77);
    }
}
