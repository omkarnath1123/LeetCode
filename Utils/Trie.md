# Trie (Prefix Tree) Pattern

## Overview
A Trie is a specialized tree data structure used for efficient retrieval of keys in a dataset of strings. It's particularly useful for prefix-based operations and autocomplete features.

## When to Use?
1. Prefix/Suffix searching
2. Autocomplete suggestions
3. Spell checking
4. IP routing tables
5. Phone directories

## Basic Structure
```java
class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEnd;
    
    public TrieNode() {
        children = new HashMap<>();
        isEnd = false;
    }
}

class Trie {
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
}

## Problem List

### Easy Problems
1. [720. Longest Word in Dictionary](https://leetcode.com/problems/longest-word-in-dictionary/)
2. [1065. Index Pairs of a String](https://leetcode.com/problems/index-pairs-of-a-string/)
3. [1408. String Matching in an Array](https://leetcode.com/problems/string-matching-in-an-array/)

### Medium Problems
1. [1166. Design File System](https://leetcode.com/problems/design-file-system/)
2. [1268. Search Suggestions System](https://leetcode.com/problems/search-suggestions-system/)
3. [421. Maximum XOR of Two Numbers in an Array](https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/)
4. [648. Replace Words](https://leetcode.com/problems/replace-words/)
5. [677. Map Sum Pairs](https://leetcode.com/problems/map-sum-pairs/)

### Hard Problems
1. [212. Word Search II](https://leetcode.com/problems/word-search-ii/)
2. [425. Word Squares](https://leetcode.com/problems/word-squares/)
3. [1032. Stream of Characters](https://leetcode.com/problems/stream-of-characters/)

## Common Operations

### 1. Insert Operation
```java
public void insert(String word) {
    TrieNode node = root;
    for (char c : word.toCharArray()) {
        node.children.putIfAbsent(c, new TrieNode());
        node = node.children.get(c);
    }
    node.isEnd = true;
}
```

### 2. Search Operation
```java
public boolean search(String word) {
    TrieNode node = root;
    for (char c : word.toCharArray()) {
        if (!node.children.containsKey(c)) {
            return false;
        }
        node = node.children.get(c);
    }
    return node.isEnd;
}
```

### 3. StartsWith Operation
```java
public boolean startsWith(String prefix) {
    TrieNode node = root;
    for (char c : prefix.toCharArray()) {
        if (!node.children.containsKey(c)) {
            return false;
        }
        node = node.children.get(c);
    }
    return true;
}
```

## Time & Space Complexity
### Time Complexity
- Insert: O(m) where m is word length
- Search: O(m)
- StartsWith: O(m)

### Space Complexity
- O(ALPHABET_SIZE * m * n) where:
  - m is average word length
  - n is number of words
  - ALPHABET_SIZE is size of character set

## Advanced Applications

### 1. Autocomplete System
```java
public List<String> getAutoComplete(String prefix) {
    TrieNode node = root;
    for (char c : prefix.toCharArray()) {
        if (!node.children.containsKey(c)) {
            return new ArrayList<>();
        }
        node = node.children.get(c);
    }
    
    List<String> results = new ArrayList<>();
    dfs(node, prefix, results);
    return results;
}

private void dfs(TrieNode node, String word, List<String> results) {
    if (node.isEnd) {
        results.add(word);
    }
    for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
        dfs(entry.getValue(), word + entry.getKey(), results);
    }
}
```

### 2. Wildcard Matching
```java
public boolean searchWildcard(String word) {
    return dfsWildcard(root, word, 0);
}

private boolean dfsWildcard(TrieNode node, String word, int i) {
    if (i == word.length()) {
        return node.isEnd;
    }
    
    if (word.charAt(i) == '.') {
        for (TrieNode child : node.children.values()) {
            if (dfsWildcard(child, word, i + 1)) {
                return true;
            }
        }
        return false;
    }
    
    char c = word.charAt(i);
    if (!node.children.containsKey(c)) {
        return false;
    }
    
    return dfsWildcard(node.children.get(c), word, i + 1);
}
    
    return dfs(self.root, 0)
```

## Optimization Techniques
1. Array-based implementation for fixed alphabet
2. Compression for sparse tries
3. Path compression
4. Memory pooling
5. Lazy loading

## Common Applications
1. Auto-complete systems
2. Spell checkers
3. IP routing tables
4. Phone directories
5. Word games

## Tips & Tricks
1. Consider memory usage
2. Use appropriate node structure
3. Handle empty strings
4. Consider case sensitivity
5. Implement deletion if needed

## Advanced Features
1. Compressed tries
2. Ternary search tries
3. Radix trees
4. Suffix tries
5. Patricia tries

## Real-world Use Cases
1. Search engine autocompletion
2. DNS servers
3. Dictionary implementations
4. Natural language processing
5. Pattern matching engines

## Common Mistakes
1. Memory leaks
2. Not handling edge cases
3. Inefficient node structure
4. Incorrect deletion
5. Poor memory management

## Implementation Variants
1. Array-based tries
2. Hash table tries
3. Compressed tries
4. Suffix tries
5. Prefix trees with values
