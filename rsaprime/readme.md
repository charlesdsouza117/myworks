RSA works on the product of two large prime numbers. The product of these two numbers forms a part of the public key.

N (public key) = p * q

We can figure out N by intercepting a message. The challenge is to find p & q, so that we can successfully decrypt the message.

Lets say N = 77. We need to find out p & q.

1. Guess a number, g.
2. g^r = mN+1.
3. To find r, while (g^i % N != 1) i++.
4. r = i, when g^i % N == 1

(g^(r/2)+1) (g^(r/2)-1) = mN

A = g^(r/2)+1
B = g^(r/2)-1

Take A, and find the GCD.
while(A%N !=0) {
    A = N;
    N = R; (Remainder)
}

when R == 0, one of the factor is N.

Now find the other factor using B, or simply N / factor1.
