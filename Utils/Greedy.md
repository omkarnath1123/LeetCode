# Greedy Algorithms Pattern

## Overview
Greedy algorithms make locally optimal choices at each step, hoping to find a global optimum. While they don't always yield the optimal solution, they are efficient and often provide good approximations.

## When to Use?
1. Making optimal choices at each step
2. Problems involving scheduling
3. Task assignment problems
4. Interval-based problems
5. Resource allocation problems

## Visual Representation
```
Initial State → [Choose Best] → [Update State] → [Repeat]
                     ↓
              Locally Optimal
                     ↓
             Hopefully Global Optimal
```

## Common Patterns

### 1. Interval Scheduling Pattern
```java
public int intervalScheduling(int[][] intervals) {
    // Sort by end time
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
    int count = 0;
    int end = Integer.MIN_VALUE;
    
    for (int[] interval : intervals) {
        if (interval[0] >= end) {
            count++;
            end = interval[1];
        }
    }
    return count;
```

### 2. Activity Selection Pattern
```java
public List<int[]> activitySelection(int[] start, int[] finish) {
    int n = start.length;
    int[][] activities = new int[n][2];
    for (int i = 0; i < n; i++) {
        activities[i] = new int[]{start[i], finish[i]};
    }
    
    // Sort by finish time
    Arrays.sort(activities, (a, b) -> Integer.compare(a[1], b[1]));
    List<int[]> selected = new ArrayList<>();
    selected.add(activities[0]);
    
    for (int i = 1; i < n; i++) {
        if (activities[i][0] >= selected.get(selected.size()-1)[1]) {
            selected.add(activities[i]);
        }
    }
    return selected;
```

## Problem List

### Easy Problems
1. [455. Assign Cookies](https://leetcode.com/problems/assign-cookies/)
2. [860. Lemonade Change](https://leetcode.com/problems/lemonade-change/)
3. [1029. Two City Scheduling](https://leetcode.com/problems/two-city-scheduling/)

### Medium Problems
1. [1024. Video Stitching](https://leetcode.com/problems/video-stitching/)
2. [1057. Campus Bikes](https://leetcode.com/problems/campus-bikes/)
3. [1090. Largest Values From Labels](https://leetcode.com/problems/largest-values-from-labels/)
4. [1167. Minimum Cost to Connect Sticks](https://leetcode.com/problems/minimum-cost-to-connect-sticks/)
5. [1144. Decrease Elements To Make Array Zigzag](https://leetcode.com/problems/decrease-elements-to-make-array-zigzag/)

### Hard Problems
1. [1168. Optimize Water Distribution in a Village](https://leetcode.com/problems/optimize-water-distribution-in-a-village/)
2. [1135. Connecting Cities With Minimum Cost](https://leetcode.com/problems/connecting-cities-with-minimum-cost/)
3. [1231. Divide Chocolate](https://leetcode.com/problems/divide-chocolate/)

## Time & Space Complexity
- Usually O(n log n) due to initial sorting
- Sometimes O(n) for simple linear scans
- Space complexity usually O(1) or O(n)

## Common Applications

### 1. Scheduling Problems
- Job sequencing
- Meeting room allocation
- Task assignment

### 2. Resource Allocation
- Memory allocation
- Processor scheduling
- Network bandwidth allocation

### 3. Optimization Problems
- Minimum spanning tree
- Huffman coding
- Coin change (when greedy works)

## Tips & Tricks
1. Sort input when dealing with intervals
2. Consider end points for interval problems
3. Think about local vs global optimum
4. Handle edge cases carefully
5. Verify greedy choice property

## Common Mistakes
1. Assuming greedy always works
2. Incorrect sorting criteria
3. Not considering edge cases
4. Overlooking better solutions
5. Not proving greedy choice property

## Proof Techniques
1. Greedy Choice Property
2. Optimal Substructure
3. Exchange Arguments
4. Induction
5. Contradiction

## Real-world Applications
1. Job scheduling
2. File compression
3. Load balancing
4. Network routing
5. Resource allocation

## Advanced Concepts
1. Matroids
2. Interval Scheduling
3. Huffman Coding
4. Minimum Spanning Trees
5. Set Cover Approximation

## Implementation Guidelines
1. Sort when necessary
2. Use priority queues for dynamic ordering
3. Maintain state efficiently
4. Consider problem constraints
5. Validate greedy approach

## Optimization Techniques
1. Custom sorting
2. Priority queues
3. Early termination
4. State compression
5. Efficient data structures
