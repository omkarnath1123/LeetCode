# String Pattern

## Overview
String patterns involve techniques for manipulating and processing text data, including string matching, parsing, and transformation algorithms.

## When to Use?
1. Pattern matching problems
2. String manipulation/transformation
3. Palindrome problems
4. Substring problems
5. String compression
6. Text parsing and validation

## Visual Representation
```
KMP Pattern Match:
Text:    ABABDABACDABABCABAB
Pattern: ABABCABAB
         ↑
         [0,0,1,2,0,1,2,3,4]
         (Prefix function)

Rolling Hash:
"abcde" → (a×p⁴ + b×p³ + c×p² + d×p¹ + e) mod m
Window:   [abc] → [bcd] → [cde]
Hash:      h1  →  h2  →  h3
```

## Common Patterns

### 1. KMP String Matching
```java
public int[] buildPrefixFunction(String pattern) {
    int[] prefix = new int[pattern.length()];
    int j = 0;
    
    for (int i = 1; i < pattern.length(); i++) {
        while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
            j = prefix[j - 1];
        }
        if (pattern.charAt(i) == pattern.charAt(j)) {
            j++;
        }
        prefix[i] = j;
    }
    return prefix;
}

public List<Integer> kmpSearch(String text, String pattern) {
    List<Integer> matches = new ArrayList<>();
    int[] prefix = buildPrefixFunction(pattern);
    int j = 0;
    
    for (int i = 0; i < text.length(); i++) {
        while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
            j = prefix[j - 1];
        }
        if (text.charAt(i) == pattern.charAt(j)) {
            j++;
        }
        if (j == pattern.length()) {
            matches.add(i - j + 1);
            j = prefix[j - 1];
        }
    }
    return matches;
}
```

## Problem List

### Easy Problems
1. [14. Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/)
2. [28. Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/)
3. [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)

### Medium Problems
1. [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)
2. [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses/)
3. [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/)
4. [647. Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/)
5. [1209. Remove All Adjacent Duplicates in String II](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/)

### Hard Problems
1. [214. Shortest Palindrome](https://leetcode.com/problems/shortest-palindrome/)
2. [32. Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/)
3. [1216. Valid Palindrome III](https://leetcode.com/problems/valid-palindrome-iii/)

## Time & Space Complexity
1. String Operations:
   - Concatenation: O(n) time and space
   - Substring: O(n) time and space
   - charAt: O(1) time
   - Split/Join: O(n) time and space

2. String Algorithms:
   - KMP: O(n + m) time, O(m) space
   - Rabin-Karp: O(n + m) time, O(1) space
   - Manacher's: O(n) time, O(n) space
   - Z algorithm: O(n) time, O(n) space

## Common Mistakes
1. Not handling edge cases (empty string, single char)
2. Inefficient string concatenation
3. Incorrect boundary conditions
4. Not considering character encoding
5. Memory inefficient string operations
6. Off-by-one errors in substring operations

## Optimization Techniques
1. Use StringBuilder for concatenation
2. Implement efficient string matching algorithms
3. Use string pools when appropriate
4. Avoid unnecessary string object creation
5. Use character arrays for manipulation
6. Implement sliding window when possible

## Real-world Applications
1. Text editors
2. Search engines
3. Spell checkers
4. DNA sequence matching
5. Code compilers/interpreters
6. Data compression
7. Natural language processing

## Advanced Concepts
1. Suffix Arrays
2. Suffix Automata
3. Aho-Corasick Algorithm
4. Trie-based string matching
5. Boyer-Moore Algorithm
6. Ukkonen's Algorithm
7. String hashing techniques
8. Compressed string matching
