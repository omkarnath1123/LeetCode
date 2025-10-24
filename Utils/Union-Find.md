# Union-Find Pattern

## Overview
Union-Find (Disjoint Set) is a data structure that tracks a set of elements partitioned into non-overlapping subsets. It provides near-constant-time operations to add new sets, merge sets, and find if elements are in the same set.

## When to Use?
1. Finding connected components in a graph
2. Detecting cycles in a graph
3. Minimum spanning tree algorithms
4. Network connectivity
5. Image processing (connected regions)

## Visual Representation
```
Initial:    [1] [2] [3] [4] [5]
After Union(1,2), Union(3,4):
            [1] [3]
             |   |
            [2] [4]  [5]
```

## Basic Implementation
```java
class UnionFind {
    private int[] parent;
    private int[] rank;
    
    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        // Initialize each element as its own parent
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }
    
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // Path compression
        }
        return parent[x];
    }
    
    public void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return;
        
        // Union by rank
        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else if (rank[px] > rank[py]) {
            parent[py] = px;
        } else {
            parent[py] = px;
            rank[px]++;
        }
    }
    
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```

## Problem List

### Easy Problems
1. [547. Number of Provinces](https://leetcode.com/problems/number-of-provinces/)
2. [1101. The Earliest Moment When Everyone Become Friends](https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/)
3. [990. Satisfiability of Equality Equations](https://leetcode.com/problems/satisfiability-of-equality-equations/)

### Medium Problems
1. [1061. Lexicographically Smallest Equivalent String](https://leetcode.com/problems/lexicographically-smallest-equivalent-string/)
2. [1135. Connecting Cities With Minimum Cost](https://leetcode.com/problems/connecting-cities-with-minimum-cost/)
3. [1168. Optimize Water Distribution in a Village](https://leetcode.com/problems/optimize-water-distribution-in-a-village/)
4. [684. Redundant Connection](https://leetcode.com/problems/redundant-connection/)
5. [1202. Smallest String With Swaps](https://leetcode.com/problems/smallest-string-with-swaps/)

### Hard Problems
1. [1632. Rank Transform of a Matrix](https://leetcode.com/problems/rank-transform-of-a-matrix/)
2. [305. Number of Islands II](https://leetcode.com/problems/number-of-islands-ii/)
3. [928. Minimize Malware Spread II](https://leetcode.com/problems/minimize-malware-spread-ii/)

## Time & Space Complexity

### Basic Operations
- Find: O(α(n)) ≈ O(1)
- Union: O(α(n)) ≈ O(1)
- Space: O(n)

Where α(n) is the inverse Ackermann function, which grows extremely slowly.

## Optimizations

### 1. Path Compression
```python
def find(self, x):
    if self.parent[x] != x:
        self.parent[x] = self.find(self.parent[x])
    return self.parent[x]
```

### 2. Union by Rank
```python
def union(self, x, y):
    px, py = self.find(x), self.find(y)
    if self.rank[px] < self.rank[py]:
        self.parent[px] = py
    elif self.rank[px] > self.rank[py]:
        self.parent[py] = px
    else:
        self.parent[py] = px
        self.rank[px] += 1
```

## Common Applications

### 1. Graph Problems
- Connected components
- Cycle detection
- Minimum spanning tree

### 2. Network Problems
- Network connectivity
- Redundant connections
- Network optimization

### 3. Image Processing
- Connected regions
- Blob detection
- Image segmentation

## Tips & Tricks
1. Always use path compression
2. Implement union by rank
3. Consider size-based union
4. Initialize sets properly
5. Handle edge cases

## Common Mistakes
1. Forgetting path compression
2. Incorrect parent updates
3. Not using union by rank
4. Memory management issues
5. Not handling cycles

## Advanced Techniques
1. Weighted union-find
2. Size-based union
3. Dynamic connectivity
4. Persistent union-find
5. Concurrent union-find

## Real-world Applications
1. Social networks
2. Network routing
3. Image segmentation
4. Circuit design
5. Clustering algorithms

## Implementation Guidelines
1. Choose appropriate representation
2. Use optimization techniques
3. Handle special cases
4. Consider thread safety
5. Optimize memory usage

## Optimization Tips
1. Cache optimization
2. Memory layout
3. Batch operations
4. Custom allocators
5. Parallel processing
