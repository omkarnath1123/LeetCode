# Two Pointer Pattern

## Overview
The Two Pointer technique uses two pointers to iterate through a data structure in a single pass. This pattern is especially useful for solving problems with minimal space complexity and is commonly used with arrays, linked lists, and strings.

## When to Use?
1. When dealing with sorted arrays
2. When searching for pairs in an array
3. When you need to compare elements at different positions
4. When dealing with palindromes or reverse operations
5. When you need to find a set of elements that fulfill certain constraints

## Types of Two Pointer Patterns

### 1. Opposite Directional
- One pointer starts from beginning, other from end
- Move towards each other
```
left = 0, right = length-1
while left < right:
    // process elements
    // move pointers based on condition
```

### 2. Same Directional
- Both pointers move in same direction
- Usually at different speeds
```
slow = fast = start
while fast and fast.next:
    slow = slow.next
    fast = fast.next.next
```

### 3. Window Pattern
- One pointer leads, other follows
- Maintains a window of elements
```
start = 0
for end in range(len(array)):
    // process window
    while condition_not_met:
        // shrink window
        start++
```

## Problem List

### Easy Problems
1. [1. Two Sum](https://leetcode.com/problems/two-sum/)
2. [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)
3. [344. Reverse String](https://leetcode.com/problems/reverse-string/)

### Medium Problems
1. [1055. Shortest Way to Form String](https://leetcode.com/problems/shortest-way-to-form-string/)
2. [1214. Two Sum BSTs](https://leetcode.com/problems/two-sum-bsts/)
3. [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/)
4. [15. 3Sum](https://leetcode.com/problems/3sum/)
5. [16. 3Sum Closest](https://leetcode.com/problems/3sum-closest/)

### Hard Problems
1. [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)
2. [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)

## Time & Space Complexity
- Usually O(n) time complexity for linear traversal
- Usually O(1) space complexity as only two pointers are used
- For sorting-based solutions: O(nlogn) time complexity

## Common Techniques
1. Sort array first if order doesn't matter
2. Use while loops for flexible pointer movement
3. Handle duplicates carefully
4. Consider using three pointers for some problems
5. Use fast/slow pointers for cycle detection

## Tips & Tricks
1. Always check for empty/single element arrays
2. Be careful with pointer updates to avoid infinite loops
3. Consider edge cases where pointers meet
4. Handle duplicates appropriately
5. When using with sorting, decide if sorting affects the problem constraints

## Common Mistakes to Avoid
1. Forgetting to check array bounds
2. Incorrect pointer updates
3. Not handling duplicates
4. Unnecessary pointer movements
5. Not considering edge cases

## Real-world Applications
1. Merging sorted arrays
2. Finding palindromes
3. Detecting cycles in linked lists
4. Finding pairs with target sum
5. String matching and comparison
