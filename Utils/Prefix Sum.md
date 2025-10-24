# Prefix Sum Pattern

## Overview
Prefix Sum (also known as Cumulative Sum) is a technique for quickly calculating the sum of elements in a given range by precomputing cumulative sums.

## When to Use?
1. Range sum queries
2. Subarray sum problems
3. Difference array problems
4. Running average calculations
5. 2D range sum queries
6. Cumulative frequency calculations

## Visual Representation
```
Array:       [3,  1,  4,  2,  5]
Prefix Sum:  [3,  4,  8, 10, 15]
            
2D Prefix Sum:
Original:    Prefix Sum:
1 2 3        1  3  6
4 5 6   →    5 12 21
7 8 9       12 27 45
```

## Common Pattern Implementation
```java
public class PrefixSumPatterns {
    // 1D Prefix Sum
    public int[] buildPrefixSum(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n];
        prefix[0] = arr[0];
        
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i-1] + arr[i];
        }
        return prefix;
    }
    
    // Range Sum Query
    public int rangeSum(int[] prefix, int left, int right) {
        return left == 0 ? prefix[right] : prefix[right] - prefix[left-1];
    }
    
    // 2D Prefix Sum
    public int[][] build2DPrefixSum(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return new int[0][0];
        
        int m = matrix.length, n = matrix[0].length;
        int[][] prefix = new int[m+1][n+1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] 
                            - prefix[i-1][j-1] + matrix[i-1][j-1];
            }
        }
        return prefix;
    }
    
    // 2D Range Sum Query
    public int sumRegion(int[][] prefix, int row1, int col1, int row2, int col2) {
        return prefix[row2+1][col2+1] - prefix[row2+1][col1] 
             - prefix[row1][col2+1] + prefix[row1][col1];
    }
}
```

## Problem List

### Easy Problems
1. [303. Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable/)
2. [1480. Running Sum of 1d Array](https://leetcode.com/problems/running-sum-of-1d-array/)
3. [724. Find Pivot Index](https://leetcode.com/problems/find-pivot-index/)

### Medium Problems
1. [304. Range Sum Query 2D - Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/)
2. [523. Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/)
3. [560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)
4. [1314. Matrix Block Sum](https://leetcode.com/problems/matrix-block-sum/)
5. [1074. Number of Submatrices That Sum to Target](https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/)

### Hard Problems
1. [1444. Number of Ways of Cutting a Pizza](https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/)
2. [1478. Allocate Mailboxes](https://leetcode.com/problems/allocate-mailboxes/)
3. [1871. Jump Game VII](https://leetcode.com/problems/jump-game-vii/)

## Time & Space Complexity
1. Construction:
   - 1D Prefix Sum: O(n) time, O(n) space
   - 2D Prefix Sum: O(m*n) time, O(m*n) space
   
2. Queries:
   - Range Sum Query: O(1) time
   - 2D Range Query: O(1) time

## Common Mistakes
1. Off-by-one errors in range calculations
2. Not handling empty arrays/matrices
3. Integer overflow in large sums
4. Incorrect boundary handling
5. Not considering negative numbers
6. Wrong subtraction order in 2D queries

## Optimization Techniques
1. In-place prefix sum when possible
2. Space optimization for sparse arrays
3. Difference array for range updates
4. Modular arithmetic for large sums
5. Compressed 2D prefix sums
6. Rolling prefix sum for streaming data

## Real-world Applications
1. Financial data analysis
2. Image processing
3. Sensor data aggregation
4. Database query optimization
5. Geographic information systems
6. Network traffic analysis
7. Statistics and analytics

## Advanced Concepts
1. 2D Difference Arrays
2. Sparse Prefix Sums
3. Dynamic Prefix Sums
4. Parallel Prefix Sums
5. Segment Trees
6. Fenwick Trees
7. Range Updates with Difference Arrays
8. Prefix XOR and other operations
