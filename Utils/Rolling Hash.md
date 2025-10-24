# Rolling Hash Pattern

## Overview
Rolling Hash is a technique that computes a hash value for a sliding window of elements, allowing efficient updates as the window moves. It's particularly useful for string matching and substring problems.

## When to Use?
1. String matching problems
2. Substring search
3. Repeated string detection
4. Pattern finding in sequences
5. String similarity comparison
6. DNA sequence matching

## Visual Representation
```
Text:    "abcdefg"
Window:   [abc] → [bcd] → [cde] → [def]
Hash:      h1  →  h2  →  h3  →  h4

Hash Update Formula:
h2 = (h1 - 'a'*p^(k-1))*p + 'd'

Example with p=31, m=10^9+9:
"abc" → (a×p² + b×p¹ + c) mod m
```

## Common Pattern Implementation
```java
public class RollingHash {
    private static final long P = 31;  // Prime base
    private static final long M = 1_000_000_009;  // Large prime modulus
    
    public long[] computeHashes(String s, int length) {
        int n = s.length() - length + 1;
        long[] hashes = new long[n];
        long hash = 0;
        long power = 1;
        
        // Compute hash for first window
        for (int i = 0; i < length; i++) {
            hash = (hash + (s.charAt(i) - 'a' + 1) * power) % M;
            if (i < length - 1) {
                power = (power * P) % M;
            }
        }
        hashes[0] = hash;
        
        // Rolling hash for remaining windows
        long firstPower = power;
        for (int i = 1; i < n; i++) {
            hash = ((hash - (s.charAt(i-1) - 'a' + 1) * firstPower % M + M) * P 
                   + (s.charAt(i+length-1) - 'a' + 1)) % M;
            hashes[i] = hash;
        }
        
        return hashes;
    }
}
```

## Problem List

### Medium Problems
1. [1044. Longest Duplicate Substring](https://leetcode.com/problems/longest-duplicate-substring/)
2. [187. Repeated DNA Sequences](https://leetcode.com/problems/repeated-dna-sequences/)
3. [1147. Longest Chunked Palindrome Decomposition](https://leetcode.com/problems/longest-chunked-palindrome-decomposition/)
4. [1316. Distinct Echo Substrings](https://leetcode.com/problems/distinct-echo-substrings/)
5. [1554. Strings Differ by One Character](https://leetcode.com/problems/strings-differ-by-one-character/)

### Hard Problems
1. [214. Shortest Palindrome](https://leetcode.com/problems/shortest-palindrome/)
2. [1397. Find All Good Strings](https://leetcode.com/problems/find-all-good-strings/)
3. [1923. Longest Common Subpath](https://leetcode.com/problems/longest-common-subpath/)

## Time & Space Complexity
1. Hash Computation:
   - Initial: O(m) for window size m
   - Rolling update: O(1)
2. Space: O(n) for storing all hashes
3. Probability of collision: O(1/M)
4. Total time for string matching: O(n + m)

## Common Mistakes
1. Poor choice of base and modulus
2. Not handling hash collisions
3. Integer overflow issues
4. Incorrect rolling calculation
5. Not using double hashing for verification
6. Modular arithmetic errors

## Optimization Techniques
1. Use double hashing to reduce collisions
2. Choose large prime modulus
3. Precompute powers of base
4. Use multiple hash functions
5. Implement efficient modular arithmetic
6. Cache frequently used values

## Real-world Applications
1. Plagiarism detection
2. DNA sequence analysis
3. File similarity checking
4. Network packet inspection
5. Data deduplication
6. Content-based file chunking
7. Version control systems

## Advanced Concepts
1. Multi-pattern matching with rolling hash
2. Probabilistic data structures
3. Locality-sensitive hashing
4. Winnowing algorithm
5. Chunk-based deduplication
6. Content-defined chunking
7. Rolling hash for streaming data
