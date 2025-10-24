# Graph Traversal Patterns (DFS & BFS)

## Overview
Graph traversal algorithms are fundamental techniques for exploring and processing graph data structures. The two primary traversal methods are Depth-First Search (DFS) and Breadth-First Search (BFS).

## When to Use?
### Use DFS when:
1. Exploring paths to leaf nodes
2. Finding cycles
3. Topological sorting
4. Solving maze-like problems
5. Exploring deep hierarchies

### Use BFS when:
1. Finding shortest path
2. Level-wise traversal
3. Finding nodes at k distance
4. State space exploration
5. Social network connections

## Visual Representation
![DFS vs BFS Traversal](../Assets/graph_traversal.svg)

## Common Patterns

### 1. DFS Template
```java
private void dfs(int node, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
    if (visited.contains(node)) {
        return;
    }
    visited.add(node);
    process(node);
    for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
        dfs(neighbor, visited, graph);
    }
}
```

### 2. BFS Template
```java
private void bfs(int start, Map<Integer, List<Integer>> graph) {
    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    
    queue.offer(start);
    visited.add(start);
    
    while (!queue.isEmpty()) {
        int node = queue.poll();
        process(node);
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                queue.offer(neighbor);
            }
        }
    }
}
                visited.add(neighbor)
                queue.append(neighbor)
```

## Problem List

### Easy Problems
1. [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)
2. [111. Minimum Depth of Binary Tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/)
3. [100. Same Tree](https://leetcode.com/problems/same-tree/)

### Medium Problems
1. [1041. Robot Bounded In Circle](https://leetcode.com/problems/robot-bounded-in-circle/)
2. [1102. Path With Maximum Minimum Value](https://leetcode.com/problems/path-with-maximum-minimum-value/)
3. [1197. Minimum Knight Moves](https://leetcode.com/problems/minimum-knight-moves/)
4. [1215. Stepping Numbers](https://leetcode.com/problems/stepping-numbers/)
5. [1101. The Earliest Moment When Everyone Become Friends](https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/)

### Hard Problems
1. [1168. Optimize Water Distribution in a Village](https://leetcode.com/problems/optimize-water-distribution-in-a-village/)
2. [1192. Critical Connections in a Network](https://leetcode.com/problems/critical-connections-in-a-network/)
3. [1136. Parallel Courses](https://leetcode.com/problems/parallel-courses/)

## Time & Space Complexity
### DFS:
- Time: O(V + E) where V is vertices and E is edges
- Space: O(h) where h is height of recursion stack

### BFS:
- Time: O(V + E)
- Space: O(w) where w is maximum width of the graph

## Special Variants

### 1. Bidirectional BFS
- Used when start and end points are known
- Searches from both directions
- Significantly faster for certain problems

### 2. Iterative Deepening DFS
- Combines benefits of DFS and BFS
- Memory-efficient like DFS
- Complete like BFS

## Common Applications

### DFS Applications:
1. Cycle detection
2. Path finding
3. Maze solving
4. Topological sorting
5. Connected components

### BFS Applications:
1. Shortest path in unweighted graph
2. Level order traversal
3. Finding connected components
4. Testing bipartiteness
5. Social networking features

## Tips & Tricks
1. Always track visited nodes
2. Consider both recursive and iterative implementations
3. Choose appropriate data structures (Stack for DFS, Queue for BFS)
4. Handle cycles carefully
5. Consider edge cases (empty graph, single node)

## Common Mistakes
1. Forgetting to mark nodes as visited
2. Incorrect handling of cycles
3. Not considering disconnected components
4. Stack overflow in recursive DFS
5. Memory overflow in BFS for wide graphs

## Advanced Techniques
1. Bi-directional search
2. A* search
3. Iterative deepening
4. Multi-source BFS
5. Topological sort variations
