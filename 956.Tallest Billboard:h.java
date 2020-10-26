// 956.Tallest Billboard/h

// You are installing a billboard and want it to have the largest height.  The billboard will have two steel supports, one on each side.  Each steel support must be an equal height.

// You have a collection of rods which can be welded together.  For example, if you have rods of lengths 1, 2, and 3, you can weld them together to make a support of length 6.

// Return the largest possible height of your billboard installation.  If you cannot support the billboard, return 0.

 

// Example 1:

// Input: [1,2,3,6]
// Output: 6
// Explanation: We have two disjoint subsets {1,2,3} and {6}, which have the same sum = 6.

// Note:

// 0 <= rods.length <= 20
// 1 <= rods[i] <= 1000
// The sum of rods is at most 5000.



class Solution {
    
    int ans = 0;
    
    public int tallestBillboard(int[] rods) {
        // dfs + pruning
        // sort and begin from the tallest, quicker to find the res
        // pruning:
        // 1. weld all the remain with smaller still not meet the taller: abs(left - right) > remain
        // 2. weld the remain, the cur steels collection is still smaller than the stored ans: left + right + remain <= 2 * ans
        if(rods == null || rods.length < 2) 
            return 0;
        int remain = 0;
        for(int rod: rods) 
            remain += rod;
        Arrays.sort(rods);
        helper(rods, rods.length-1, remain, 0, 0);
        return ans;
    }
    
    public void helper(int[] rods, int idx, int remain, int left, int right) {
        // find a pair and update the ans
        if(left == right && left > ans) 
            ans = left;
        
        // pruning
        if(Math.abs(left - right) > remain || left + right + remain <= 2 * ans) 
            return;
        remain -= rods[idx];
        // try three ways: weld the left, weld the right, don't weld
        helper(rods, idx - 1, remain, left + rods[idx], right);
        helper(rods, idx - 1, remain, left, right + rods[idx]);
        helper(rods, idx - 1, remain, left, right);
    }
}

// refer to LC-cn: 衬字逃 2020-06-25
// try dp someday