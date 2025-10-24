# Mathematical Patterns

## Overview
Mathematical patterns involve techniques for solving numerical problems, including number theory, combinatorics, probability, and geometry-based problems.

## When to Use?
1. Number theory problems (GCD, prime numbers)
2. Combinatorial problems
3. Probability calculations
4. Geometric computations
5. Matrix operations
6. Modular arithmetic

## Visual Representation
```
Prime Sieve:          Binary Exponentiation:
[2, 3, 4, 5, 6, 7]   3^10 = (3^2)^5
  ↓                     = 9^5
[2, 3, ×, 5, ×, 7]     = (9^2)^2 * 9
                        = 81^2 * 9

GCD:                  Matrix Multiplication:
GCD(48, 18)          [1 2] × [5 6] = [19 22]
48 = 2 × 18 + 12     [3 4]   [7 8]   [43 50]
18 = 1 × 12 + 6
12 = 2 × 6 + 0
GCD = 6
```

## Common Patterns

### 1. Binary Exponentiation
```java
public long binaryExponentiation(long base, long power, long mod) {
    long result = 1;
    while (power > 0) {
        if ((power & 1) == 1) {
            result = (result * base) % mod;
        }
        base = (base * base) % mod;
        power >>= 1;
    }
    return result;
}
```

### 2. Prime Number Generation (Sieve)
```java
public boolean[] sieveOfEratosthenes(int n) {
    boolean[] isPrime = new boolean[n + 1];
    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;
    
    for (int i = 2; i * i <= n; i++) {
        if (isPrime[i]) {
            for (int j = i * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
    }
    return isPrime;
}
```

## Problem List

### Easy Problems
1. [204. Count Primes](https://leetcode.com/problems/count-primes/)
2. [231. Power of Two](https://leetcode.com/problems/power-of-two/)
3. [1201. Ugly Number III](https://leetcode.com/problems/ugly-number-iii/)

### Medium Problems
1. [50. Pow(x, n)](https://leetcode.com/problems/powx-n/)
2. [372. Super Pow](https://leetcode.com/problems/super-pow/)
3. [1780. Check if Number is Sum of Powers of Three](https://leetcode.com/problems/check-if-number-is-sum-of-powers-of-three/)
4. [2183. Count Array Pairs Divisible by K](https://leetcode.com/problems/count-array-pairs-divisible-by-k/)
5. [1643. Kth Smallest Instructions](https://leetcode.com/problems/kth-smallest-instructions/)

### Hard Problems
1. [878. Nth Magical Number](https://leetcode.com/problems/nth-magical-number/)
2. [1622. Fancy Sequence](https://leetcode.com/problems/fancy-sequence/)
3. [2117. Abbreviating the Product of a Range](https://leetcode.com/problems/abbreviating-the-product-of-a-range/)

## Time & Space Complexity
1. Binary Exponentiation: O(log n) time, O(1) space
2. Sieve of Eratosthenes: O(n log log n) time, O(n) space
3. GCD (Euclidean): O(log min(a,b)) time
4. Prime Factorization: O(√n) time
5. Matrix Multiplication: O(n³) time for n×n matrices

## Common Mistakes
1. Integer overflow not handled
2. Modular arithmetic errors
3. Floating-point precision issues
4. Not considering edge cases
5. Inefficient prime number generation
6. Missing base cases in recursion

## Optimization Techniques
1. Use binary exponentiation for powers
2. Implement modular arithmetic for large numbers
3. Use bit manipulation when possible
4. Cache frequently used calculations
5. Use mathematical properties to simplify calculations
6. Implement iterative solutions instead of recursive

## Real-world Applications
1. Cryptography
2. Computer Graphics
3. Financial Calculations
4. Scientific Computing
5. Game Development
6. Data Compression
7. Error Detection/Correction

## Advanced Concepts
1. Number Field Sieve
2. Chinese Remainder Theorem
3. Fast Fourier Transform
4. Gaussian Elimination
5. Euler's Totient Function
6. Linear Diophantine Equations
7. Pollard's Rho Algorithm
8. Miller-Rabin Primality Test
