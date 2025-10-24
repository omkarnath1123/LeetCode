# Array Pattern

## Overview
Array patterns involve techniques for manipulating and processing sequences of elements. These patterns are fundamental to solving various algorithmic problems efficiently.

## When to Use?
1. Sequential data processing
2. In-place array manipulation
3. Subarray/subsequence problems
4. Two-pointer operations
5. Sorting and searching
6. Prefix sum calculations

## Visual Representations
```
Two Pointers:         [1, 2, 3, 4, 5, 6]
                       ↑              ↑
                     left          right

Sliding Window:       [1, 2, 3, 4, 5, 6]
                      ←--window--→
                      [    k=3    ]

Kadane's Algorithm:   [-2, 1, -3, 4, -1, 2, 1, -5, 4]
                           ↑    [max subarray]    ↑
```

## Common Patterns

### 1. In-place Array Manipulation
```java
public void reverseArray(int[] arr) {
    int left = 0, right = arr.length - 1;
    while (left < right) {
        // Swap elements
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
        left++;
        right--;
    }
}
```

### 2. Prefix Sum
```java
public int[] buildPrefixSum(int[] arr) {
    int[] prefix = new int[arr.length];
    prefix[0] = arr[0];
    for (int i = 1; i < arr.length; i++) {
        prefix[i] = prefix[i-1] + arr[i];
    }
    return prefix;
}
```

## Problem List

### Easy Problems
1. [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)
2. [27. Remove Element](https://leetcode.com/problems/remove-element/)
3. [283. Move Zeroes](https://leetcode.com/problems/move-zeroes/)

### Medium Problems
1. [75. Sort Colors](https://leetcode.com/problems/sort-colors/)
2. [238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/)
3. [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/)
4. [560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)
5. [974. Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/)

### Hard Problems
1. [41. First Missing Positive](https://leetcode.com/problems/first-missing-positive/)
2. [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)
3. [84. Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/)

## Time & Space Complexity
1. Basic Array Operations:
   - Access: O(1)
   - Insertion/Deletion at end: O(1)
   - Insertion/Deletion at middle: O(n)
   - Search: O(n)

2. Common Array Algorithms:
   - Two Pointers: O(n) time, O(1) space
   - Sliding Window: O(n) time, O(1) space
   - Prefix Sum: O(n) time, O(n) space
   - Kadane's Algorithm: O(n) time, O(1) space

## Common Mistakes
1. Off-by-one errors in array indexing
2. Not handling edge cases (empty array, single element)
3. Incorrect boundary conditions in two-pointer approaches
4. Not considering integer overflow in sum calculations
5. Inefficient array resizing in dynamic arrays

## Optimization Techniques
1. Use two-pointer technique when possible
2. Implement in-place modifications
3. Use prefix sums for range queries
4. Consider sorting for certain problems
5. Use appropriate data structures (HashSet/HashMap) for lookups

## Real-world Applications
1. Database query optimization
2. Image processing
3. Signal processing
4. Financial data analysis
5. Sensor data processing
6. Memory management
7. Cache implementations

## Advanced Concepts
1. Sparse Arrays
2. Circular Arrays
3. Dynamic Programming with Arrays
4. Segment Trees
5. Fenwick Trees
6. Skip Lists
7. Sparse Tables
