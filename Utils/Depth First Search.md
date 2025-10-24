# Depth First Search (DFS) Pattern

## Overview
Depth First Search is a graph/tree traversal algorithm that explores as far as possible along each branch before backtracking, commonly used for exploring paths, cycles, and tree-like structures.

## When to Use?
1. Path finding problems
2. Cycle detection
3. Topological sorting
4. Maze solving
5. Tree traversal
6. Connected components
7. Backtracking problems

## Visual Representation
```
Graph DFS:             Tree DFS (Preorder):
    1                        1
   / \                     / \
  2   3         →        2   3
 / \   \               / \   \
4   5   6            4   5   6

DFS Order: [1, 2, 4, 5, 3, 6]
Stack:     [1] → [1,2] → [1,2,4] → backtrack
```

## Common Pattern Implementation
```java
public class DFSPatterns {
    // Recursive DFS
    public void dfsRecursive(Map<Integer, List<Integer>> graph, Set<Integer> visited, int node) {
        if (visited.contains(node)) return;
        
        visited.add(node);
        process(node);  // Process current node
        
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            dfsRecursive(graph, visited, neighbor);
        }
    }
    
    // Iterative DFS
    public void dfsIterative(Map<Integer, List<Integer>> graph, int start) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        
        stack.push(start);
        
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (visited.contains(node)) continue;
            
            visited.add(node);
            process(node);
            
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
    }
    
    // DFS for Cycle Detection
    private boolean hasCycle(Map<Integer, List<Integer>> graph, Set<Integer> visited, 
                           Set<Integer> recursionStack, int node) {
        visited.add(node);
        recursionStack.add(node);
        
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                if (hasCycle(graph, visited, recursionStack, neighbor)) {
                    return true;
                }
            } else if (recursionStack.contains(neighbor)) {
                return true;
            }
        }
        
        recursionStack.remove(node);
        return false;
    }
}
```

## Problem List

### Easy Problems
1. [94. Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/)
2. [100. Same Tree](https://leetcode.com/problems/same-tree/)
3. [112. Path Sum](https://leetcode.com/problems/path-sum/)

### Medium Problems
1. [200. Number of Islands](https://leetcode.com/problems/number-of-islands/)
2. [394. Decode String](https://leetcode.com/problems/decode-string/)
3. [695. Max Area of Island](https://leetcode.com/problems/max-area-of-island/)
4. [797. All Paths From Source to Target](https://leetcode.com/problems/all-paths-from-source-to-target/)
5. [1192. Critical Connections in a Network](https://leetcode.com/problems/critical-connections-in-a-network/)

### Hard Problems
1. [301. Remove Invalid Parentheses](https://leetcode.com/problems/remove-invalid-parentheses/)
2. [329. Longest Increasing Path in a Matrix](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/)
3. [834. Sum of Distances in Tree](https://leetcode.com/problems/sum-of-distances-in-tree/)

## Time & Space Complexity
1. Time Complexity:
   - O(V + E) for graphs
   - O(n) for trees
   - O(b^d) for backtracking problems

2. Space Complexity:
   - O(h) for recursion stack (h = height)
   - O(V) for visited set
   - O(V + E) for graph representation

## Common Mistakes
1. Not handling cycles
2. Stack overflow in deep recursion
3. Not tracking visited nodes
4. Incorrect backtracking
5. Memory management issues
6. Wrong recursion base case

## Optimization Techniques
1. Iterative implementation for deep graphs
2. Path compression
3. State pruning
4. Early termination
5. Tail recursion
6. Memory-efficient visited set

## Real-world Applications
1. File system traversal
2. Compiler syntax analysis
3. Puzzle solving
4. Game AI (minimax)
5. Circuit design verification
6. Resource dependency resolution
7. Network topology analysis

## Advanced Concepts
1. Articulation points and bridges
2. Strongly connected components
3. Euler paths
4. Hamilton paths
5. Iterative deepening DFS
6. Parallel DFS
7. Memory-efficient DFS
8. Distributed DFS
