# Monotonic Queue Pattern

## Overview
A Monotonic Queue is a data structure that maintains elements in a strictly increasing or decreasing order. Elements that break the monotonic property are removed from the back.

## When to Use?
1. Finding maximum/minimum in sliding window
2. Next greater/smaller element problems
3. Online processing of streaming data
4. Maximum/minimum range queries
5. Stock price span problems

## Visual Representation
```
Original Array:     [3, 1, 4, 1, 5, 9, 2, 6]
                     ↓
Monotonic Dec:     [3]
                     ↓
                   [3, 1]
                     ↓
                   [4]
                     ↓
                   [4, 1]
                     ↓
                   [5]
                    ...
```

## Common Pattern Implementation
```java
class MonotonicQueue {
    private Deque<Integer> deque;
    
    public MonotonicQueue() {
        deque = new LinkedList<>();
    }
    
    // Maintains decreasing monotonic property
    public void push(int num) {
        while (!deque.isEmpty() && deque.peekLast() < num) {
            deque.pollLast();
        }
        deque.offerLast(num);
    }
    
    public int getMax() {
        return deque.peekFirst();
    }
    
    public void pop(int num) {
        if (!deque.isEmpty() && deque.peekFirst() == num) {
            deque.pollFirst();
        }
    }
}
```

## Problem List

### Medium Problems
1. [239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/)
2. [1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit](https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/)
3. [862. Shortest Subarray with Sum at Least K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/)
4. [901. Online Stock Span](https://leetcode.com/problems/online-stock-span/)
5. [1425. Constrained Subsequence Sum](https://leetcode.com/problems/constrained-subsequence-sum/)

### Hard Problems
1. [1562. Find Latest Group of Size M](https://leetcode.com/problems/find-latest-group-of-size-m/)
2. [1499. Max Value of Equation](https://leetcode.com/problems/max-value-of-equation/)
3. [1687. Delivering Boxes from Storage to Ports](https://leetcode.com/problems/delivering-boxes-from-storage-to-ports/)

## Time & Space Complexity
1. Push Operation: O(1) amortized
2. Pop Operation: O(1)
3. Get Maximum/Minimum: O(1)
4. Space Complexity: O(n) worst case

## Common Mistakes
1. Not handling duplicates properly
2. Incorrect monotonic property (increasing vs decreasing)
3. Forgetting to remove elements from front
4. Not considering edge cases
5. Inefficient implementation leading to O(n) operations

## Optimization Techniques
1. Use ArrayDeque instead of LinkedList for better performance
2. Maintain indices instead of values for more complex problems
3. Combine with sliding window technique
4. Early termination when possible
5. Memory-efficient implementations

## Real-world Applications
1. Stock price analysis
2. Network packet processing
3. Temperature monitoring systems
4. Resource allocation in operating systems
5. Traffic flow monitoring
6. Real-time analytics
7. Sensor data processing

## Advanced Concepts
1. Multi-dimensional monotonic queues
2. Parallel monotonic queues
3. Lazy removal techniques
4. Persistent monotonic queues
5. Distributed implementations
6. Dynamic monotonic queues
7. Self-adjusting monotonic queues
