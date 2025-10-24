# Sliding Window Pattern

## Overview
The Sliding Window pattern is an algorithmic technique that involves creating a "window" that slides over data to track a subset of elements. This pattern is particularly useful for array or string problems where we need to find or calculate something among all subarrays or substrings of a given size.

## When to Use?
Use Sliding Window when:
1. The problem deals with arrays/strings and asks to find/calculate something among contiguous subarrays/substrings
2. The problem involves finding min, max, median, or sum of subarrays
3. The problem requires tracking a subset of elements

## Types of Sliding Windows

### 1. Fixed Size Window
- Window size remains constant throughout
- Usually simpler to implement
- Example: Find max sum subarray of size K

### 2. Variable Size Window
- Window size changes based on conditions
- More complex but more versatile
- Example: Longest substring with K distinct characters

## Common Patterns

### Pattern 1: Fixed Window Sum/Average
```java
public double findFixedWindowSum(int[] nums, int k) {
    int windowSum = 0;
    // Sum first k elements
    for (int i = 0; i < k; i++) {
        windowSum += nums[i];
    }
    
    // Slide window
    int maxSum = windowSum;
    for (int i = k; i < nums.length; i++) {
        windowSum = windowSum - nums[i - k] + nums[i];  // Slide window
        maxSum = Math.max(maxSum, windowSum);
    }
    return maxSum;
}
```

### Pattern 2: Variable Window with Condition
```java
public int findVariableWindow(String s, int k) {
    Map<Character, Integer> window = new HashMap<>();
    int left = 0, right = 0;
    int maxLength = 0;
    
    while (right < s.length()) {
        // Add element at right
        char c = s.charAt(right);
        window.put(c, window.getOrDefault(c, 0) + 1);
        
        // Shrink window while condition is not met
        while (window.size() > k) {
            char leftChar = s.charAt(left);
            window.put(leftChar, window.get(leftChar) - 1);
            if (window.get(leftChar) == 0) {
                window.remove(leftChar);
            }
            left++;
        }
        
        // Update result if condition is met
        if (window.size() == k) {
            maxLength = Math.max(maxLength, right - left + 1);
        }
        right++;
    }
    
    return maxLength;
}
```

## Problem List

### Easy Problems
1. [643. Maximum Average Subarray I](https://leetcode.com/problems/maximum-average-subarray-i/)
2. [1876. Substrings of Size Three with Distinct Characters](https://leetcode.com/problems/substrings-of-size-three-with-distinct-characters/)

### Medium Problems
1. [1100. Find K-Length Substrings With No Repeated Characters](https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/)
2. [1156. Swap For Longest Repeated Character Substring](https://leetcode.com/problems/swap-for-longest-repeated-character-substring/)
3. [1004. Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/)
4. [904. Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/)
5. [424. Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/)

### Hard Problems
1. [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)
2. [239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/)
3. [995. Minimum Number of K Consecutive Bit Flips](https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/)

## Time & Space Complexity
- Usually O(n) time complexity where n is the size of array/string
- Usually O(k) space complexity where k is the window size or unique elements in window
- Can be O(1) space for fixed window size problems

## Common Techniques
1. Two pointers (left and right bounds of window)
2. Hash Map/Set for frequency counting
3. Queue/Deque for maintaining elements in window
4. Running sum/product maintenance

## Tips & Tricks
1. Always consider edge cases (empty array, window size > array size)
2. Be careful with window bounds (inclusive/exclusive)
3. Remember to update window state when sliding
4. Consider using collections to maintain window state
5. Watch out for off-by-one errors in window calculations
