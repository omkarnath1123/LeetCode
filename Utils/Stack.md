# Stack & Monotonic Stack Patterns

## Overview
Stack-based patterns are powerful techniques for solving problems involving sequence processing, parsing, and finding next/previous greater/smaller elements. Monotonic stack is a special type of stack that maintains elements in strictly increasing or decreasing order.

## When to Use?

### Regular Stack
1. Expression evaluation
2. Parentheses matching
3. Function call tracking
4. Undo operations
5. DFS implementations

### Monotonic Stack
1. Finding next/previous greater/smaller elements
2. Rectangle histogram problems
3. Temperature span problems
4. Stock span problems
5. Maximum rectangle problems

## Visual Representation
```
Regular Stack:              Monotonic Stack (Increasing):
    top →  [5]                 top →  [5]
           [3]                        [4]
           [8]                        [3]
           [1]                        [1]
```

## Common Patterns

### 1. Next Greater Element Pattern
```java
public int[] nextGreaterElement(int[] arr) {
    Stack<Integer> stack = new Stack<>();
    int[] result = new int[arr.length];
    Arrays.fill(result, -1);
    
    for (int i = 0; i < arr.length; i++) {
        while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
            result[stack.pop()] = arr[i];
        }
        stack.push(i);
    }
    return result;
}
```

### 2. Valid Parentheses Pattern
```java
public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    Map<Character, Character> mapping = new HashMap<>();
    mapping.put(')', '(');
    mapping.put('}', '{');
    mapping.put(']', '[');
    
    for (char c : s.toCharArray()) {
        if (mapping.containsKey(c)) {
            char topElement = stack.isEmpty() ? '#' : stack.pop();
            if (topElement != mapping.get(c)) {
                return false;
            }
        } else {
            stack.push(c);
        }
    }
    
    return stack.isEmpty();
}
    for char in s:
        if char in mapping:
            if not stack or stack.pop() != mapping[char]:
                return False
        else:
            stack.append(char)
    return not stack
```

## Problem List

### Easy Problems
1. [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)
2. [155. Min Stack](https://leetcode.com/problems/min-stack/)
3. [225. Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/)

### Medium Problems
1. [1209. Remove All Adjacent Duplicates in String II](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/)
2. [735. Asteroid Collision](https://leetcode.com/problems/asteroid-collision/)
3. [901. Online Stock Span](https://leetcode.com/problems/online-stock-span/)
4. [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/)
5. [856. Score of Parentheses](https://leetcode.com/problems/score-of-parentheses/)

### Hard Problems
1. [84. Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/)
2. [85. Maximal Rectangle](https://leetcode.com/problems/maximal-rectangle/)
3. [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)

## Time & Space Complexity

### Regular Stack Operations
- Push: O(1)
- Pop: O(1)
- Top: O(1)
- Space: O(n)

### Monotonic Stack
- Time: Usually O(n) as each element is pushed/popped at most once
- Space: O(n) worst case

## Advanced Stack Patterns

### 1. Two Stack Pattern
```python
class MinStack:
    def __init__(self):
        self.stack = []
        self.minStack = []
```

### 2. Circular Array Stack
```python
def nextGreaterElements(nums):
    n = len(nums)
    stack = []
    result = [-1] * n
    
    for i in range(2 * n):
        while stack and nums[stack[-1]] < nums[i % n]:
            result[stack.pop()] = nums[i % n]
        if i < n:
            stack.append(i)
    return result
```

## Tips & Tricks
1. Consider edge cases (empty stack)
2. Handle duplicates carefully
3. Use auxiliary stacks when needed
4. Consider circular array scenarios
5. Remember LIFO property

## Common Applications
1. Browser history
2. Expression evaluation
3. Syntax parsing
4. Undo/Redo operations
5. Function call tracking

## Implementation Guidelines
1. Choose appropriate stack type
2. Consider memory constraints
3. Handle edge cases properly
4. Use clear variable names
5. Document complex operations

## Common Mistakes
1. Not checking empty stack
2. Incorrect order of operations
3. Stack overflow/underflow
4. Memory leaks
5. Incorrect comparisons

## Optimization Techniques
1. Using arrays for implementation
2. Lazy evaluation
3. Space optimization
4. Early termination
5. Combining multiple stacks
