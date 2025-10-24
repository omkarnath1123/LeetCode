# Binary Search Pattern

## Overview
Binary Search is a divide and conquer algorithm that efficiently finds a target value within a sorted array. However, its applications go far beyond simple searching, extending to optimization problems and decision problems.

## When to Use?
1. Searching in a sorted array
2. Finding minimum/maximum values that satisfy a condition
3. Finding peak elements
4. Optimization problems where monotonicity exists
5. Finding square roots or similar mathematical computations

## Types of Binary Search

### 1. Classical Binary Search
```
left = 0, right = length-1
while left <= right:
    mid = left + (right - left) // 2
    if array[mid] == target:
        return mid
    else if array[mid] < target:
        left = mid + 1
    else:
        right = mid - 1
```

### 2. Binary Search on Answer Space
```
left = min_possible
right = max_possible
while left < right:
    mid = left + (right - left) // 2
    if is_feasible(mid):
        right = mid
    else:
        left = mid + 1
```

## Visual Representation
![Binary Search Process](../Assets/binary_search.svg)

## Problem List

### Easy Problems
1. [704. Binary Search](https://leetcode.com/problems/binary-search/)
2. [35. Search Insert Position](https://leetcode.com/problems/search-insert-position/)
3. [69. Sqrt(x)](https://leetcode.com/problems/sqrtx/)

### Medium Problems
1. [1201. Ugly Number III](https://leetcode.com/problems/ugly-number-iii/)
2. [1231. Divide Chocolate](https://leetcode.com/problems/divide-chocolate/)
3. [1011. Capacity To Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/)
4. [875. Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/)
5. [1482. Minimum Number of Days to Make m Bouquets](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/)

### Hard Problems
1. [4. Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)
2. [1044. Longest Duplicate Substring](https://leetcode.com/problems/longest-duplicate-substring/)
3. [410. Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum/)

## Time & Space Complexity
- Time Complexity: O(log n) for standard binary search
- Space Complexity: O(1) for iterative implementation
- Space Complexity: O(log n) for recursive implementation

## Common Templates

### 1. Left-most insertion point
```
while left < right:
    mid = left + (right - left) // 2
    if nums[mid] >= target:
        right = mid
    else:
        left = mid + 1
```

### 2. Right-most insertion point
```
while left < right:
    mid = left + (right - left + 1) // 2
    if nums[mid] <= target:
        left = mid
    else:
        right = mid - 1
```

## Tips & Tricks
1. Always handle edge cases (empty array, single element)
2. Be careful with integer overflow in mid calculation
3. Choose the right template based on problem requirements
4. For floating-point numbers, set appropriate precision
5. Consider using predicate functions for complex conditions

## Common Mistakes to Avoid
1. Infinite loops due to incorrect mid calculation
2. Off-by-one errors in boundary conditions
3. Not considering duplicates
4. Wrong comparison operators
5. Incorrect handling of search space boundaries

## Real-world Applications
1. Database indexing and searching
2. Finding optimal values in monotonic functions
3. Rate limiting algorithms
4. Load balancing
5. Resource allocation problems
