# Bottom-Up Solution Pattern

## Overview
Bottom-Up (Tabulation) is a dynamic programming approach that solves problems by building up solutions from smaller subproblems to larger ones, typically using iteration rather than recursion.

## When to Use?
1. Dynamic Programming problems
2. Memory optimization is needed
3. Stack overflow concerns with recursive solutions
4. Problems with overlapping subproblems
5. Problems requiring optimal substructure
6. When all subproblems need to be solved

## Visual Representation
```
Fibonacci Bottom-Up:
[1, 1, 2, 3, 5, 8, 13, 21]
 ↑  ↑  ↑
    ↑  ↑  ↑
       ↑  ↑  ↑

Matrix Chain Multiplication:
   A1  A2  A3  A4
A1  0   *   *   *
A2      0   *   *
A3          0   *
A4              0

Fill diagonally:
* = min cost of multiplying matrices
```

## Common Pattern Implementation
```java
public class BottomUpDP {
    // Example: Bottom-up Fibonacci
    public int fibonacci(int n) {
        if (n <= 1) return n;
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    
    // Example: Bottom-up Knapsack
    public int knapsack(int[] values, int[] weights, int capacity) {
        int n = values.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i-1] <= w) {
                    dp[i][w] = Math.max(
                        values[i-1] + dp[i-1][w - weights[i-1]],
                        dp[i-1][w]
                    );
                } else {
                    dp[i][w] = dp[i-1][w];
                }
            }
        }
        return dp[n][capacity];
    }
}
```

## Problem List

### Easy Problems
1. [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)
2. [53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray/)
3. [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)

### Medium Problems
1. [322. Coin Change](https://leetcode.com/problems/coin-change/)
2. [300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)
3. [1143. Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/)
4. [516. Longest Palindromic Subsequence](https://leetcode.com/problems/longest-palindromic-subsequence/)
5. [1049. Last Stone Weight II](https://leetcode.com/problems/last-stone-weight-ii/)

### Hard Problems
1. [10. Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching/)
2. [312. Burst Balloons](https://leetcode.com/problems/burst-balloons/)
3. [1092. Shortest Common Supersequence](https://leetcode.com/problems/shortest-common-supersequence/)

## Time & Space Complexity
1. Time: Usually O(n²) or O(nm) for two-state problems
2. Space: O(n) or O(nm) depending on dimensions
3. Space optimization possible to O(n) for many problems
4. Iteration overhead is less than recursion
5. Cache-friendly memory access patterns

## Common Mistakes
1. Incorrect base case initialization
2. Wrong state transition
3. Not handling edge cases
4. Unnecessary space usage
5. Incorrect loop order
6. Off-by-one errors in table filling

## Optimization Techniques
1. Space optimization using rolling arrays
2. Cache-friendly access patterns
3. State compression when possible
4. Eliminate redundant calculations
5. Use primitive arrays instead of objects
6. Early termination when possible

## Real-world Applications
1. Resource allocation
2. Investment strategies
3. Sequence alignment in biology
4. Text similarity algorithms
5. Network routing
6. Game theory problems
7. Optimization problems

## Advanced Concepts
1. State compression techniques
2. Multi-dimensional DP
3. Divide and conquer optimization
4. Knuth's optimization
5. Convex hull optimization
6. Slope trick optimization
7. Quadrangle inequality
8. Alien trick
