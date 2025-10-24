# Hash Table & String Patterns

## Overview
Hash Table and String manipulation patterns are fundamental techniques used in solving a wide variety of programming problems, especially those involving character frequency, anagrams, and substring operations.

## When to Use?
1. Frequency counting
2. Anagram detection
3. Substring problems
4. Two-sum type problems
5. Caching/Memoization

## Common String Patterns

### 1. Character Frequency Pattern
```java
public Map<Character, Integer> getFrequencyMap(String s) {
    Map<Character, Integer> freq = new HashMap<>();
    for (char c : s.toCharArray()) {
        freq.put(c, freq.getOrDefault(c, 0) + 1);
    }
    return freq;
}
```

### 2. Sliding Window with Hash Table
```java
public void slidingWindowWithHash(String s) {
    Map<Character, Integer> window = new HashMap<>();
    int left = 0;
    
    for (int right = 0; right < s.length(); right++) {
        // Add character
        char c = s.charAt(right);
        window.put(c, window.getOrDefault(c, 0) + 1);
        
        // Contract window if needed
        while (/* condition */) {
            char leftChar = s.charAt(left);
            window.put(leftChar, window.get(leftChar) - 1);
            if (window.get(leftChar) == 0) {
                window.remove(leftChar);
            }
            left++;
        }
    }
}
                del window[s[left]]
            left += 1
```

## Problem List

### Easy Problems
1. [1. Two Sum](https://leetcode.com/problems/two-sum/)
2. [217. Contains Duplicate](https://leetcode.com/problems/contains-duplicate/)
3. [1062. Longest Repeating Substring](https://leetcode.com/problems/longest-repeating-substring/)

### Medium Problems
1. [1055. Shortest Way to Form String](https://leetcode.com/problems/shortest-way-to-form-string/)
2. [1100. Find K-Length Substrings With No Repeated Characters](https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/)
3. [1156. Swap For Longest Repeated Character Substring](https://leetcode.com/problems/swap-for-longest-repeated-character-substring/)
4. [1166. Design File System](https://leetcode.com/problems/design-file-system/)
5. [1182. Shortest Distance to Target Color](https://leetcode.com/problems/shortest-distance-to-target-color/)

### Hard Problems
1. [1044. Longest Duplicate Substring](https://leetcode.com/problems/longest-duplicate-substring/)
2. [1092. Shortest Common Supersequence](https://leetcode.com/problems/shortest-common-supersequence/)
3. [1216. Valid Palindrome III](https://leetcode.com/problems/valid-palindrome-iii/)

## Common Hash Table Applications

### 1. Two Sum Pattern
```java
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> seen = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (seen.containsKey(complement)) {
            return new int[]{seen.get(complement), i};
        }
        seen.put(nums[i], i);
    }
    return new int[]{};
}
```

### 2. Group Anagrams Pattern
```java
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> groups = new HashMap<>();
    
    for (String s : strs) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String key = new String(chars);
        
        groups.putIfAbsent(key, new ArrayList<>());
        groups.get(key).add(s);
    }
    
    return new ArrayList<>(groups.values());
}
```

## Time & Space Complexity

### Hash Table Operations
- Insert: O(1) average
- Delete: O(1) average
- Search: O(1) average
- Space: O(n)

### String Operations
- Concatenation: O(n)
- Substring: O(1) to O(n)
- Split/Join: O(n)

## Advanced Techniques

### 1. Rolling Hash
```java
public long rollingHash(String s) {
    long hashValue = 0;
    final int PRIME = 31;
    final long MOD = 1_000_000_009;
    
    for (char c : s.toCharArray()) {
        hashValue = (hashValue * PRIME + (c - 'a' + 1)) % MOD;
    }
    return hashValue;
}
```

### 2. Trie Implementation
```python
class TrieNode:
    def __init__(self):
        self.children = {}
        self.isEnd = False
```

## Tips & Tricks
1. Consider using Counter class in Python
2. Handle collisions properly
3. Use appropriate hash functions
4. Consider case sensitivity
5. Handle special characters

## Common String Problems
1. Palindrome checking
2. String matching
3. String compression
4. Word break problems
5. Pattern matching

## Optimization Techniques
1. Using built-in methods
2. Avoiding string concatenation
3. Using StringBuilder
4. Proper hash function selection
5. Load factor management

## Real-world Applications
1. Spell checkers
2. Document similarity
3. Caching systems
4. Symbol tables
5. Database indexing

## Common Mistakes
1. Not handling collisions
2. Incorrect hash functions
3. Memory leaks
4. Poor load factor
5. String immutability issues

## Advanced Concepts
1. Perfect Hashing
2. Consistent Hashing
3. Bloom Filters
4. String matching algorithms
5. Rabin-Karp algorithm
