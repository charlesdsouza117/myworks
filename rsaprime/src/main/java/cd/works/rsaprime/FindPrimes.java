package cd.works.rsaprime;

import java.math.BigInteger;
import java.util.Random;

public class FindPrimes {

    public Long[] findPrimes(long N) {
        System.out.println("N: " + N);
        long g = 0;
        int r = 1;
        boolean hadToBreak = false;
        BigInteger bigN = BigInteger.valueOf(N);
        BigInteger bigG;
        do {
            g = 56;//guess(N);
            bigG = BigInteger.valueOf(g);
            System.out.println("Guess: " + g);
            BigInteger pow = bigG.pow(r);
            BigInteger mod = pow.mod(bigN);
            while (mod.intValue() != 1) {
                System.out.println(g + "^" + r + "=" + pow);
                System.out.println("mod: " + mod);
                r++;
                pow = bigG;
                pow = pow.pow(r);
                mod = pow.mod(bigN);
                System.out.println("mod: " + mod);
                if (r > 10) {
                    hadToBreak = true;
                    r = 1;
                    break;
                }
            }
        } while (hadToBreak);
        System.out.println("r: " + r);
        long fpow = r / 2;
        System.out.println("fpow: " + fpow);

        BigInteger bigA = bigG.pow(Long.valueOf(fpow).intValue());
        bigA = bigA.add(BigInteger.ONE);

        //long B = Double.valueOf(Math.pow(g, fpow) - 1).longValue();

        BigInteger factor1 = gcd(bigA, bigN);
        BigInteger factor2 = bigN.divide(factor1);

        System.out.println("Guess: " + g);

        return new Long[]{factor1.longValue(), factor2.longValue()};
    }

    private long guess(long N) {
        while (true) {
            long r = new Random().nextLong(N);
            if (r % N != 0 && !isPrime(r)) {
                return r;
            }
        }
//        return 8;
    }

    private boolean isPrime(long num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= num / 2; i++) {
            if ((num % i) == 0)
                return false;
        }
        return true;
    }

    private BigInteger gcd(BigInteger A, BigInteger N) {
        System.out.println("A: " + A + " ;  N: " + N);
        BigInteger factor = A.mod(N);
        System.out.println("Factor: " + factor);
        while (factor.intValue() != 0) {
            A = N;
            N = factor;
            factor = A.mod(N);
            System.out.println("A: " + A + " ;  N: " + N + "; factor: " + factor);
        }
        return N;
    }
}
