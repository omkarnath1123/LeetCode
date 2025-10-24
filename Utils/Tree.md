# Tree Patterns

## Overview
Tree problems often involve recursive traversal, manipulation of tree structures, and understanding various tree properties. This guide focuses on common tree patterns and their applications.

## Types of Trees
1. Binary Trees
2. Binary Search Trees (BST)
3. N-ary Trees
4. Balanced Trees (AVL, Red-Black)
5. Tries

## Common Tree Traversals

### 1. DFS Traversals
```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}

// Inorder (Left -> Root -> Right)
public void inorder(TreeNode root) {
    if (root == null) return;
    inorder(root.left);
    process(root);
    inorder(root.right);
}

// Preorder (Root -> Left -> Right)
public void preorder(TreeNode root) {
    if (root == null) return;
    process(root);
    preorder(root.left);
    preorder(root.right);
}

// Postorder (Left -> Right -> Root)
public void postorder(TreeNode root) {
    if (root == null) return;
    postorder(root.left);
    postorder(root.right);
    process(root);
}
```

### 2. BFS (Level Order) Traversal
```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> currentLevel = new ArrayList<>();
        
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            currentLevel.add(node.val);
            
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        
        result.add(currentLevel);
    }
    
    return result;
}
            if node.right: queue.append(node.right)
        result.append(level)
    return result
```

## Problem List

### Easy Problems
1. [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)
2. [226. Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/)
3. [543. Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/)

### Medium Problems
1. [114. Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/)
2. [1214. Two Sum BSTs](https://leetcode.com/problems/two-sum-bsts/)
3. [98. Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)
4. [236. Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)
5. [105. Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)

### Hard Problems
1. [124. Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/)
2. [297. Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/)
3. [968. Binary Tree Cameras](https://leetcode.com/problems/binary-tree-cameras/)

## Common Patterns

### 1. Path Problems
- Finding paths between nodes
- Path sum problems
- Maximum/minimum path values

### 2. Tree Construction
- From traversal arrays
- From level order arrays
- BST construction

### 3. Tree Properties
- Height/Depth calculations
- Balanced tree validation
- BST validation

### 4. Tree Transformation
- Flattening to linked list
- Converting to mirror tree
- Pruning trees

## Time & Space Complexity Analysis

### Time Complexity
- Traversal: O(n)
- Height: O(log n) for balanced, O(n) for skewed
- Search in BST: O(log n) average, O(n) worst
- Insertion/Deletion in BST: O(log n) average

### Space Complexity
- Recursive: O(h) where h is height
- Iterative: O(w) where w is maximum width
- BFS: O(w) where w is maximum width
- DFS: O(h) where h is height

## Tips & Tricks
1. Consider both recursive and iterative solutions
2. Use level order for level-based problems
3. Use inorder for BST to get sorted order
4. Keep track of parent nodes when needed
5. Use dummy nodes for complex transformations

## Common Mistakes
1. Not handling null nodes
2. Incorrect base cases
3. Not considering unbalanced trees
4. Mixing up traversal orders
5. Stack overflow in deep recursion

## Advanced Techniques
1. Morris Traversal (O(1) space)
2. Threaded Binary Trees
3. Segment Trees
4. Fenwick Trees
5. Custom Tree Serialization

## Real-world Applications
1. File systems
2. Database indexing
3. Expression parsing
4. Huffman coding
5. Decision trees
