# Bit Mask Pattern

## Overview
Bit masking is a technique that uses bits to represent sets, flags, or states. It's particularly useful for state compression and efficient set operations.

## When to Use?
1. Set operations
2. State compression in DP
3. Permission/Flag management
4. Subset generation
5. Optimization problems
6. Resource allocation

## Visual Representation
```
Set Operations:
A = 1 0 1 1 0  (22 in decimal)
B = 1 1 0 1 0  (26 in decimal)

A | B  = 1 1 1 1 0  (Union)
A & B  = 1 0 0 1 0  (Intersection)
A ^ B  = 0 1 1 0 0  (Symmetric Difference)
~A    = 0 1 0 0 1  (Complement)
```

## Common Pattern Implementation
```java
public class BitMaskOperations {
    // Generate all subsets
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        
        for (int mask = 0; mask < (1 << n); mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }
            result.add(subset);
        }
        return result;
    }
    
    // State compression DP example
    public int travelingSalesman(int[][] dist) {
        int n = dist.length;
        int[][] dp = new int[1 << n][n];
        
        for (int mask = 0; mask < (1 << n); mask++) {
            Arrays.fill(dp[mask], Integer.MAX_VALUE);
        }
        dp[1][0] = 0;  // Start at city 0
        
        for (int mask = 1; mask < (1 << n); mask++) {
            for (int curr = 0; curr < n; curr++) {
                if ((mask & (1 << curr)) == 0) continue;
                
                for (int next = 0; next < n; next++) {
                    if ((mask & (1 << next)) != 0) continue;
                    int newMask = mask | (1 << next);
                    dp[newMask][next] = Math.min(
                        dp[newMask][next],
                        dp[mask][curr] + dist[curr][next]
                    );
                }
            }
        }
        return dp[(1 << n) - 1][n - 1];
    }
}
```

## Problem List

### Medium Problems
1. [78. Subsets](https://leetcode.com/problems/subsets/)
2. [1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix](https://leetcode.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix/)
3. [1327. List the Products Ordered in a Period](https://leetcode.com/problems/list-the-products-ordered-in-a-period/)
4. [1371. Find the Longest Substring Containing Vowels in Even Counts](https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/)
5. [1457. Pseudo-Palindromic Paths in a Binary Tree](https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/)

### Hard Problems
1. [847. Shortest Path Visiting All Nodes](https://leetcode.com/problems/shortest-path-visiting-all-nodes/)
2. [1349. Maximum Students Taking Exam](https://leetcode.com/problems/maximum-students-taking-exam/)
3. [1434. Number of Ways to Wear Different Hats to Each Other](https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other/)

## Time & Space Complexity
1. Bit Operations: O(1)
2. Subset Generation: O(2^n)
3. State Space: O(2^n) typically
4. DP with bitmasks: O(2^n * n) typical
5. Memory: Usually O(2^n)

## Common Mistakes
1. Not handling sign bit
2. Incorrect bit shifting
3. Integer overflow
4. Wrong mask construction
5. Forgetting to consider all states
6. Inefficient state transitions

## Optimization Techniques
1. Use built-in bit operations
2. Precompute bit counts
3. Use lookup tables
4. State compression
5. Efficient state transitions
6. Parallel bit operations

## Real-world Applications
1. Permissions systems
2. Resource management
3. Cache systems
4. Network protocols
5. Game state management
6. Compiler optimizations
7. Hardware design

## Advanced Concepts
1. Gosper's Hack
2. Parallel bit counting
3. Dynamic programming optimization
4. Gray codes
5. Hamming distance optimization
6. State compression techniques
7. Bit-parallel algorithms
