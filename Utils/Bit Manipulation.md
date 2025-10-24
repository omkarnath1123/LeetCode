# Bit Manipulation Pattern

## Overview
Bit manipulation involves directly manipulating individual bits in data. These techniques are crucial for optimization, working with flags, and solving various algorithmic problems efficiently.

## When to Use?
1. Space optimization
2. Flag handling
3. Power of two problems
4. XOR-based problems
5. Binary number operations

## Common Operations

### Basic Operations
```
AND (&): 1 & 1 = 1, others = 0
OR (|): 0 | 0 = 0, others = 1
XOR (^): Same = 0, Different = 1
NOT (~): Flips all bits
Left Shift (<<): Multiplies by 2^n
Right Shift (>>): Divides by 2^n
```

### Common Bit Tricks
```java
// Check if number is power of 2
public boolean isPowerOfTwo(int n) {
    return n > 0 && (n & (n - 1)) == 0;
}

// Count set bits
public int countSetBits(int n) {
    int count = 0;
    while (n != 0) {
        n &= (n - 1);  // Clear least significant bit
        count++;
    }
    return count;
}

// Get/Set/Clear bit operations
public boolean getBit(int num, int i) {
    return (num & (1 << i)) != 0;
}

public int setBit(int num, int i) {
    return num | (1 << i);
}

public int clearBit(int num, int i) {
    return num & ~(1 << i);
}
```

## Problem List

### Easy Problems
1. [191. Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits/)
2. [461. Hamming Distance](https://leetcode.com/problems/hamming-distance/)
3. [136. Single Number](https://leetcode.com/problems/single-number/)

### Medium Problems
1. [1239. Maximum Length of a Concatenated String with Unique Characters](https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/)
2. [1318. Minimum Flips to Make a OR b Equal to c](https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/)
3. [1404. Number of Steps to Reduce a Number in Binary Representation to One](https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/)
4. [1442. Count Triplets That Can Form Two Arrays of Equal XOR](https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/)
5. [1457. Pseudo-Palindromic Paths in a Binary Tree](https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/)

### Hard Problems
1. [1178. Number of Valid Words for Each Puzzle](https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/)
2. [1434. Number of Ways to Wear Different Hats to Each Other](https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other/)
3. [1611. Minimum One Bit Operations to Make Integers Zero](https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero/)

## Common Patterns

### 1. Single Number Pattern
```java
public int singleNumber(int[] nums) {
    int result = 0;
    for (int num : nums) {
        result ^= num;
    }
    return result;
}
```

### 2. Bit Mask Pattern
```java
public List<List<Integer>> generateSubsets(int[] nums) {
    int n = nums.length;
    List<List<Integer>> subsets = new ArrayList<>();
    
    // Generate all possible masks from 0 to 2^n - 1
    for (int mask = 0; mask < (1 << n); mask++) {
        List<Integer> subset = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) {
                subset.add(nums[i]);
            }
        }
        subsets.add(subset);
    }
    return subsets;
}
```

## Time & Space Complexity
- Most bit operations: O(1)
- Traversing bits: O(log n) or O(1) for fixed-width integers
- Space: Usually O(1)

## Advanced Techniques

### 1. Bit DP
```python
def countBits(n):
    dp = [0] * (n + 1)
    for i in range(1, n + 1):
        dp[i] = dp[i >> 1] + (i & 1)
    return dp
```

### 2. State Compression
```python
def compressState(items):
    state = 0
    for item in items:
        state |= (1 << item)
    return state
```

## Tips & Tricks
1. Use parentheses with bit operations
2. Remember operator precedence
3. Consider unsigned vs signed
4. Test edge cases
5. Use masks for efficiency

## Common Applications
1. Permissions and flags
2. Memory optimization
3. Error detection/correction
4. Hardware interaction
5. State compression

## Optimization Techniques
1. Lookup tables
2. Parallel bit counting
3. SIMD operations
4. Bit tricks
5. Branch elimination

## Common Mistakes
1. Sign extension issues
2. Operator precedence
3. Integer overflow
4. Off-by-one errors
5. Platform dependencies

## Real-world Applications
1. Network protocols
2. File permissions
3. Graphics processing
4. Data compression
5. Hardware drivers

## Advanced Concepts
1. Gray codes
2. Gosper's hack
3. Bit-scanning instructions
4. Population count
5. Perfect hashing
