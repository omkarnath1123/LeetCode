# Breadth First Search (BFS) Pattern

## Overview
Breadth First Search is a graph/tree traversal algorithm that explores nodes level by level, visiting all nodes at the current depth before moving to nodes at the next depth level.

## When to Use?
1. Shortest path in unweighted graphs
2. Level-order traversal
3. Connected components
4. Web crawling
5. Social networking (friend circles)
6. Shortest path in maze
7. Word ladder problems

## Visual Representation
```
Graph BFS:               Level Order Tree:
    1                        1
   / \                     / \
  2   3         →        2   3
 / \   \               / \   \
4   5   6            4   5   6

BFS Order: [1, 2, 3, 4, 5, 6]
Levels:    [1] → [2,3] → [4,5,6]
```

## Common Pattern Implementation
```java
public class BFSPatterns {
    // Graph BFS
    public void bfs(Map<Integer, List<Integer>> graph, int start) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        
        queue.offer(start);
        visited.add(start);
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            process(node);  // Process current node
            
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
    }
    
    // Level-order Tree Traversal
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
}
```

## Problem List

### Easy Problems
1. [101. Symmetric Tree](https://leetcode.com/problems/symmetric-tree/)
2. [111. Minimum Depth of Binary Tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/)
3. [559. Maximum Depth of N-ary Tree](https://leetcode.com/problems/maximum-depth-of-n-ary-tree/)

### Medium Problems
1. [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)
2. [1091. Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/)
3. [909. Snakes and Ladders](https://leetcode.com/problems/snakes-and-ladders/)
4. [433. Minimum Genetic Mutation](https://leetcode.com/problems/minimum-genetic-mutation/)
5. [752. Open the Lock](https://leetcode.com/problems/open-the-lock/)

### Hard Problems
1. [127. Word Ladder](https://leetcode.com/problems/word-ladder/)
2. [126. Word Ladder II](https://leetcode.com/problems/word-ladder-ii/)
3. [815. Bus Routes](https://leetcode.com/problems/bus-routes/)

## Time & Space Complexity
1. Time Complexity:
   - O(V + E) for graphs
   - O(n) for trees
   - O(b^d) for game trees (b=branching, d=depth)

2. Space Complexity:
   - O(V) for visited set
   - O(w) for queue (w = max width)
   - O(V + E) for graph representation

## Common Mistakes
1. Not marking nodes as visited
2. Visiting nodes multiple times
3. Queue overflow in large graphs
4. Not tracking levels properly
5. Memory management issues
6. Incorrect neighbor generation

## Optimization Techniques
1. Use visited set to avoid cycles
2. Bidirectional BFS for faster search
3. Level-wise processing
4. Early termination
5. Memory-efficient queue implementation
6. State compression

## Real-world Applications
1. Network routing protocols
2. Social network analysis
3. Web crawlers
4. GPS navigation systems
5. Game AI pathfinding
6. Garbage collection
7. Recommendation systems

## Advanced Concepts
1. Bidirectional BFS
2. Multi-source BFS
3. Priority-based BFS
4. Parallel BFS
5. Memory-efficient BFS
6. A* Search (informed BFS)
7. Interactive BFS
8. Concurrent BFS
