# Dijkstra's Algorithm Pattern

## Overview
Dijkstra's algorithm finds the shortest paths between nodes in a weighted graph. It's particularly useful for problems involving routing, networking, and distance calculations.

## When to Use?
1. Finding shortest path in weighted graphs
2. Network routing problems
3. GPS navigation
4. Social networking distance
5. Resource distribution optimization

## Basic Template
```java
public Map<Integer, Integer> dijkstra(Map<Integer, Map<Integer, Integer>> graph, int start) {
    Map<Integer, Integer> distances = new HashMap<>();
    for (int node : graph.keySet()) {
        distances.put(node, Integer.MAX_VALUE);
    }
    distances.put(start, 0);
    
    // Priority queue of (distance, node) pairs
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
    pq.offer(new int[]{0, start});
    
    while (!pq.isEmpty()) {
        int[] current = pq.poll();
        int currDist = current[0];
        int currNode = current[1];
        
        if (currDist > distances.get(currNode)) {
            continue;
        }
        
        for (Map.Entry<Integer, Integer> neighbor : graph.get(currNode).entrySet()) {
            int nextNode = neighbor.getKey();
            int weight = neighbor.getValue();
            int distance = currDist + weight;
            
            if (distance < distances.get(nextNode)) {
                distances.put(nextNode, distance);
                pq.offer(new int[]{distance, nextNode});
            }
        }
    }
    
    return distances;
}
```

## Problem List

### Medium Problems
1. [1102. Path With Maximum Minimum Value](https://leetcode.com/problems/path-with-maximum-minimum-value/)
2. [1135. Connecting Cities With Minimum Cost](https://leetcode.com/problems/connecting-cities-with-minimum-cost/)
3. [1368. Minimum Cost to Make at Least One Valid Path in a Grid](https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/)
4. [1514. Path with Maximum Probability](https://leetcode.com/problems/path-with-maximum-probability/)
5. [1631. Path With Minimum Effort](https://leetcode.com/problems/path-with-minimum-effort/)

### Hard Problems
1. [1168. Optimize Water Distribution in a Village](https://leetcode.com/problems/optimize-water-distribution-in-a-village/)
2. [1786. Number of Restricted Paths From First to Last Node](https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/)
3. [2203. Minimum Weighted Subgraph With the Required Paths](https://leetcode.com/problems/minimum-weighted-subgraph-with-the-required-paths/)

## Variations

### 1. Multi-Source Dijkstra
```java
public Map<Integer, Integer> multiSourceDijkstra(Map<Integer, Map<Integer, Integer>> graph, List<Integer> sources) {
    Map<Integer, Integer> distances = new HashMap<>();
    for (int node : graph.keySet()) {
        distances.put(node, Integer.MAX_VALUE);
    }
    
    // Priority queue of (distance, node) pairs
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
    
    // Initialize distances for all source nodes
    for (int source : sources) {
        distances.put(source, 0);
        pq.offer(new int[]{0, source});
    }
    
    while (!pq.isEmpty()) {
        int[] current = pq.poll();
        int currDist = current[0];
        int currNode = current[1];
        
        if (currDist > distances.get(currNode)) {
            continue;
        }
        
        for (Map.Entry<Integer, Integer> neighbor : graph.get(currNode).entrySet()) {
            int nextNode = neighbor.getKey();
            int weight = neighbor.getValue();
            int distance = currDist + weight;
            
            if (distance < distances.get(nextNode)) {
                distances.put(nextNode, distance);
                pq.offer(new int[]{distance, nextNode});
            }
        }
    }
    
    return distances;
}
```

### 2. Path Reconstruction
```python
def dijkstraWithPath(graph, start, end):
    distances = {node: float('inf') for node in graph}
    distances[start] = 0
    pq = [(0, start)]
    prev = {node: None for node in graph}
    
    while pq:
        curr_dist, curr = heappop(pq)
        if curr == end:
            break
            
        if curr_dist > distances[curr]:
            continue
            
        for neighbor, weight in graph[curr].items():
            distance = curr_dist + weight
            if distance < distances[neighbor]:
                distances[neighbor] = distance
                prev[neighbor] = curr
                heappush(pq, (distance, neighbor))
    
    # Reconstruct path
    path = []
    curr = end
    while curr:
        path.append(curr)
        curr = prev[curr]
    return path[::-1]
```

## Time & Space Complexity
- Time: O((V + E) log V) with binary heap
- Space: O(V + E)
- With Fibonacci heap: O(V log V + E)

## Tips & Tricks
1. Use priority queue for efficiency
2. Handle unreachable nodes
3. Consider path reconstruction
4. Early termination when possible
5. Handle negative weights carefully

## Common Mistakes
1. Not handling disconnected graphs
2. Using with negative weights
3. Incorrect priority queue usage
4. Not checking visited nodes
5. Memory management issues

## Optimizations
1. Bidirectional search
2. A* heuristic
3. Fibonacci heap
4. Goal-directed search
5. Contraction hierarchies

## Real-world Applications
1. GPS navigation
2. Network routing
3. Social networks
4. Games pathfinding
5. Resource distribution

## Advanced Techniques
1. Dynamic weights
2. Multi-criteria paths
3. Time-dependent graphs
4. Hierarchical routing
5. Parallel implementation

## Implementation Guidelines
1. Choose appropriate data structures
2. Handle edge cases
3. Consider space-time tradeoffs
4. Add error checking
5. Optimize for specific cases
