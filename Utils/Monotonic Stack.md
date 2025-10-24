# Monotonic Stack Pattern

## Overview
A Monotonic Stack is a stack that maintains its elements either in strictly increasing or strictly decreasing order. It's particularly useful for finding the next/previous greater/smaller elements.

## When to Use?
1. Finding next/previous greater element
2. Finding next/previous smaller element
3. Rectangle histogram problems
4. Temperature span problems
5. Stock price span problems
6. Range query problems

## Visual Representation
```
Array:           [3, 1, 4, 1, 5, 9, 2, 6]
Next Greater:    [4, 4, 5, 5, 9, -1, 6, -1]

Monotonic Stack Process:
[3] → [1] → [1,4] → [1] → [1,5] → [1,5,9] → [1,2] → [1,2,6]
```

## Common Pattern Implementation
```java
public class MonotonicStack {
    // Next Greater Element
    public int[] nextGreaterElement(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                result[stack.pop()] = arr[i];
            }
            stack.push(i);
        }
        return result;
    }
    
    // Previous Smaller Element
    public int[] previousSmallerElement(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                result[stack.pop()] = arr[i];
            }
            stack.push(i);
        }
        return result;
    }
}
```

## Problem List

### Medium Problems
1. [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/)
2. [901. Online Stock Span](https://leetcode.com/problems/online-stock-span/)
3. [456. 132 Pattern](https://leetcode.com/problems/132-pattern/)
4. [503. Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/)
5. [1019. Next Greater Node In Linked List](https://leetcode.com/problems/next-greater-node-in-linked-list/)

### Hard Problems
1. [84. Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/)
2. [85. Maximal Rectangle](https://leetcode.com/problems/maximal-rectangle/)
3. [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)

## Time & Space Complexity
1. Standard Operations:
   - Push: O(1)
   - Pop: O(1)
   - Top: O(1)
2. Overall Algorithm:
   - Time: O(n) - each element pushed/popped once
   - Space: O(n) - stack storage

## Common Mistakes
1. Wrong monotonic order (increasing vs decreasing)
2. Not handling duplicates properly
3. Incorrect index tracking
4. Stack overflow in large inputs
5. Not clearing stack between test cases
6. Incorrect initialization of result array

## Optimization Techniques
1. Use array-based stack implementation
2. Process elements in optimal order
3. Combine multiple passes into one
4. Handle circular array efficiently
5. Use parallel processing for large inputs
6. Memory-efficient implementations

## Real-world Applications
1. Stock price analysis
2. Temperature monitoring
3. Building skyline problems
4. Network packet processing
5. Resource allocation
6. Load balancing
7. Time series analysis

## Advanced Concepts
1. Bi-directional monotonic stack
2. Monotonic stack with lazy propagation
3. Segment tree with monotonic property
4. Dynamic programming with monotonic stack
5. Parallel monotonic stack algorithms
6. Distributed monotonic stack
7. Persistent monotonic stack
