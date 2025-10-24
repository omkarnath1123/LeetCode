# Backtracking Pattern

## Overview
Backtracking is an algorithmic technique that considers searching every possible combination in order to solve a computational problem, particularly constraint satisfaction problems.

## When to Use?
1. Finding all possible combinations/permutations
2. Solving puzzles (Sudoku, N-Queens)
3. Finding all possible paths
4. Making decisions with constraints
5. State space exploration

## Visual Representation
```
                    Root
                /    |    \
            Choice1 Choice2 Choice3
           /  |  \
      Choice4 Choice5 Choice6
        |
    Backtrack if invalid
```

## Template Pattern
```python
def backtrack(candidate):
    if find_solution(candidate):
        output(candidate)
        return
    
    for next_candidate in list_of_candidates:
        if is_valid(next_candidate):
            place(next_candidate)
            backtrack(next_candidate)
            remove(next_candidate)
```

## Problem List

### Easy Problems
1. [401. Binary Watch](https://leetcode.com/problems/binary-watch/)
2. [784. Letter Case Permutation](https://leetcode.com/problems/letter-case-permutation/)
3. [1087. Brace Expansion](https://leetcode.com/problems/brace-expansion/)

### Medium Problems
1. [1096. Brace Expansion II](https://leetcode.com/problems/brace-expansion-ii/)
2. [1088. Confusing Number II](https://leetcode.com/problems/confusing-number-ii/)
3. [46. Permutations](https://leetcode.com/problems/permutations/)
4. [47. Permutations II](https://leetcode.com/problems/permutations-ii/)
5. [39. Combination Sum](https://leetcode.com/problems/combination-sum/)

### Hard Problems
1. [51. N-Queens](https://leetcode.com/problems/n-queens/)
2. [37. Sudoku Solver](https://leetcode.com/problems/sudoku-solver/)
3. [126. Word Ladder II](https://leetcode.com/problems/word-ladder-ii/)

## Common Patterns

### 1. Choice Pattern
```python
def backtrack(choices, path):
    if len(path) == target_length:
        result.append(path[:])
        return
        
    for choice in choices:
        path.append(choice)
        backtrack(choices, path)
        path.pop()
```

### 2. State Space Tree Pattern
```python
def backtrack(state, candidates):
    if is_valid_solution(state):
        solutions.append(state.copy())
        return
        
    for candidate in candidates:
        if is_valid_state(state + candidate):
            backtrack(state + candidate, candidates)
```

## Time & Space Complexity
- Time: O(k^n) where k is branches per state, n is depth
- Space: O(n) for recursion stack

## Tips & Tricks
1. Always validate states before recursing
2. Use pruning to optimize
3. Consider sorting input for duplicates
4. Use visited sets for optimization
5. Remember to remove choices after exploring

## Common Mistakes to Avoid
1. Forgetting to backtrack (remove choices)
2. Not handling base cases
3. Incorrect state validation
4. Memory issues with large inputs
5. Not pruning efficiently

## Optimization Techniques
1. State Pruning
2. Branch Pruning
3. Symmetry Breaking
4. Constraint Propagation
5. Forward Checking

## Real-world Applications
1. Constraint satisfaction problems
2. Circuit design verification
3. Game playing AI
4. Resource allocation
5. Schedule planning

## Advanced Concepts
1. Dancing Links (DLX)
2. Iterative Deepening
3. Alpha-Beta Pruning
4. Forward Checking
5. Conflict-Directed Backjumping

## Implementation Tips
1. Use recursion for cleaner code
2. Maintain state efficiently
3. Consider iterative approaches for memory
4. Use helper functions for clarity
5. Document state changes clearly
