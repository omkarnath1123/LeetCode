# Binary Indexed Tree (Fenwick Tree) Pattern

## Overview
A Binary Indexed Tree (BIT) or Fenwick Tree is a data structure that efficiently updates elements and calculates prefix sums in a table of numbers. It's particularly useful for range queries and point updates.

## When to Use?
1. Range sum queries
2. Range updates
3. Frequency counting
4. Cumulative frequency queries
5. Dynamic prefix operations

## Visual Representation
```
Array:     [1, 2, 3,  4,  5,  6,  7,  8]
BIT idx:   [1, 2, 3,  4,  5,  6,  7,  8]
Ranges:    [1][2][1:3][4][1:5][6][1:7][8]
```

## Basic Implementation
```java
class BinaryIndexedTree {
    private final int[] tree;
    private final int size;
    
    public BinaryIndexedTree(int n) {
        this.size = n;
        this.tree = new int[n + 1];
    }
    
    public void update(int idx, int delta) {
        while (idx <= size) {
            tree[idx] += delta;
            idx += idx & (-idx);  // Add least significant bit
        }
    }
    
    public int query(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += tree[idx];
            idx -= idx & (-idx);  // Remove least significant bit
        }
        return sum;
    }
    
    public int rangeQuery(int left, int right) {
        return query(right) - query(left - 1);
    }
}
```

## Problem List

### Medium Problems
1. [307. Range Sum Query - Mutable](https://leetcode.com/problems/range-sum-query-mutable/)
2. [1409. Queries on a Permutation With Key](https://leetcode.com/problems/queries-on-a-permutation-with-key/)
3. [1395. Count Number of Teams](https://leetcode.com/problems/count-number-of-teams/)
4. [1626. Best Team With No Conflicts](https://leetcode.com/problems/best-team-with-no-conflicts/)
5. [2031. Count Subarrays With More Ones Than Zeros](https://leetcode.com/problems/count-subarrays-with-more-ones-than-zeros/)

### Hard Problems
1. [315. Count of Smaller Numbers After Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self/)
2. [493. Reverse Pairs](https://leetcode.com/problems/reverse-pairs/)
3. [1649. Create Sorted Array through Instructions](https://leetcode.com/problems/create-sorted-array-through-instructions/)

## Advanced Operations

### 1. Range Update Point Query
```java
class RangeUpdateBIT {
    private final int[] bit1;
    private final int[] bit2;
    private final int size;
    
    public RangeUpdateBIT(int n) {
        this.size = n;
        this.bit1 = new int[n + 1];
        this.bit2 = new int[n + 1];
    }
    
    private void update(int idx, long val) {
        int i = idx;
        while (i <= size) {
            bit1[i] += val;
            bit2[i] += val * (idx - 1);
            i += i & (-i);
        }
    }
    
    public void rangeUpdate(int left, int right, int val) {
        update(right + 1, -val);
        update(left, val);
    }
    
    public long query(int idx) {
        long sum = 0;
        int i = idx;
        while (i > 0) {
            sum += bit1[i] * idx - bit2[i];
            i -= i & (-i);
        }
        return sum;
    }
    
    public long rangeQuery(int left, int right) {
        return query(right) - query(left - 1);
    }
}
```

### 2. 2D Binary Indexed Tree
```java
class BinaryIndexedTree2D {
    private final int[][] tree;
    private final int rows;
    private final int cols;
    
    public BinaryIndexedTree2D(int n, int m) {
        this.rows = n;
        this.cols = m;
        this.tree = new int[n + 1][m + 1];
    }
    
    public void update(int row, int col, int delta) {
        for (int i = row; i <= rows; i += i & (-i)) {
            for (int j = col; j <= cols; j += j & (-j)) {
                tree[i][j] += delta;
            }
        }
    }
    
    public int query(int row, int col) {
        int sum = 0;
        for (int i = row; i > 0; i -= i & (-i)) {
            for (int j = col; j > 0; j -= j & (-j)) {
                sum += tree[i][j];
            }
        }
        return sum;
    }
    
    public int queryRange(int row1, int col1, int row2, int col2) {
        return query(row2, col2) - query(row2, col1 - 1) 
               - query(row1 - 1, col2) + query(row1 - 1, col1 - 1);
    }
}
```

## Time & Space Complexity
### Basic Operations:
- Update: O(log n)
- Query: O(log n)
- Space: O(n)

### 2D Operations:
- Update: O(log n * log m)
- Query: O(log n * log m)
- Space: O(n * m)

## Common Applications
1. Range sum queries
2. Count inversions
3. Rectangle sum queries
4. Dynamic frequency counting
5. Order statistics

## Tips & Tricks
1. Use 1-based indexing
2. Remember LSB operation
3. Initialize tree properly
4. Handle range updates efficiently
5. Consider compression for sparse data

## Common Mistakes
1. Using 0-based indexing
2. Incorrect range calculations
3. Not handling edge cases
4. Memory management
5. Integer overflow

## Optimization Techniques
1. Coordinate compression
2. Lazy propagation
3. Memory-efficient implementation
4. Batch updates
5. Sparse representation

## Real-world Applications
1. Database queries
2. Financial calculations
3. Statistical analysis
4. Game rankings
5. Sensor data processing

## Advanced Concepts
1. Multi-dimensional BIT
2. Range updates
3. Order statistics
4. Dynamic order statistics
5. Persistent BIT

## Implementation Guidelines
1. Choose appropriate size
2. Handle edge cases
3. Consider memory constraints
4. Optimize for specific use case
5. Add error checking

## Common Variations
1. Range update range query
2. Order statistic tree
3. Dynamic ranking
4. Interval modifications
5. Multi-dimensional queries
