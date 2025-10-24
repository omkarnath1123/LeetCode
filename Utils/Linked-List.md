# Linked List Pattern

## Overview
Linked List patterns involve manipulating sequences of nodes where each node points to the next node in the sequence. These patterns are crucial for many data structure implementations and algorithmic problems.

## When to Use?
1. Sequential data access
2. Dynamic data structure needs
3. Memory allocation flexibility
4. Merging or splitting sequences
5. Implementing LRU Cache
6. Cycle detection problems

## Visual Representation
```
Singly Linked List:
[1] → [2] → [3] → [4] → null

Cycle Detection:
[1] → [2] → [3] → [4]
      ↑           ↓
      [6] ← [5]

Fast/Slow Pointers:
[1] → [2] → [3] → [4] → [5] → [6]
 s     f
      s     f
           s     f
```

## Common Patterns

### 1. Basic Node Structure
```java
public class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}
```

### 2. Floyd's Cycle Detection
```java
public boolean hasCycle(ListNode head) {
    if (head == null) return false;
    
    ListNode slow = head;
    ListNode fast = head;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) return true;
    }
    
    return false;
}
```

## Problem List

### Easy Problems
1. [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/)
2. [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)
3. [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)

### Medium Problems
1. [1171. Remove Zero Sum Consecutive Nodes from Linked List](https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/)
2. [2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers/)
3. [143. Reorder List](https://leetcode.com/problems/reorder-list/)
4. [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)
5. [148. Sort List](https://leetcode.com/problems/sort-list/)

### Hard Problems
1. [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)
2. [25. Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/)
3. [146. LRU Cache](https://leetcode.com/problems/lru-cache/)

## Time & Space Complexity
1. Basic Operations:
   - Access: O(n)
   - Insert at beginning: O(1)
   - Delete at beginning: O(1)
   - Insert/Delete at end: O(n)
   - Search: O(n)

2. Common Algorithms:
   - Reverse: O(n) time, O(1) space
   - Cycle Detection: O(n) time, O(1) space
   - Merge Two Lists: O(n+m) time, O(1) space
   - Find Middle: O(n) time, O(1) space

## Common Mistakes
1. Not handling null pointers
2. Losing the head reference
3. Not updating next pointers correctly
4. Memory leaks in languages without GC
5. Incorrect termination conditions
6. Not handling cycles properly

## Optimization Techniques
1. Use dummy nodes for edge cases
2. Implement two-pointer techniques
3. In-place operations when possible
4. Cache frequently accessed nodes
5. Use sentinel nodes
6. Maintain tail pointers when needed

## Real-world Applications
1. Memory allocation systems
2. File systems
3. Music playlist implementation
4. Browser history
5. Undo functionality
6. Hash table collision chains
7. Graph adjacency lists

## Advanced Concepts
1. XOR Linked Lists
2. Skip Lists
3. Self-adjusting lists
4. Memory-efficient linked lists
5. Lock-free linked lists
6. Persistent linked lists
7. Concurrent linked lists
