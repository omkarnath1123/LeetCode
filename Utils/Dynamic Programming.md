# Dynamic Programming

## Overview
Dynamic Programming (DP) is both a mathematical optimization method and a computer programming method. In both contexts, it refers to simplifying a complicated problem by breaking it down into simpler sub-problems in a recursive manner.

## When to Use?
Use Dynamic Programming when:
1. The problem can be broken down into smaller subproblems
2. The solution to the problem can be constructed from solutions to its subproblems
3. The problem has overlapping subproblems (same subproblems are encountered multiple times)
4. There's a need to find an optimal solution (minimum/maximum value)

## Types of Dynamic Programming
1. **Top-Down (Memoization)**
   - Start with the original problem
   - Break it down into smaller subproblems
   - Store results in a cache/memo
   ![DP Memoization](../Assets/DP_fibonacci.svg)

2. **Bottom-Up (Tabulation)**
   - Start with smallest subproblems
   - Work your way up to the original problem
   - Store results in a table
   ![DP Tabulation](../Assets/DP_knapsack.svg)

## Common DP Patterns

### 1. 0/1 Knapsack
![Knapsack Pattern](../Assets/DP_knapsack.svg)
Problems that require making choices to maximize/minimize a target value while staying within constraints.

### 2. Unbounded Knapsack
![Unbounded Knapsack](../Assets/DP_unbounded.svg)
Similar to 0/1 knapsack but items can be used multiple times.

### 3. Fibonacci Pattern
![Fibonacci Pattern](../Assets/DP_fibonacci.svg)
Problems where current state depends on previous states.

### 4. Longest Common Subsequence (LCS)
![LCS Pattern](../Assets/DP_lcs.svg)
Finding common patterns between sequences.

### 5. Matrix Chain Multiplication
![Matrix Pattern](../Assets/DP_matrix.svg)
Problems involving optimal ways to perform a series of operations.

## Problem List

### Easy (5-10 Problems)
1. [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)
2. [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)
3. [53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray/)

### Medium (25-30 Problems)
1. [322. Coin Change](https://leetcode.com/problems/coin-change/)
2. [1143. Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/)
3. [62. Unique Paths](https://leetcode.com/problems/unique-paths/)
4. [1039. Minimum Score Triangulation of Polygon](https://leetcode.com/problems/minimum-score-triangulation-of-polygon/)
5. [1216. Valid Palindrome III](https://leetcode.com/problems/valid-palindrome-iii/)
6. [1230. Toss Strange Coins](https://leetcode.com/problems/toss-strange-coins/)
7. [1218. Longest Arithmetic Subsequence of Given Difference](https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/)

### Hard (15-20 Problems)
1. [10. Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching/)
2. [115. Distinct Subsequences](https://leetcode.com/problems/distinct-subsequences/)
3. [123. Best Time to Buy and Sell Stock III](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/)
4. [1092. Shortest Common Supersequence](https://leetcode.com/problems/shortest-common-supersequence/)

## Common Techniques
1. State Definition
2. Transition Function
3. Base Cases
4. State Reduction
5. Space Optimization

## Time & Space Complexity Patterns
- Usually O(n²) for two-dimensional DPs
- Usually O(n) space can be optimized to O(1) for Fibonacci-style problems
- Usually O(n*m) for string-matching problems where n and m are string lengths

## Tips & Tricks
1. Always start with the brute force solution
2. Look for overlapping subproblems
3. Draw out the recursion tree
4. Start with memoization (easier to implement)
5. Convert to tabulation for better space complexity
