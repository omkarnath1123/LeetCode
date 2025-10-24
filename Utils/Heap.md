# Heap (Priority Queue) Pattern

## Overview
Heap is a specialized tree-based data structure that satisfies the heap property. It's commonly used in problems involving finding/maintaining the k-th largest/smallest elements, median finding, and scheduling.

## When to Use?
1. K-th largest/smallest element problems
2. Stream processing
3. Scheduling problems
4. Median maintenance
5. Graph algorithms (Dijkstra, Prim's)

## Types of Heaps

### 1. Min Heap
- Root is the minimum element
- Parent always smaller than children
```java
// Min Heap
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
minHeap.offer(5);  // Adds element to min heap

// Max Heap
// Use reversed natural ordering for max heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
maxHeap.offer(5);  // Adds element to max heap

// Alternative max heap with custom comparator
PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>((a, b) -> b - a);
```

## Problem List

### Easy Problems
1. [1046. Last Stone Weight](https://leetcode.com/problems/last-stone-weight/)
2. [703. Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream/)
3. [1167. Minimum Cost to Connect Sticks](https://leetcode.com/problems/minimum-cost-to-connect-sticks/)

### Medium Problems
1. [1066. Campus Bikes II](https://leetcode.com/problems/campus-bikes-ii/)
2. [1090. Largest Values From Labels](https://leetcode.com/problems/largest-values-from-labels/)
3. [1102. Path With Maximum Minimum Value](https://leetcode.com/problems/path-with-maximum-minimum-value/)
4. [1135. Connecting Cities With Minimum Cost](https://leetcode.com/problems/connecting-cities-with-minimum-cost/)
5. [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/)

### Hard Problems
1. [1168. Optimize Water Distribution in a Village](https://leetcode.com/problems/optimize-water-distribution-in-a-village/)
2. [295. Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/)
3. [480. Sliding Window Median](https://leetcode.com/problems/sliding-window-median/)

## Common Patterns

### 1. K-th Element Pattern
```java
public int findKthLargest(int[] nums, int k) {
    // Use min heap for k-th largest
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    
    for (int num : nums) {
        heap.offer(num);
        if (heap.size() > k) {
            heap.poll();
        }
    }
    return heap.peek();
}
```

### 2. Merge K Sorted Pattern
```java
public List<Integer> mergeKSorted(List<List<Integer>> lists) {
    // Custom class to store element value and its position
    class Element {
        int val, listIndex, elementIndex;
        Element(int v, int li, int ei) {
            val = v;
            listIndex = li;
            elementIndex = ei;
        }
    }
    
    // Min heap ordered by element value
    PriorityQueue<Element> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
    
    // Add first element from each list
    for (int i = 0; i < lists.size(); i++) {
        if (!lists.get(i).isEmpty()) {
            heap.offer(new Element(lists.get(i).get(0), i, 0));
        }
    }
    
    List<Integer> result = new ArrayList<>();
    while (!heap.isEmpty()) {
        Element curr = heap.poll();
        result.add(curr.val);
        
        // If there are more elements in the current list, add the next one
        if (curr.elementIndex + 1 < lists.get(curr.listIndex).size()) {
            heap.offer(new Element(
                lists.get(curr.listIndex).get(curr.elementIndex + 1),
                curr.listIndex,
                curr.elementIndex + 1
            ));
        }
    }
    
    return result;
}
```

## Time & Space Complexity

### Basic Operations
- Insert: O(log n)
- Delete: O(log n)
- Get Min/Max: O(1)
- Build Heap: O(n)
- Space: O(n)

## Advanced Applications

### 1. Median Finding
```java
class MedianFinder {
    // Max heap for the smaller half
    private PriorityQueue<Integer> small;
    // Min heap for the larger half
    private PriorityQueue<Integer> large;
    
    public MedianFinder() {
        // Max heap for smaller numbers
        small = new PriorityQueue<>(Collections.reverseOrder());
        // Min heap for larger numbers
        large = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        // Add to max heap first
        small.offer(num);
        
        // Balance the heaps
        large.offer(small.poll());
        if (large.size() > small.size()) {
            small.offer(large.poll());
        }
    }
    
    public double findMedian() {
        if (small.size() > large.size()) {
            return small.peek();
        } else {
            return (small.peek() + large.peek()) / 2.0;
        }
    }
        heappush(self.large, -heappop(self.small))
        if len(self.large) > len(self.small):
            heappush(self.small, -heappop(self.large))
```

### 2. Sliding Window Maximum
```python
def maxSlidingWindow(nums, k):
    heap = []  # (-value, index)
    result = []
    
    for i, num in enumerate(nums):
        while heap and heap[0][1] <= i - k:
            heappop(heap)
        heappush(heap, (-num, i))
        if i >= k - 1:
            result.append(-heap[0][0])
    return result
```

## Tips & Tricks
1. Use negative numbers for max heap
2. Keep track of indices for deletion
3. Handle duplicates carefully
4. Consider lazy deletion
5. Use multiple heaps when needed

## Common Mistakes
1. Wrong heap type (min vs max)
2. Not handling duplicates
3. Memory leaks
4. Incorrect comparators
5. Not removing stale elements

## Real-world Applications
1. Task scheduling
2. Network traffic shaping
3. Event-driven simulation
4. Data stream processing
5. Operating system scheduling

## Implementation Guidelines
1. Choose appropriate heap type
2. Handle edge cases
3. Consider custom comparators
4. Optimize memory usage
5. Consider thread safety

## Advanced Concepts
1. Binomial Heaps
2. Fibonacci Heaps
3. Double-ended Priority Queues
4. Concurrent Priority Queues
5. External Memory Heaps

## Optimization Techniques
1. Lazy deletion
2. Custom comparators
3. Bulk operations
4. Memory pooling
5. Index management
