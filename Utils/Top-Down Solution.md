# Top-Down Solution Pattern (Memoization)

## Overview
Top-Down solution pattern uses recursion with memoization to solve problems by breaking them down into smaller subproblems and caching results to avoid redundant computations.

## When to Use?
1. Dynamic Programming problems
2. When recursive solution is intuitive
3. When all subproblems aren't needed
4. Problems with optimal substructure
5. When space complexity isn't critical
6. Complex state transitions

## Visual Representation
```
Fibonacci Top-Down:
                 fib(5)
              /          \
         fib(4)          fib(3)
        /      \        /      \
    fib(3)    fib(2)  fib(2)  fib(1)
    /    \
fib(2)  fib(1)

With Memoization:
{1: 1, 2: 1, 3: 2, 4: 3, 5: 5}
```

## Common Pattern Implementation
```java
public class TopDownDP {
    // Example: Fibonacci with memoization
    private Map<Integer, Integer> memo = new HashMap<>();
    
    public int fibonacci(int n) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) return memo.get(n);
        
        memo.put(n, fibonacci(n-1) + fibonacci(n-2));
        return memo.get(n);
    }
    
    // Example: Longest Common Subsequence
    private Integer[][] memo;
    
    public int longestCommonSubsequence(String text1, String text2) {
        memo = new Integer[text1.length()][text2.length()];
        return lcs(text1, text2, text1.length()-1, text2.length()-1);
    }
    
    private int lcs(String s1, String s2, int i, int j) {
        if (i < 0 || j < 0) return 0;
        if (memo[i][j] != null) return memo[i][j];
        
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = 1 + lcs(s1, s2, i-1, j-1);
        } else {
            memo[i][j] = Math.max(
                lcs(s1, s2, i-1, j),
                lcs(s1, s2, i, j-1)
            );
        }
        return memo[i][j];
    }
}
```

## Problem List

### Easy Problems
1. [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)
2. [509. Fibonacci Number](https://leetcode.com/problems/fibonacci-number/)
3. [1137. N-th Tribonacci Number](https://leetcode.com/problems/n-th-tribonacci-number/)

### Medium Problems
1. [322. Coin Change](https://leetcode.com/problems/coin-change/)
2. [494. Target Sum](https://leetcode.com/problems/target-sum/)
3. [1049. Last Stone Weight II](https://leetcode.com/problems/last-stone-weight-ii/)
4. [337. House Robber III](https://leetcode.com/problems/house-robber-iii/)
5. [1155. Number of Dice Rolls With Target Sum](https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/)

### Hard Problems
1. [10. Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching/)
2. [887. Super Egg Drop](https://leetcode.com/problems/super-egg-drop/)
3. [1289. Minimum Falling Path Sum II](https://leetcode.com/problems/minimum-falling-path-sum-ii/)

## Time & Space Complexity
1. Time: O(n * m) for filling memo table
2. Space:
   - O(n * m) for memo table
   - O(n) additional for recursion stack
3. Recursion depth: Usually O(n)
4. Cache hits: O(1)

## Common Mistakes
1. Not initializing memo structure
2. Incorrect memo key construction
3. Stack overflow in deep recursion
4. Redundant state parameters
5. Not handling base cases
6. Inefficient state representation

## Optimization Techniques
1. Use arrays instead of HashMap for memoization
2. Optimize state representation
3. Remove unnecessary parameters
4. Use primitive types when possible
5. Clear memo between test cases
6. Tail recursion optimization

## Real-world Applications
1. Resource allocation
2. Path finding
3. Game theory problems
4. Financial modeling
5. Sequence alignment
6. Pattern matching
7. Decision making systems

## Advanced Concepts
1. Memoization with multiple parameters
2. State compression in memoization
3. Iterative deepening
4. Memory-efficient memoization
5. Parallel memoization
6. Probabilistic memoization
7. Adaptive memoization
8. Persistent memoization tables
