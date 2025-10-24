# Queue Pattern

## Overview
Queue patterns involve processing elements in First-In-First-Out (FIFO) order, essential for breadth-first traversals, level-order processing, and scheduling problems.

## When to Use?
1. Level-order traversals
2. BFS algorithms
3. Task scheduling
4. Cache implementation
5. Request processing
6. Stream processing
7. Job scheduling

## Visual Representation
```
Basic Queue:
Front → [1] [2] [3] [4] ← Back
Dequeue: 1 → [2] [3] [4]
Enqueue: [2] [3] [4] [5]

Circular Queue:
     [1]
[6] [2]
[5] [3]
   [4]
```

## Common Pattern Implementation
```java
public class QueuePatterns {
    // Level Order Traversal using Queue
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(currentLevel);
        }
        return result;
    }
    
    // Circular Queue Implementation
    class CircularQueue {
        private int[] array;
        private int front;
        private int rear;
        private int size;
        
        public CircularQueue(int capacity) {
            array = new int[capacity];
            front = 0;
            rear = -1;
            size = 0;
        }
        
        public boolean enqueue(int value) {
            if (size == array.length) return false;
            rear = (rear + 1) % array.length;
            array[rear] = value;
            size++;
            return true;
        }
        
        public boolean dequeue() {
            if (size == 0) return false;
            front = (front + 1) % array.length;
            size--;
            return true;
        }
    }
}
```

## Problem List

### Easy Problems
1. [225. Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/)
2. [232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/)
3. [933. Number of Recent Calls](https://leetcode.com/problems/number-of-recent-calls/)

### Medium Problems
1. [622. Design Circular Queue](https://leetcode.com/problems/design-circular-queue/)
2. [641. Design Circular Deque](https://leetcode.com/problems/design-circular-deque/)
3. [1429. First Unique Number](https://leetcode.com/problems/first-unique-number/)
4. [950. Reveal Cards In Increasing Order](https://leetcode.com/problems/reveal-cards-in-increasing-order/)
5. [362. Design Hit Counter](https://leetcode.com/problems/design-hit-counter/)

### Hard Problems
1. [239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/)
2. [358. Rearrange String k Distance Apart](https://leetcode.com/problems/rearrange-string-k-distance-apart/)
3. [862. Shortest Subarray with Sum at Least K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/)

## Time & Space Complexity
1. Basic Operations:
   - Enqueue: O(1)
   - Dequeue: O(1)
   - Peek: O(1)
   - Size: O(1)

2. Special Implementations:
   - Circular Queue: All operations O(1)
   - Priority Queue: Insert/Delete O(log n)
   - Double-ended Queue: All operations O(1)

## Common Mistakes
1. Not checking queue capacity
2. Incorrect front/rear pointer management
3. Memory leaks in custom implementations
4. Not handling empty queue cases
5. Incorrect circular queue indexing
6. Race conditions in concurrent access

## Optimization Techniques
1. Use appropriate queue implementation
2. Implement circular buffer for fixed size
3. Batch processing when possible
4. Memory pooling for objects
5. Lock-free implementations
6. Cache-friendly memory layout

## Real-world Applications
1. Process scheduling
2. Print job spooling
3. Message queues
4. Request processing
5. BFS in social networks
6. Traffic management
7. Operating system scheduling

## Advanced Concepts
1. Priority Queues
2. Double-ended Queues
3. Blocking Queues
4. Concurrent Queues
5. Distributed Queues
6. Real-time Queues
7. Lock-free Queues
8. Persistent Queues
