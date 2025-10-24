# Topological Sort Pattern

## Overview
Topological sorting is used for ordering vertices in a directed acyclic graph (DAG) such that for every directed edge u → v, vertex u comes before v in the ordering. It's crucial for dependency resolution and scheduling.

## When to Use?
1. Course scheduling
2. Build system dependencies
3. Task scheduling with prerequisites
4. Package dependencies
5. Project management

## Visual Representation
```
Dependencies:     Topological Sort:
A → B → C        One valid order:
↓   ↓            A → D → B → C
D → E            → E
```

## Common Patterns

### 1. Kahn's Algorithm (BFS)
```java
public List<Integer> topologicalSort(List<List<Integer>> graph, int V) {
    int[] indegree = new int[V];
    // Calculate in-degree for each vertex
    for (int u = 0; u < V; u++) {
        for (int v : graph.get(u)) {
            indegree[v]++;
        }
    }
    
    // Add all vertices with 0 in-degree to queue
    Queue<Integer> queue = new LinkedList<>();
    for (int u = 0; u < V; u++) {
        if (indegree[u] == 0) {
            queue.offer(u);
        }
    }
    
    List<Integer> result = new ArrayList<>();
    while (!queue.isEmpty()) {
        int u = queue.poll();
        result.add(u);
        
        for (int v : graph.get(u)) {
            indegree[v]--;
            if (indegree[v] == 0) {
                queue.offer(v);
            }
        }
    }
    
    // If size of result is less than V, there's a cycle
    return result.size() == V ? result : new ArrayList<>();
}
```

### 2. DFS Method
```java
public List<Integer> topologicalSortDFS(List<List<Integer>> graph, int V) {
    boolean[] visited = new boolean[V];
    List<Integer> stack = new ArrayList<>();
    
    for (int u = 0; u < V; u++) {
        if (!visited[u]) {
            dfs(u, visited, stack, graph);
        }
    }
    
    // Reverse the stack to get topological order
    Collections.reverse(stack);
    return stack;
}

private void dfs(int u, boolean[] visited, List<Integer> stack, List<List<Integer>> graph) {
    visited[u] = true;
    
    for (int v : graph.get(u)) {
        if (!visited[v]) {
            dfs(v, visited, stack, graph);
        }
    }
    
    stack.add(u);
}
```

## Problem List

### Medium Problems
1. [207. Course Schedule](https://leetcode.com/problems/course-schedule/)
2. [210. Course Schedule II](https://leetcode.com/problems/course-schedule-ii/)
3. [1136. Parallel Courses](https://leetcode.com/problems/parallel-courses/)
4. [269. Alien Dictionary](https://leetcode.com/problems/alien-dictionary/)
5. [444. Sequence Reconstruction](https://leetcode.com/problems/sequence-reconstruction/)

### Hard Problems
1. [1203. Sort Items by Groups Respecting Dependencies](https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies/)
2. [329. Longest Increasing Path in a Matrix](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/)
3. [1591. Strange Printer II](https://leetcode.com/problems/strange-printer-ii/)

## Time & Space Complexity
### Kahn's Algorithm:
- Time: O(V + E)
- Space: O(V)

### DFS Method:
- Time: O(V + E)
- Space: O(V) for recursion stack

## Common Applications

### 1. Build Systems
```java
class BuildSystem {
    private Map<String, List<String>> graph;
    private Set<String> built;
    
    public BuildSystem() {
        graph = new HashMap<>();
        built = new HashSet<>();
    }
    
    public void addDependency(String target, String dependency) {
        graph.putIfAbsent(target, new ArrayList<>());
        graph.get(target).add(dependency);
    }
    
    public boolean build(String target) {
        if (built.contains(target)) {
            return true;
        }
        
        if (!graph.containsKey(target)) {
            graph.put(target, new ArrayList<>());
        }
        
        for (String dep : graph.get(target)) {
            if (!build(dep)) {
                return false;
            }
        }
        
        // Build target
        built.add(target);
        return true;
    }
    
    public void reset() {
        built.clear();
    }
}
```

### 2. Task Scheduler
```java
public List<String> scheduleTasksWithDependencies(List<String> tasks, List<Pair<String, String>> dependencies) {
    // Build adjacency list and in-degree map
    Map<String, List<String>> graph = new HashMap<>();
    Map<String, Integer> inDegree = new HashMap<>();
    
    // Initialize graph and in-degree for all tasks
    for (String task : tasks) {
        graph.put(task, new ArrayList<>());
        inDegree.put(task, 0);
    }
    
    // Build the graph
    for (Pair<String, String> dep : dependencies) {
        String prev = dep.getKey();
        String next = dep.getValue();
        graph.get(prev).add(next);
        inDegree.put(next, inDegree.get(next) + 1);
    }
    
    // Add all tasks with no dependencies to queue
    Queue<String> queue = new LinkedList<>();
    for (String task : tasks) {
        if (inDegree.get(task) == 0) {
            queue.offer(task);
        }
    }
    
    List<String> schedule = new ArrayList<>();
    
    // Process tasks in topological order
    while (!queue.isEmpty()) {
        String task = queue.poll();
        schedule.add(task);
        
        for (String nextTask : graph.get(task)) {
            inDegree.put(nextTask, inDegree.get(nextTask) - 1);
            if (inDegree.get(nextTask) == 0) {
                queue.offer(nextTask);
            }
        }
    }
    
    // Check if all tasks were scheduled
    return schedule.size() == tasks.size() ? schedule : new ArrayList<>();
}
```

## Tips & Tricks
1. Always check for cycles
2. Consider using both BFS and DFS approaches
3. Track in-degrees for BFS approach
4. Use visited set for DFS approach
5. Handle disconnected components

## Common Mistakes
1. Not detecting cycles
2. Incorrect dependency tracking
3. Not handling all vertices
4. Wrong edge direction
5. Memory management issues

## Advanced Techniques
1. Cycle detection
2. Minimum height ordering
3. Lexicographically smallest order
4. Layer-by-layer processing
5. Priority-based ordering

## Real-world Applications
1. Software build systems
2. Course prerequisites
3. Project scheduling
4. Dependency resolution
5. Data processing pipelines

## Optimization Techniques
1. Early cycle detection
2. Priority queues for ordering
3. Batch processing
4. Memory-efficient representations
5. Parallel processing

## Implementation Guidelines
1. Choose appropriate graph representation
2. Handle cycles gracefully
3. Consider multiple valid orders
4. Optimize for specific use cases
5. Add error handling

## Common Variations
1. All possible orderings
2. Minimum height ordering
3. Grouping with dependencies
4. Dynamic dependencies
5. Weighted dependencies
