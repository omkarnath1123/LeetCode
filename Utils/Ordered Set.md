# Ordered Set Pattern

## Overview
Ordered Set patterns involve maintaining a sorted collection of elements with efficient insertion, deletion, and search operations. These are particularly useful for problems requiring range queries and maintaining sorted order dynamically.

## When to Use?
1. Range queries in dynamic data
2. Finding k-th smallest/largest element
3. Maintaining sorted order with updates
4. Count of elements in range
5. Finding closest elements
6. Dynamic median finding

## Visual Representation
```
TreeSet/Red-Black Tree:
       4
      / \
     2   6
    / \ / \
   1  3 5  7

Range Query [2,5]:
[2,3,4,5]

Floor/Ceiling:
floor(3.5) = 3
ceiling(3.5) = 4
```

## Common Pattern Implementation
```java
// Using TreeSet for ordered set operations
public class OrderedSetOperations {
    private TreeSet<Integer> set;
    
    public OrderedSetOperations() {
        set = new TreeSet<>();
    }
    
    public void add(int num) {
        set.add(num);
    }
    
    public void remove(int num) {
        set.remove(num);
    }
    
    public Integer floor(int num) {
        return set.floor(num);
    }
    
    public Integer ceiling(int num) {
        return set.ceiling(num);
    }
    
    public NavigableSet<Integer> range(int from, int to) {
        return set.subSet(from, true, to, true);
    }
    
    public Integer lower(int num) {
        return set.lower(num);
    }
    
    public Integer higher(int num) {
        return set.higher(num);
    }
}
```

## Problem List

### Medium Problems
1. [220. Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii/)
2. [1818. Minimum Absolute Sum Difference](https://leetcode.com/problems/minimum-absolute-sum-difference/)
3. [729. My Calendar I](https://leetcode.com/problems/my-calendar-i/)
4. [855. Exam Room](https://leetcode.com/problems/exam-room/)
5. [2426. Number of Pairs Satisfying Inequality](https://leetcode.com/problems/number-of-pairs-satisfying-inequality/)

### Hard Problems
1. [732. My Calendar III](https://leetcode.com/problems/my-calendar-iii/)
2. [352. Data Stream as Disjoint Intervals](https://leetcode.com/problems/data-stream-as-disjoint-intervals/)
3. [715. Range Module](https://leetcode.com/problems/range-module/)

## Time & Space Complexity
1. Basic Operations:
   - Insert: O(log n)
   - Delete: O(log n)
   - Search: O(log n)
   - Floor/Ceiling: O(log n)
   - Range Query: O(log n + k) where k is size of range

2. Space Complexity:
   - O(n) for storing n elements
   - Additional O(log n) for balanced tree height

## Common Mistakes
1. Not handling duplicates properly
2. Incorrect range boundaries
3. Not considering empty set cases
4. Inefficient range queries
5. Memory leaks in custom implementations
6. Not maintaining balance in custom trees

## Optimization Techniques
1. Use built-in TreeSet/TreeMap when possible
2. Implement lazy propagation for range updates
3. Use skip lists for concurrent access
4. Balance optimization for custom trees
5. Batch operations when possible
6. Cache frequently accessed nodes

## Real-world Applications
1. Database indexing
2. Scheduling systems
3. Range-based queries
4. Priority management
5. Event timeline management
6. Temperature monitoring
7. Stock price tracking

## Advanced Concepts
1. Augmented Binary Search Trees
2. Skip Lists
3. Interval Trees
4. B-Trees
5. Red-Black Trees
6. AVL Trees
7. Splay Trees
8. Persistent Ordered Sets
