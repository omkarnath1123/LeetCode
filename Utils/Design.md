# Design Pattern Problems

## Overview
Design problems focus on implementing data structures, classes, and systems efficiently. These problems test understanding of data structure trade-offs, system design principles, and optimization techniques.

## When to Use?
1. Implementing new data structures
2. Building efficient systems
3. Optimizing existing implementations
4. Handling concurrent operations
5. Managing complex state

## Common Design Patterns

### 1. LRU Cache Pattern
```java
class LRUCache {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private Map<Integer, Node> cache;
    private int capacity;
    private Node head;
    private Node tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        moveToFront(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveToFront(node);
        } else {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNode(newNode);
            
            if (cache.size() > capacity) {
                Node lru = tail.prev;
                removeNode(lru);
                cache.remove(lru.key);
            }
        }
    }
    
    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    
    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }
    
    private void moveToFront(Node node) {
        removeNode(node);
        addNode(node);
    }
}
```

### 2. Trie-based System
```java
class FileSystem {
    class TrieNode {
        Map<String, TrieNode> children;
        int value;
        boolean isFile;
        
        TrieNode() {
            children = new HashMap<>();
            value = -1;
            isFile = false;
        }
    }
    
    private TrieNode root;
    
    public FileSystem() {
        root = new TrieNode();
    }
    
    public boolean createPath(String path, int value) {
        if (path.equals("/") || path.isEmpty()) {
            return false;
        }
        
        String[] components = path.substring(1).split("/");
        TrieNode current = root;
        
        for (int i = 0; i < components.length; i++) {
            String component = components[i];
            if (!current.children.containsKey(component)) {
                if (i == components.length - 1) {
                    current.children.put(component, new TrieNode());
                } else {
                    return false;
                }
            }
            current = current.children.get(component);
        }
        
        if (current.isFile) {
            return false;
        }
        
        current.value = value;
        current.isFile = true;
        return true;
    }
    
    public int get(String path) {
        if (path.equals("/")) {
            return -1;
        }
        
        String[] components = path.substring(1).split("/");
        TrieNode current = root;
        
        for (String component : components) {
            if (!current.children.containsKey(component)) {
                return -1;
            }
            current = current.children.get(component);
        }
        
        return current.isFile ? current.value : -1;
    }
}
        return curr.get('value', -1)
```

## Problem List

### Easy Problems
1. [703. Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream/)
2. [705. Design HashSet](https://leetcode.com/problems/design-hashset/)
3. [706. Design HashMap](https://leetcode.com/problems/design-hashmap/)

### Medium Problems
1. [1166. Design File System](https://leetcode.com/problems/design-file-system/)
2. [146. LRU Cache](https://leetcode.com/problems/lru-cache/)
3. [341. Flatten Nested List Iterator](https://leetcode.com/problems/flatten-nested-list-iterator/)
4. [380. Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/)
5. [384. Shuffle an Array](https://leetcode.com/problems/shuffle-an-array/)

### Hard Problems
1. [460. LFU Cache](https://leetcode.com/problems/lfu-cache/)
2. [1032. Stream of Characters](https://leetcode.com/problems/stream-of-characters/)
3. [295. Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/)

## Design Principles
1. Single Responsibility Principle
2. Open-Closed Principle
3. Interface Segregation
4. Dependency Inversion
5. Composition over Inheritance

## Common Design Patterns

### 1. Observer Pattern
```java
interface Observer {
    void update(Object state);
}

class Subject {
    private List<Observer> observers;
    private Object state;
    
    public Subject() {
        observers = new ArrayList<>();
    }
    
    public void attach(Observer observer) {
        observers.add(observer);
    }
    
    public void detach(Observer observer) {
        observers.remove(observer);
    }
    
    public void setState(Object state) {
        this.state = state;
        notifyObservers();
    }
    
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }
}
```

### 2. Factory Pattern
```java
interface Cache {
    int get(int key);
    void put(int key, int value);
}

class CacheFactory {
    public static Cache createCache(String cacheType, int capacity) {
        switch (cacheType.toUpperCase()) {
            case "LRU":
                return new LRUCache(capacity);
            case "LFU":
                return new LFUCache(capacity);
            default:
                throw new IllegalArgumentException("Unknown cache type: " + cacheType);
        }
    }
}
```

## Time & Space Complexity Considerations
1. Operation complexity
2. Memory usage
3. Scalability
4. Concurrent access
5. Cache efficiency

## Implementation Tips
1. Choose appropriate data structures
2. Handle edge cases
3. Consider thread safety
4. Document assumptions
5. Plan for extensibility

## Common Mistakes
1. Not handling edge cases
2. Poor error handling
3. Inefficient data structures
4. Memory leaks
5. Race conditions

## Advanced Concepts
1. Thread safety
2. Concurrency control
3. Distributed systems
4. Caching strategies
5. Load balancing

## Real-world Applications
1. Database systems
2. File systems
3. Cache implementations
4. Web services
5. Game engines

## Testing Strategies
1. Unit testing
2. Integration testing
3. Load testing
4. Edge case testing
5. Concurrency testing

## Performance Optimization
1. Caching
2. Lazy loading
3. Batch processing
4. Memory pooling
5. Indexing strategies
